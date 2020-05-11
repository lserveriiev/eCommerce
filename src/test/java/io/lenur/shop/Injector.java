package io.lenur.shop;

import io.lenur.di.annotation.Inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Injector {
    private final Map<Class<?>, Object> dependencies;

    public Injector(Map<Class<?>, Object> dependencies) {
        this.dependencies = dependencies;
    }

    public Object getInstance(Class<?> clazz) {
        Object object = newInstance(clazz);
        this.inject(object, clazz);

        return object;
    }

    private void inject(Object object, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Inject.class)) {
                try {
                    field.set(object, dependencies.get(field.getType()));
                } catch (IllegalAccessException e) {
                    throw new InjectException(e.getMessage());
                }
            }
        }
    }

    private Object newInstance(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            return constructor.newInstance();

        } catch (NoSuchMethodException
            | InstantiationException
            | IllegalAccessException
            | InvocationTargetException e
        ) {
            throw new InjectException(e.getMessage());
        }
    }
}
