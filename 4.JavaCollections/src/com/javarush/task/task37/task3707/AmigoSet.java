package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>,Serializable,Cloneable {

    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map ;


    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max((int)(collection.size() / .75f)+1,16));
        addAll(collection);
    }


    @Override
    public Iterator iterator() {

        return map.keySet().iterator();
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public boolean removeIf(Predicate filter) {
        return false;
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public Stream stream() {
        return null;
    }

    @Override
    public Stream parallelStream() {
        return null;
    }

    @Override
    public int size() {
        return map.size();
    }



    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {

        return map.remove(o) !=  null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        try {
            HashMap<E,Object> hashMap = (HashMap<E, Object>) map.clone();
            AmigoSet<E> amigoSet = new AmigoSet<>(hashMap.keySet());
            return amigoSet;
        }catch (Exception e){
            throw new InternalError();
        }

    }

    private void writeObject(ObjectOutputStream outputStream){
        try {
            outputStream.defaultWriteObject();
            outputStream.writeInt(HashMapReflectionHelper.<Integer>callHiddenMethod(map,"capacity"));
            outputStream.writeFloat(HashMapReflectionHelper.<Float>callHiddenMethod(map,"loadFactor"));
            outputStream.writeInt(map.size());
            for (E e : map.keySet()) {
                outputStream.writeObject(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream inputStream){
        try {
            inputStream.defaultReadObject();

            int capacity =  inputStream.readInt();
            float  loadfactor =  inputStream.readFloat();

            map = new HashMap<>(capacity,loadfactor);

            int size  = inputStream.readInt();
            for (int i = 0; i < size; i++) {
                map.put((E) inputStream.readObject(),PRESENT);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }


}
