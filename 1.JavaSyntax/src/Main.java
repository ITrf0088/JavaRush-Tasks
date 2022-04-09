
public class Main {
    A a = new A();
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName("com.javarush.task.task35.task3506.Task");
        System.out.println(clazz.getClassLoader());
    }
}
