package com.kinde.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("sys_out")
public class Out implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer drugId;

    private Integer userId;

    private Integer number;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date outTime;

    private String message;

    @TableField(exist = false)
    private String drugName;

    @TableField(exist = false)
    private String userName;
}
