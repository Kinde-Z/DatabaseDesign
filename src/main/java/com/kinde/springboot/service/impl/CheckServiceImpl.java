package com.kinde.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinde.springboot.entity.Check;
import com.kinde.springboot.mapper.CheckMapper;
import com.kinde.springboot.service.ICheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements ICheckService {

    @Resource
    private CheckMapper checkMapper;

    @Override
    public Integer findUser(String name) {
        return checkMapper.findUser(name);
    }

    @Override
    public Page<Check> findPage(Page<Check> page, String name) {
        return checkMapper.findPage(page, name);
    }
}
