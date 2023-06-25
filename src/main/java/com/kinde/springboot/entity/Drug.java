package com.kinde.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.kinde.springboot.config.CustomerDoubleSerialize;
import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.io.Serializable;


@Data
@TableName("sys_drug")
public class Drug implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer guarantee;

    @JsonSerialize(using = CustomerDoubleSerialize.class)
    private Double price;

    private Integer number;

    @JsonSerialize(using = CustomerDoubleSerialize.class)
    private Double amount;
}
