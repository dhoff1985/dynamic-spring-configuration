package com.dthoffman.dynamicspringconfig;

import java.lang.reflect.Field;

/**
 * Created by dhoffman on 3/15/16.
 */
class UpdateBeanPropertySourcePropertyListener implements PropertySourcePropertyListener {

    UpdateBeanPropertySourcePropertyListener(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
        field.setAccessible(true);
    }

    Field field;
    Object instance;

    @Override
    public void newValue(String newValue) {
        try {
            field.set(instance, newValue);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
