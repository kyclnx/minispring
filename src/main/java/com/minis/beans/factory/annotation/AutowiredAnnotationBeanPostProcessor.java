package com.minis.beans.factory.annotation;

import com.minis.beans.factory.config.AutowireCapableBeanFactory;
import com.minis.beans.factory.config.BeanPostProcessor;
import com.minis.beans.factory.exception.BeansException;
import com.minis.beans.factory.exception.NoSuchBeanDefinitionException;

import java.lang.reflect.Field;

public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private AutowireCapableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if (isAutowired) {
                //  根据属性名查找同名的bean
                String fieldName = field.getName();
                try {
                    Object autowiredObj = this.getBeanFactory().getBean(fieldName);
                    field.setAccessible(true);
                    field.set(bean, autowiredObj);
                    System.out.println("autowire " + fieldName + " for bean " + beanName);
                } catch (NoSuchBeanDefinitionException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }


    public AutowireCapableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
