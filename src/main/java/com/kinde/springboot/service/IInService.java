package com.kinde.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinde.springboot.entity.In;

public interface IInService extends IService<In> {

    Page<In> findPage(Page<In> page, String name);
}
