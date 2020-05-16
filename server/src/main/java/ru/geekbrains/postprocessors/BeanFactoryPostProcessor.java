package ru.geekbrains.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryPostProcessor implements org.springframework.beans.factory.config.BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        for(String name: beanDefinitionNames){
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            String className = beanDefinition.getBeanClassName();
            System.out.println(className);
        }

    }
}
