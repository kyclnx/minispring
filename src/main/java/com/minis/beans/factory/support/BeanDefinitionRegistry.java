package com.minis.beans.factory.support;

import com.minis.beans.factory.config.BeanDefinition;

//这个相当于一个存放BeanDefinition的仓库，可以存放、移除、获取、判断BeanDefinition对象
public interface BeanDefinitionRegistry {
    //注册bean的定义
    void registerBeanDefinition(String name, BeanDefinition bd);
    //移除bean的定义
    void removeBeanDefinition(String name);
    //获取指定名称的bean的定义
    BeanDefinition getBeanDefinition(String name);
    //检查是否存在指定名称的bean的定义
    boolean containsBeanDefinition(String name);

}
