package com.shop.core.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BeanUtils {

    public static void copyNonNullProperties(Object source, Object destination, String... ignoreProperties) {
        final Set<String> ignoreAllProperties = new HashSet<>();
        ignoreAllProperties.addAll(getPropertyNamesWithNullValue(source));
        ignoreAllProperties.addAll(Arrays.asList(ignoreProperties));

        org.springframework.beans.BeanUtils.copyProperties(source, destination, ignoreAllProperties.toArray(new String[]{}));
    }

    private static Set<String> getPropertyNamesWithNullValue(Object source) {
        final BeanWrapper sourceBeanWrapper = new BeanWrapperImpl(source);
        final java.beans.PropertyDescriptor[] propertyDescriptors = sourceBeanWrapper.getPropertyDescriptors();
        final Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            // Check if value of this property is null then add it to the collection
            Object propertyValue = sourceBeanWrapper.getPropertyValue(propertyDescriptor.getName());
            if (propertyValue != null) continue;

            emptyNames.add(propertyDescriptor.getName());
        }

        return emptyNames;
    }
}