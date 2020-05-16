package ru.geekbrains.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;
import ru.geekbrains.db.DbConnection;

@Component
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {

        if (bean instanceof DbConnection) {
            ((DbConnection) bean).connect();
        }
        return bean;
    }

}
