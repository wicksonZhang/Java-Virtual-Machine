package cn.wickson.jvm.item05;

public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        CustomClassLoader customClassLoader = new CustomClassLoader("E:");
        Class<?> clazz = customClassLoader.loadClass("cn.wickson.jvm.item05.ExampleClass");
        Object instance = clazz.newInstance();
        System.out.println(instance.getClass().getClassLoader());
    }

}
