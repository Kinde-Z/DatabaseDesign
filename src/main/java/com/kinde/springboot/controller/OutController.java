package com.kinde.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinde.springboot.common.Result;
import com.kinde.springboot.entity.Out;
import com.kinde.springboot.service.IOutService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/out")
public class OutController {
    @Resource
    private IOutService outService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Out out) {
        outService.saveOrUpdate(out);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        outService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        outService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(outService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(outService.getById(id));
    }

    @GetMapping("/user")
    public Result findUser(@RequestParam String name) {
//        QueryWrapper<Stock> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("name", name);
//        queryWrapper.orderByDesc("id");
        Integer user = outService.findUser(name);

//        System.out.println(user);
//        return Result.success(stockService.page(new Page<>(pageNum, pageSize), queryWrapper));
        return Result.success(user);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
//        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        Page<Course> page = courseService.page(new Page<>(pageNum, pageSize), queryWrapper);
//        List<Course> records = page.getRecords();
//        for (Course record : records) {
//            User user = userService.getById(record.getTeacherId());
//            if(user != null) {
//                record.setTeacher(user.getNickname());
//            }
//
//        }
        Page<Out> page = outService.findPage(new Page<>(pageNum, pageSize), name);
//        System.out.println(page);
        return Result.success(page);
    }

}
