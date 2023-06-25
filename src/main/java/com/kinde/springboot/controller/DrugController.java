package com.kinde.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinde.springboot.common.Result;
import com.kinde.springboot.entity.Drug;
import com.kinde.springboot.service.IDrugService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/drug")
public class DrugController {

    @Resource
    private IDrugService drugService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Drug drug) {
        drugService.saveOrUpdate(drug);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        drugService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        drugService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(drugService.list());
    }

    //-----通过id获取数据库中的药品信息------
    @GetMapping("/ids")
    public Result findDrugById() {
        QueryWrapper<Drug> queryWrapper = new QueryWrapper<>();
        List<Drug> list = drugService.list(queryWrapper);
        return Result.success(list);
    }

    @GetMapping("/num")
    public Result findDrugNum(@RequestParam Integer drugId){
        Integer num = drugService.findDrug(drugId);
        return Result.success(num);
    }
    //------------

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(drugService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Drug> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return Result.success(drugService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}
