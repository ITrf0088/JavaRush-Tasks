package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException {
        Set<Animal> animals = new HashSet<>();

        List<Class<?>> classes = getClasses(pathToAnimals);
        for (Class<?> aClass : classes) {
            Animal animal = getCorrectAnimal(aClass);
            if(animal!= null) {
                animals.add(animal);
            }
        }

        return animals;
    }

    private static <T extends Animal> T getCorrectAnimal(Class<?> aClass) throws IllegalAccessException, InstantiationException {
        T animal = null;

        Constructor<?>[] constructor = aClass.getDeclaredConstructors();
        for (Constructor<?> constr : constructor) {
            if(constr.getModifiers()== Modifier.PUBLIC
                    && constr.getParameterCount()==0){
                    if(Animal.class.isAssignableFrom(aClass)){
                                  // Плохой тон
                        animal = ((T) aClass.newInstance());
                    }
            }
        }
        return  animal;
    }

    private static List<Class<?>> getClasses(String pathToAnimals) throws ClassNotFoundException, MalformedURLException {
        List<Class<?>> list = new ArrayList<>();

        String[] files = new File(pathToAnimals).list();

        CustomClassLoader loader = new CustomClassLoader(ClassLoader.getSystemClassLoader(),pathToAnimals);
        for (String file : files) {
            list.add(loader.findClass(file.split(".class")[0]));

        }
        return list;
    }



    static class CustomClassLoader extends ClassLoader {

        String path;
        public CustomClassLoader(ClassLoader parent,String path) {
            super(parent);
            this.path = path;
        }

        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] b = loadClassFromFile(path+"/"+name+".class");

            return defineClass("com.javarush.task.task35.task3507.data."+name, b, 0, b.length);
        }

        private byte[] loadClassFromFile(String fileName)  {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buffer;
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int nextValue = 0;
            try {
                while ( (nextValue = inputStream.read()) != -1 ) {
                    byteStream.write(nextValue);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer = byteStream.toByteArray();

            return buffer;
        }
    }
}
