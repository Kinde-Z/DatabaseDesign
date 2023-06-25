package com.kinde.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinde.springboot.entity.Out;
import org.apache.ibatis.annotations.Param;

public interface OutMapper extends BaseMapper<Out> {

    Integer findUser(@Param("name") String name);

    Page<Out> findPage(Page<Out> page,@Param("name") String name);
}