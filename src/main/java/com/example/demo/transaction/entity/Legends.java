package com.example.demo.transaction.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("legends")
public class Legends extends Model<Legends> {
    @TableId("id")
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("sex")
    private String sex;

    @Override
    public Serializable pkVal() {
        return id;
    }
}