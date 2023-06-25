package com.kinde.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinde.springboot.common.Result;
import com.kinde.springboot.entity.Check;
import com.kinde.springboot.service.ICheckService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {
    @Resource
    private ICheckService checkService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Check check) {
        checkService.saveOrUpdate(check);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        checkService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        checkService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(checkService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(checkService.getById(id));
    }

    @GetMapping("/user")
    public Result findUser(@RequestParam String name) {
//        QueryWrapper<Stock> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("name", name);
//        queryWrapper.orderByDesc("id");
        Integer user = checkService.findUser(name);

        System.out.println(user);
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
        Page<Check> page = checkService.findPage(new Page<>(pageNum, pageSize), name);
        return Result.success(page);
    }

}
