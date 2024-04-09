package com.example.demo.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dynamicDatasource.Dynamic;
import com.example.demo.dynamicDatasource.DynamicDataSourceHolder;
import com.example.demo.transaction.entity.Legends;
import com.example.demo.transaction.mapper.LegendsMapper;

@Service
public class LeagueServiceImpl implements LeagueService {
    @Autowired
    private LegendsMapper legendsMapper;

    @Override
    @Dynamic(DynamicDataSourceHolder.SLAVE)
    @Transactional(rollbackFor = Exception.class)
    public void addLegend(String name) {
        Legends legends = legendsMapper.selectById("25");
        legends.setName(name);
        legendsMapper.insert(legends);
    }
}