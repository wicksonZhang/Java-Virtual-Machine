package cn.wickson.jvm.item04;

/**
 * @author ZhangZiHeng
 * @date 2024-03-26
 */
public class BootstrapClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("cn.wickson.classloader.BootStrapClassLoader");
        System.out.println("clazz = " + clazz);
    }

}
