package com.kinde.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinde.springboot.entity.Drug;

public interface DrugMapper extends BaseMapper<Drug> {

    Integer findDrug(Integer drugId);
}
