package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author njx
 * @version 1.0
 * @since 1.0
 */
public class XmlBeanDefinitionReader {

    SimpleBeanFactory bf;

    public XmlBeanDefinitionReader(SimpleBeanFactory beanFactory) {
        this.bf = beanFactory;
    }

    //将解析出来的XML转化为BeanDefinition的形式，并且将这些定义注册到传递进来的BeanFactory中
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            //第一次DeBug到了这一步的时候，将beans.xml的文件解析为id = aservice，className = com.minis.test.AServiceImpl
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            // handle constructor
            //第一次DeBug到了这一步的时候，解析了constructor-arg的所内容，type="String" name="name" value="abc和type="int" name="level" value="3"
            List<Element> constructorElements = element.elements("constructor- arg");
                    ArgumentValues AVS = new ArgumentValues();
            for (Element e : constructorElements) {
                String aType = e.attributeValue("type");
                String aName = e.attributeValue("name");
                String aValue = e.attributeValue("value");
                AVS.addArgumentValue(new ArgumentValue(aType, aName, aValue));
            }
            //第一次DeBug到了这一步的时候，将上述解析的内容添加到了ArgumentValue
            beanDefinition.setConstructorArgumentValues(AVS);

            // handle properties
            List<Element> propertyElements = element.elements("property");
            /** 第一次DeBug到了这一步的时候，将type="String" name="property1" value="Someone says"/>
                                           type="String" name="property2" value="Hello World!"/>
                                           type="com.minis.test.BaseService" name="ref1" ref="baseservice"/>  解析**/
            PropertyValues PVS = new PropertyValues();
            List<String> refs = new ArrayList<>();
            for (Element e : propertyElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                String pRef = e.attributeValue("ref");
                String pV = "";
                boolean isRef = false;
                if (pValue != null && !pValue.isEmpty()) {
                    pV = pValue;
                } else if (pRef != null && !pRef.isEmpty()) {
                    isRef = true;
                    pV = pRef;
                    refs.add(pRef);
                }
                PVS.addPropertyValue(new PropertyValue(pType, pName, pV, isRef));
            }
            beanDefinition.setPropertyValues(PVS);

            String[] refArray = refs.toArray(new String[0]);
            beanDefinition.setDependsOn(refArray);
            this.bf.registerBeanDefinition(beanID, beanDefinition);
        }
    }
}
