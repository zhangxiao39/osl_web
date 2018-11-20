package com.osl.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UtilReflect {
    public static Field getFieldByFieldName(Object obj, String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
            }
        }
        return null;
    }

    /**
     * Obj fieldName
     * 
     * @param obj
     * @param fieldName
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getValueByFieldName(Object obj, String fieldName) throws SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        Object value = null;
        if (field != null) {
            if (field.isAccessible()) {
                value = field.get(obj);
            } else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * obj fieldName
     * 
     * @param obj
     * @param fieldName
     * @param value
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }

    /**
     * 根据fieldName使用get方法取值
     * 
     * @param obj
     * @param fieldName
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static Object getValByFieldNmWithGetMethod(Object obj, String fieldName) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
        String getMethodNm = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = obj.getClass().getMethod(getMethodNm);
        return method.invoke(obj);
    }

    /**
     * 根据fieldName使用set方法设值
     * 
     * @param obj
     * @param fieldName
     * @param value
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void setValByFieldNmWithSetMethod(Object obj, String fieldName, Object value)
            throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        String setMethodNm = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = obj.getClass().getMethod(setMethodNm, value.getClass());
        method.invoke(obj, value);
    }
}
