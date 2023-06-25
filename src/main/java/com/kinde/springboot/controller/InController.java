package com.kinde.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinde.springboot.common.Result;
import com.kinde.springboot.entity.In;
import com.kinde.springboot.service.IInService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/in")
public class InController {
    @Resource
    private IInService inService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody In in) {
        inService.saveOrUpdate(in);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        inService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        inService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(inService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(inService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        Page<In> page = inService.findPage(new Page<>(pageNum, pageSize), name);
//        System.out.println(page);
        return Result.success(page);
    }

}
