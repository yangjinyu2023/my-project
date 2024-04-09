package com.example.demo.designPattern.proxy.cglib;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import java.lang.reflect.Method;
/**
 * cglib应用测试
 *
 * @author yangjinyu
 * @time 2021/10/12 11:24
 */
// https://blog.csdn.net/danchu/article/details/70238002
public class CglibApplyTest {
    @Test
    public void testMethodIntercept(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleBean.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        SampleBean sample = (SampleBean) enhancer.create();
        sample.test();
    }

    @Test
    public void testFastClass() throws Exception{
        FastClass fastClass = FastClass.create(SampleBean.class);
        FastMethod fastMethod = fastClass.getMethod("getValue",new Class[0]);
        SampleBean bean = new SampleBean();
        bean.setValue("Hello world");
        Assert.assertEquals("Hello world",fastMethod.invoke(bean, new Object[0]));
    }

    @Test
    public void testBeanGenerator() throws Exception{
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("value",String.class);
        Object myBean = beanGenerator.create();
        Method setter = myBean.getClass().getMethod("setValue",String.class);
        setter.invoke(myBean,"Hello cglib");

        Method getter = myBean.getClass().getMethod("getValue");
        Assert.assertEquals("Hello cglib",getter.invoke(myBean));
    }

    @Test
    public void testBeanCopier() throws Exception{
        BeanCopier copier = BeanCopier.create(SampleBean.class, OtherSampleBean.class, false);//设置为true，则使用converter
        SampleBean myBean = new SampleBean();
        myBean.setValue("Hello cglib");
        OtherSampleBean otherBean = new OtherSampleBean();
        copier.copy(myBean, otherBean, null); //设置为true，则传入converter指明怎么进行转换
        Assert.assertEquals("Hello cglib", otherBean.getValue());
    }

    @Test
    public void testBeanMap() throws Exception{
        BeanGenerator generator = new BeanGenerator();
        generator.addProperty("username",String.class);
        generator.addProperty("password",String.class);
        Object bean = generator.create();
        Method setUserName = bean.getClass().getMethod("setUsername", String.class);
        Method setPassword = bean.getClass().getMethod("setPassword", String.class);
        setUserName.invoke(bean, "admin");
        setPassword.invoke(bean,"password");
        BeanMap map = BeanMap.create(bean);
        Assert.assertEquals("admin", map.get("username"));
        Assert.assertEquals("password", map.get("password"));
    }
}