package com.minis.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
//定义了beanName列表和singleton的映射关系，
// beanNames用于存储所有单例bean的别名,
// singletons则存储Bean名称和实现类的映射关系
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {


    //容器中存放所有bean的名称的列表
    protected final List<String> beanNames = new ArrayList<>();

    //容器中存放所有bean实例的map
    protected final Map<String, Object> singletons = new ConcurrentHashMap<>(256);
//ConcurrentHashMap、synchronized
// 为了确保在多线程并发的情况下，我们仍然能安全地实现对单例 Bean 的管理，
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletons) {
            beanNames.add(beanName);
            singletons.put(beanName, singletonObject);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletons.get(beanName);
    }

    @Override
    public Boolean containsSingleton(String beanName) {
        return this.singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return (String[]) beanNames.toArray();
    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletons) {
            this.beanNames.remove(beanName);
            this.singletons.remove(beanName);
        }
    }
}
//修改SimpleBeanFactory，继承上一步创建的DefaultSingletonBeanRegistry，确保SimpleBeanFactory创建的Bean默认就是单例的。