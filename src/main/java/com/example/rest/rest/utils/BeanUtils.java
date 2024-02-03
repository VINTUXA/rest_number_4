package com.example.rest.rest.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;

@UtilityClass
public class BeanUtils {

    @SneakyThrows
    public void copyNonNullProperties(Object source, Object destination){
        Class<?> clazz = source.getClass();
        Field[] fieldz = clazz.getDeclaredFields();

        for(Field field: fieldz){
            field.setAccessible(true);
            Object value = field.get(source);
            if(value != null){
                field.set(destination, value);
            }
        }
    }
}
