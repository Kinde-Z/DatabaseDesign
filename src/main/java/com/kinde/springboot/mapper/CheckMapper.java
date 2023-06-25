package com.kinde.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinde.springboot.entity.Check;
import com.kinde.springboot.entity.Out;
import org.apache.ibatis.annotations.Param;

public interface CheckMapper extends BaseMapper<Check> {

    Integer findUser(@Param("name") String name);

    Page<Check> findPage(Page<Check> page,@Param("name") String name);
}
