package com.dthoffman.dynamicspringconfig;

import org.springframework.beans.SimpleTypeConverter;

import java.lang.reflect.Field;

/**
 * Created by dhoffman on 3/15/16.
 */
class UpdateBeanPropertySourcePropertyListener implements PropertySourcePropertyListener {

    SimpleTypeConverter simpleTypeConverter;
    Field field;
    Object instance;

    UpdateBeanPropertySourcePropertyListener(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
        field.setAccessible(true);
        simpleTypeConverter = new SimpleTypeConverter();
    }

    @Override
    public void newValue(String newValue) {
        try {
            field.set(instance, simpleTypeConverter.convertIfNecessary(newValue, field.getType()));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
