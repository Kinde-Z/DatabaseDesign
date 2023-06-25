package com.kinde.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinde.springboot.entity.Out;
import com.kinde.springboot.mapper.OutMapper;
import com.kinde.springboot.service.IOutService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OutServiceImpl extends ServiceImpl<OutMapper, Out> implements IOutService {

    @Resource
    private OutMapper outMapper;

    @Override
    public Integer findUser(String name) {
        return outMapper.findUser(name);
    }

    @Override
    public Page<Out> findPage(Page<Out> page, String name) {
        return outMapper.findPage(page, name);
    }
}
