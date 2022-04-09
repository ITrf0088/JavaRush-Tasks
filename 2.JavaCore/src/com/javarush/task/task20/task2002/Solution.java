package com.javarush.task.task20.task2002;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = new File("E:/text.txt");

            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            User user = new User();
            user.setFirstName("Jamol");
            user.setLastName("Komilov");
            String s = "12 03 2000";
            user.setBirthDate(new Date(1223432434524L));
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user);

            User us = new User();
            us.setFirstName("Rubi");
            us.setLastName("Rail");
            us.setBirthDate(new Date(1508944516168L));
            us.setMale(true);
            us.setCountry(User.Country.OTHER);
            javaRush.users.add(us);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public  List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter bw = new PrintWriter(outputStream);

            if (users.size() != 0) {
                for (int i = 0; i < users.size(); i++) {
                    bw.println(users.get(i).getFirstName());
                    bw.println(users.get(i).getLastName());
                    bw.println(users.get(i).getBirthDate().getTime());
                    bw.println(users.get(i).isMale());
                    bw.println(users.get(i).getCountry());
                }
            }

            bw.close();

        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            List<User> user = new ArrayList<>();
            while (bf.ready()){
                User user1 = new User();
                user1.setFirstName(bf.readLine());
                user1.setLastName(bf.readLine());
                user1.setBirthDate(new Date(Long.parseLong(bf.readLine())));
                user1.setMale(Boolean.parseBoolean(bf.readLine()));
                user1.setCountry(User.Country.valueOf(bf.readLine()));
                this.users.add(user1);
        }

            bf.close();

            for (User use : users) {
                System.out.println(use.getFirstName());
                System.out.println(use.getLastName());
                System.out.println(use.getBirthDate());
                System.out.println(use.isMale());
                System.out.println(use.getCountry());
            }

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
