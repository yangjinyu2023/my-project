package com.example.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bean相关工具类
 *
 * @author yangjinyu
 */
@Component
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    @SuppressWarnings("NullableProblems")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 通过name获取 Bean
     *
     * @Param: [beanName]
     * @return: java.lang.Object
     * @author: yangjinyu
     * @time: 2019/12/9 20:02
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * 通过class获取Bean
     *
     * @Param: [clazz]
     * @return: T
     * @author: yangjinyu
     * @time: 2019/12/9 20:02
     */
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * bean拷贝，要求属性名称、类型都相同
     *
     * @Param: [targetClass, sourceClass, source]
     * @return: T
     * @author: yangjinyu
     * @time: 2019/12/9 20:02
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T, S> T copyProperties(Class targetClass, Class sourceClass, final S source) {
        if (source == null) {
            return null;
        }
        T target = (T) ReflectUtils.newInstance(targetClass);
        BeanCopier copier = BeanCopier.create(sourceClass, targetClass, false);
        copier.copy(source, target, null);
        return target;
    }

    /**
     * bean拷贝，要求属性名称、类型都相同
     *
     * @Param: [targetClass, sourceClass, source]
     * @return: java.util.List<T>
     * @author: yangjinyu
     * @time: 2019/12/9 20:02
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T, S> List<T> copyProperties(Class targetClass, Class sourceClass, final List<S> source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        List<T> target = new ArrayList<>();
        BeanCopier copier = BeanCopier.create(sourceClass, targetClass, false);
        for (S oSource : source) {
            T oTarget = (T) ReflectUtils.newInstance(targetClass);
            copier.copy(oSource, oTarget, null);
            target.add(oTarget);
        }
        return target;
    }

    /**
     * 将bean转换为map
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Param [bean]
     * @author yangjinyu
     * @time 2021/3/26 10:03
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map < String, Object > map = new HashMap <>(1 << 7);
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key.toString().toUpperCase(), beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为bean
     *
     * @return T
     * @Param [map, bean]
     * @author yangjinyu
     * @time 2021/3/26 10:04
     */
    public static < T > T mapToBean(Map < String, Object > map, Class < T > clazz) {
        T bean = null;
        try {
            Map < String, Object > internalMap = new HashMap <>();
            for (Map.Entry < String, Object > entry : map.entrySet()) {
                internalMap.put(entry.getKey().toLowerCase(), entry.getValue());
            }
            bean = clazz.getConstructor().newInstance();
            BeanMap.create(bean).putAll(internalMap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Param [beanList]
     * @author yangjinyu
     * @time 2021/3/26 10:03
     */
    public static <T> List<Map<String, Object>> beansToMaps(List<T> beanList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (beanList != null && !beanList.isEmpty()) {
            beanList.forEach(bean -> list.add(beanToMap(bean)));
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @return java.util.List<T>
     * @Param [maps, clazz]
     * @author yangjinyu
     * @time 2021/3/26 10:04
     */
    public static <T> List<T> mapsToBeans(List<Map<String, Object>> mapList, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (mapList != null && !mapList.isEmpty()) {
            mapList.forEach(map -> list.add(mapToBean(map, clazz)));
        }
        return list;
    }

}