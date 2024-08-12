package com.minis.beans;

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
//接口定义好了之后就需要定义一个默认实现类DefaultSingletonBeanRegistry
