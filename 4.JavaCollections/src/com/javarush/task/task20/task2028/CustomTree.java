package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String>implements Cloneable,Serializable {


    Entry<String> root;
    private  List<Entry<String>> list = new ArrayList<>();

    public CustomTree() {
        this.root = new Entry<>("0") ;
        list.add(root);
    }

    @Override
    public int size() {
        return list.size()-1;
    }

    @Override
    public boolean add(String s) {

        boolean isAdd = isadd(s);
        if(!isAdd) {
            for (int i = list.size() / 2; i < list.size(); i++) {
                list.get(i).availableToAddLeftChildren = true;
                list.get(i).availableToAddRightChildren = true;
            }
            isAdd = isadd(s);
        }

        return isAdd;
    }

    private boolean isadd(String s){
        Entry<String> entryS = new Entry<>(s);
        for (int i = 0; i < list.size(); i++) {
            Entry<String> entryRoot = list.get(i);
            if (entryRoot.isAvailableToAddChildren()) {

                if (entryRoot.availableToAddLeftChildren) {
                    entryRoot.leftChild = entryS;
                    entryRoot.availableToAddLeftChildren = false;
                    entryS.parent = entryRoot;
                    list.add(entryS);
                    return true;
                } else {
                    entryRoot.rightChild = entryS;
                    entryRoot.availableToAddRightChildren = false;
                    entryS.parent = entryRoot;
                    list.add(entryS);
                    return true;
                }

            }
        }
        return false;
    }

    public String getParent(String s){

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).elementName.equals(s)){
                return list.get(i).parent.elementName;
            }
        }

        return null;
    }

    @Override
    public boolean remove(Object o) {
        if(!(o instanceof String)){ throw new UnsupportedOperationException(); }
        String number = ((String) o);

        //Обходим цикл
        for (int i = 1; i < list.size(); i++) {
            Entry<String> entry = list.get(i);
            // если имя элемента равняется параметру метода
            if (entry.elementName.equals(number) ||
                    //если у родителя эелемента все ссылки
                    //на потомки null значит что ветку нужно удалять
                    (entry.parent.leftChild == null &&
                    entry.parent.rightChild == null)){

                //то обнуляем поля(разрываем связь) и удаляем из списка
                entry.parent = null;
                entry.leftChild = null;
                entry.rightChild = null;
                list.remove(entry);
                i--;
            }

        }


        return true;
    }

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;

        }

        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }












    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();    }


}
