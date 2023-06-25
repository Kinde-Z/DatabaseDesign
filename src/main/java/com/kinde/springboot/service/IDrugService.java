package com.kinde.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kinde.springboot.entity.Drug;

public interface IDrugService extends IService<Drug> {

    Integer findDrug(Integer drugId);
}
