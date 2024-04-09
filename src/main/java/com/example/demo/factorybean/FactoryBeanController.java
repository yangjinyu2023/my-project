package com.example.demo.factorybean;

import com.example.demo.domain.Aa60DTO;
import com.example.demo.domain.Data;
import com.example.demo.domain.Data2;
import com.example.demo.utils.BeanUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FactoryBeanController {


    @PostMapping("/factoryBeanController")
    public Boolean test() {

        System.out.println(BeanUtil.getBean("myFactoryBean"));
        System.out.println(BeanUtil.getBean("myFactoryBean"));
        System.out.println(BeanUtil.getBean(Data.class));
        //com.example.demo.domain.Data@46dd2df4
        //com.example.demo.domain.Data@46dd2df4
        //com.example.demo.domain.Data@46dd2df4

        System.out.println(BeanUtil.getBean(MyFactoryBean.class));
        System.out.println(BeanUtil.getBean("&myFactoryBean"));
        //com.example.demo.factorybean.MyFactoryBean@481acb5f
        //com.example.demo.factorybean.MyFactoryBean@481acb5f


        // MyFactoryBean2
        // isSingleton() {
        //        return false;
        // }
        // 获取了4个不同的Aa60DTO对象
        System.out.println(BeanUtil.getBean("myFactoryBean2"));
        System.out.println(BeanUtil.getBean("myFactoryBean2"));
        System.out.println(BeanUtil.getBean(Aa60DTO.class));
        System.out.println(BeanUtil.getBean(Aa60DTO.class));


        // Data2的scope为prototype
        // 每次getBean会获取新的对象
        System.out.println(BeanUtil.getBean(Data2.class));
        System.out.println(BeanUtil.getBean(Data2.class));

        return true;
    }
}