package com.example.demo.dynamicDatasource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;

@Configuration
public class DataSourceConfig {
    @Autowired
    private DynamicProperties dynamicProperties;

    @Bean
    public DynamicDataSource dynamicDataSource() {
        Map < Object, Object > map = Maps.newHashMap();
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 解析 DynamicDataSource。转换成 DataSource
        //dynamicProperties.getDataSources()
        //        .forEach(properties -> map.put(properties.getName(), properties.initializeDataSourceBuilder().build()));
        dynamicDataSource.setTargetDataSources(map);
        if (map.containsKey(DynamicDataSourceHolder.MASTER)) {
            dynamicDataSource.setDefaultTargetDataSource(map.get(DynamicDataSourceHolder.MASTER));
        }
        return dynamicDataSource;
    }

    //@Bean
    //public SqlSessionFactory dynamicSqlSessionFactory(DataSource dataSource) throws Exception {
    //    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    //    bean.setDataSource(dataSource);
    //    // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
    //    bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml"));
    //    return bean.getObject();
    //}
    //
    //@Bean
    //public SqlSessionTemplate dynamicSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    //    return new SqlSessionTemplate(sqlSessionFactory);
    //}
}