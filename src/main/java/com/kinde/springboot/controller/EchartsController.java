package com.kinde.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.kinde.springboot.common.Result;
import com.kinde.springboot.entity.Drug;
import com.kinde.springboot.entity.In;
import com.kinde.springboot.entity.Out;
import com.kinde.springboot.service.IDrugService;
import com.kinde.springboot.service.IInService;
import com.kinde.springboot.service.IOutService;
import com.kinde.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDrugService drugService;

    @Autowired
    private IInService inService;

    @Autowired
    private IOutService outService;

//    @GetMapping("/example")
//    public Result get() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
//        map.put("y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
//        return Result.success(map);
//    }

    @GetMapping("/num")
    public Result getInNum() {
        List<In> list1 = inService.list();
        int q1 = 0; // 第一季度
        int q2 = 0; // 第二季度
        int q3 = 0; // 第三季度
        int q4 = 0; // 第四季度
        for (In in : list1) {
            Integer num = in.getNumber();
            Date inTime = in.getInTime();
            Quarter quarter = DateUtil.quarterEnum(inTime);
            switch (quarter) {
                case Q1: q1 += num; break;
                case Q2: q2 += num; break;
                case Q3: q3 += num; break;
                case Q4: q4 += num; break;
                default: break;
            }
        }
        List<Out> list2 = outService.list();
        int q5 = 0; // 第一季度
        int q6 = 0; // 第二季度
        int q7 = 0; // 第三季度
        int q8 = 0; // 第四季度
        for (Out out : list2) {
            Integer num = out.getNumber();
            Date outTime = out.getOutTime();
            Quarter quarter = DateUtil.quarterEnum(outTime);
            switch (quarter) {
                case Q1: q5 += num; break;
                case Q2: q6 += num; break;
                case Q3: q7 += num; break;
                case Q4: q8 += num; break;
                default: break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4, q5, q6, q7, q8));
    }

    @GetMapping("/bar")
    public Result getBar() {
        List<Drug> list = drugService.list();
        Map<String, Integer> map = new HashMap<>();

        for (Drug drug : list) {
            map.put(drug.getName(), drug.getNumber());
        }
        System.out.println(map);
        return Result.success(map);
    }

    @GetMapping("/drug_kind")
    public Result getKind() {
        return Result.success(drugService.count());
    }

    @GetMapping("/drug_num")
    public Result getNum() {
        List<Drug> list = drugService.list();
        int num = 0;
        for (Drug drug : list) {
            num += drug.getNumber();
        }
        return Result.success(num);
    }

    @GetMapping("/drug_amount")
    public Result getAmount() {
        List<Drug> list = drugService.list();
        int amount = 0;
        for (Drug drug : list) {
            amount += drug.getAmount();
        }
        return Result.success(amount);
    }
}