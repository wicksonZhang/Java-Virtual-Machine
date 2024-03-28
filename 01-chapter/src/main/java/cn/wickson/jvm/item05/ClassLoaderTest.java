package cn.wickson.jvm.item05;

public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        // 创建自定义类加载器
        CustomClassLoader classLoader = new CustomClassLoader("E:/");
        // 加载加密的类
        Class<?> clazz = classLoader.loadClass("ExampleClass");
        // 创建类实例并调用方法
        Object instance = clazz.getDeclaredConstructor().newInstance();
        clazz.getMethod("sayHello").invoke(instance);

        System.out.println("加载此类的类的加载器为：" + clazz.getClassLoader().getClass().getName()); // chapter04.java2.MyClassLoader
        System.out.println("加载当前Demo1类的类的加载器的父类加载器为：" + clazz.getClassLoader().getParent().getClass().getName());
    }

}
