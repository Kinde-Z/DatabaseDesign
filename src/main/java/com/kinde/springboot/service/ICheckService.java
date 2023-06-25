package com.kinde.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinde.springboot.entity.Check;

public interface ICheckService extends IService<Check> {

    Integer findUser(String name);

    Page<Check> findPage(Page<Check> page, String name);
}
