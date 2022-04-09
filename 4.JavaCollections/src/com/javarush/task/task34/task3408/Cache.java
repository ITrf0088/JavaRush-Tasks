package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cache<K, V> {

    private Map<K, V> cache = new WeakHashMap<>();


    public V getByKey(K key, Class<V> clazz) throws Exception {
        V v = (V) cache.get(key);
        if(v == null){
            Object o = clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key, (V) o);
            return cache.get(key);
        }
        return v ;
    }
    public boolean put(V obj)  {
        Method method = null;
        try {
            method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            Object o = method.invoke(obj);
            cache.put((K) o,obj);
            return  true;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

        }
        return false;
    }

    public int size() {
        return cache.size();
    }
}
