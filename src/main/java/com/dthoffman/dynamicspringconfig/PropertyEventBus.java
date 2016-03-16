package com.dthoffman.dynamicspringconfig;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhoffman on 3/15/16.
 */
@Component
public class PropertyEventBus {

    Map<String, List<PropertySourcePropertyListener>> propertyListeners;

    PropertyEventBus() {
        propertyListeners = new HashMap<String, List<PropertySourcePropertyListener>>();
    }

    public void publishChange(String property, String value) {
        List<PropertySourcePropertyListener> listeners = propertyListeners.get(property);
        if (listeners != null) {
            for (PropertySourcePropertyListener listener : listeners) {
                listener.newValue(value);
            }
        }

    }

    public void addPropertyListener(String property, PropertySourcePropertyListener propertySourcePropertyListener) {
        List<PropertySourcePropertyListener> list = propertyListeners.get(property);
        if (list == null) {
            list = new ArrayList<PropertySourcePropertyListener>();
            propertyListeners.put(property, list);
        }
        list.add(propertySourcePropertyListener);
    }
}
