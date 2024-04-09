package com.example.demo.transaction.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.transaction.entity.Legends;

@Mapper
public interface LegendsMapper extends BaseMapper < Legends > {
    @Insert("insert into Legends(name) value(#{legends.name})")
    int insert(@Param("legends") Legends legends);

    @Select("select * from Legends where id = #{id}")
    Legends selectById(@Param("id") String id);
}
