package com.kinde.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinde.springboot.entity.Out;

public interface IOutService extends IService<Out> {

    Integer findUser(String name);

    Page<Out> findPage(Page<Out> page, String name);
}
