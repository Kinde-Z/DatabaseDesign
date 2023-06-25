package com.kinde.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinde.springboot.entity.In;
import org.apache.ibatis.annotations.Param;

public interface InMapper extends BaseMapper<In> {
    Page<In> findPage(Page<In> page, @Param("name") String name);
}
