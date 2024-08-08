package com.minis;

import com.minis.beans.exception.NoSuchBeanDefinitionException;
import com.minis.context.ClassPathXmlApplicationContext;
import com.minis.test.AService;

public class Main {
    public static void main(String[] args) throws NoSuchBeanDefinitionException {
        //创建了一个对象，加载名为beans.xml的Spring配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //通过context对象从Spring容器中获取名为aservice的bean，并将其转换为AService类型。
        AService aService = (AService) context.getBean("aservice");
        aService.sayHello();
    }
}
