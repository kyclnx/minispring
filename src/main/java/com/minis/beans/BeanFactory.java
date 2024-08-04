package com.minis.beans;

import com.minis.beans.exception.NoSuchBeanDefinitionException;

public interface BeanFactory {
    //一：让这个接口拥有两个特性，一是获取一个bean(getbean方法)

    Object getBean(String beanName) throws NoSuchBeanDefinitionException;
    //二：注册一个beanDefinition。(懒加载，需要这个bean的时候才会创建这个对象)

    void registerBeanDefinition(BeanDefinition beanDefinition);

}