package com.kinde.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinde.springboot.entity.Drug;
import com.kinde.springboot.mapper.DrugMapper;
import com.kinde.springboot.service.IDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements IDrugService {

    @Resource
    private DrugMapper drugMapper;

    @Override
    public Integer findDrug(Integer drugId) {
        return drugMapper.findDrug(drugId);
    }
}
