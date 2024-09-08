package com.minis.beans.factory.config;

/**
 * @author njx
 * @version 1.0
 * @since 1.0
 */
//存储bean
public interface SingletonBeanRegistry {
    //注册
    void registerSingleton(String beanName, Object singletonObject);
    //获取
    Object getSingleton(String beanName);
    //判断是否存在
    Boolean containsSingleton(String beanName);
    //获取所有的单例bean
    String[] getSingletonNames();
}
