package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.domain.MyConfiguration;
import com.example.demo.dynamicDatasource.DynamicProperties;
import com.example.demo.importSelector.MyApplication;
import com.example.demo.propertySource.Computer;
import com.example.demo.propertySource.Pad;
import org.springframework.context.annotation.ImportResource;

//5f42b8000782173a--77e6nqZEfLZlkJ0BgB2ROb1iXEulOYuE
//DataSourceAutoConfiguration.class默认会帮我们自动配置单数据源，所以，如果想在项目中使用多数据源就需要排除它，手动指定多数据源
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@MyApplication
@ImportResource(value = {
        "classpath:spring-datasource.xml"
})
// @MapperScan(basePackages = "com.example.demo.transaction.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        // Person person = applicationContext.getBean(Person.class);
        // person.callPet();
        // applicationContext.publishEvent(new MyEvent("想涨工资"));
        // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        // context.start();
        System.out.println(applicationContext.getBean(Computer.class).getName());
        System.out.println(applicationContext.getBean(Pad.class).getName());
        System.out.println(applicationContext.getBean(Computer.class).getScreen().getSize());
        System.out.println(applicationContext.getBean("aa"));
        applicationContext.getEnvironment().getProperty("server.port");
        applicationContext.getEnvironment().getProperty("computer.name");
        System.out.println(applicationContext.getBean(DynamicProperties.class).getDataSources());
        MyConfiguration configuration = applicationContext.getBean(MyConfiguration.class);
        System.out.println(configuration.dog2());
        System.out.println(configuration.dog2());//两次打印的对象一致
        System.out.println();
    }

}
