package com.minis.beans;

import com.minis.beans.exception.NoSuchBeanDefinitionException;
public interface BeanFactory {
    //获取指定名称的bean的实例
    Object getBean(String beanName) throws NoSuchBeanDefinitionException;
    //检查是否存在指定名称的Bean
    Boolean containsBean(String beanName);

    //void registerBean(String beanName, Object obj);

    //判断指定名称的Bean是否为单例
    boolean isSingleton(String name);
    //判断指定名称的bean是否为原型
    boolean isPrototype(String name);
    //获取指定名称的bean的类型
    Class<?> getType(String name);

}
