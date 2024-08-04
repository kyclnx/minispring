package com.minis.context;

import com.minis.beans.BeanDefinition;
import com.minis.beans.BeanFactory;
import com.minis.beans.SimpleBeanFactory;
import com.minis.beans.XmlBeanDefinitionReader;
import com.minis.beans.exception.NoSuchBeanDefinitionException;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

/**
 * 这个类在实例化的过程中做了三件事情，
 * 1、解析xml文件中的内容
 * 2、加载解析的内容，构建beanDefinition
 * 3、读取BeanDefinition的配置的信息，实例化bean，然后将它注入到beanFactory容器中
 */

public class ClassPathXmlApplicationContext implements BeanFactory {

    BeanFactory beanFactory;

    //构造器获取外部配置，解析出Bean的定义，形成内存映像。
    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(simpleBeanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = simpleBeanFactory;
    }



    @Override
    public Object getBean(String beanName) throws NoSuchBeanDefinitionException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }
}