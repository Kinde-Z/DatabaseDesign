package com.kinde.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinde.springboot.common.Result;
import com.kinde.springboot.entity.Drug;
import com.kinde.springboot.entity.Supplier;
import com.kinde.springboot.service.ISupplierService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private ISupplierService supplierService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Supplier supplier) {
        supplierService.saveOrUpdate(supplier);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        supplierService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        supplierService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(supplierService.list());
    }

    //-----------
    @GetMapping("/ids")
    public Result findSupplierById() {
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        List<Supplier> list = supplierService.list(queryWrapper);
        return Result.success(list);
    }
    //------------

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(supplierService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return Result.success(supplierService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}
