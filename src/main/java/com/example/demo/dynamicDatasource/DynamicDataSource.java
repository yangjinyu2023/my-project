package com.example.demo.dynamicDatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    // Springboot 中动态数据源的实现的核心类是 AbstractRoutingDataSource
    // 这里我们注意到了 AbstractRoutingDataSource 实现了 InitializingBean 接口
    // AbstractRoutingDataSource#afterPropertiesSet
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSourceKey();
    }
}