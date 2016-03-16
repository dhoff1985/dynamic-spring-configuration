package com.dthoffman.dynamicspringconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dhoffman on 3/15/16.
 */
@Component
public class PropertyEventApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    PropertyEventBus propertiesEventBus;

    Pattern valuePattern = Pattern.compile("\\$\\{(.*)\\}");

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        for(String beanName: applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(beanName);
            for(Field field: bean.getClass().getDeclaredFields()) {
                Value valueAnnotation = field.getAnnotation(Value.class);
                if(valueAnnotation != null) {
                    Matcher valueMatcher = valuePattern.matcher(valueAnnotation.value());
                    if(valueMatcher.find()) {
                        String property = valueMatcher.group(1);
                        propertiesEventBus.addPropertyListener(property, new UpdateBeanPropertySourcePropertyListener(field, bean));
                    }
                }
            }
        }

    }
}
