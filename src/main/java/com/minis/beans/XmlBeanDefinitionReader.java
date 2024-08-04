package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;

/**
 * @author mqz
 * @version 1.0
 * @since 1.0
 */
//将classPathXmlResource中解析的xml信息转换为BeanDefinition的形式
public class XmlBeanDefinitionReader {

    BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    //这个方法将解析的xml内容转换成BeanDefinition并且加载到beanFactory中
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            this.beanFactory.registerBeanDefinition(new BeanDefinition(beanId, beanClassName));
        }
    }

}
