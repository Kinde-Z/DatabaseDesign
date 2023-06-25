package com.kinde.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_suppliers")
public class Supplier implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String phone;

    private String address;

}