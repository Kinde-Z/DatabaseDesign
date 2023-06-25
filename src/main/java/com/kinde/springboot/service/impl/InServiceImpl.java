package com.kinde.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinde.springboot.entity.In;
import com.kinde.springboot.mapper.InMapper;
import com.kinde.springboot.service.IInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InServiceImpl extends ServiceImpl<InMapper, In> implements IInService {

    @Resource
    private InMapper inMapper;

    @Override
    public Page<In> findPage(Page<In> page, String name) {
        return inMapper.findPage(page, name);
    }
}
