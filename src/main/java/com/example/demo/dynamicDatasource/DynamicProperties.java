package com.example.demo.dynamicDatasource;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "dynamic")
public class DynamicProperties {
    // 这里直接使用 DataSourceProperties 来接受 yml 中的配置
    private List<DataSourceProperties> dataSources;
}