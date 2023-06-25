package com.kinde.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinde.springboot.entity.Supplier;
import com.kinde.springboot.mapper.SupplierMapper;
import com.kinde.springboot.service.ISupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

}
