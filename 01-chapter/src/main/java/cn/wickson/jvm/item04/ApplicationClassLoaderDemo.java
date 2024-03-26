package cn.wickson.jvm.item04;

import cn.hutool.core.lang.Validator;
import cn.wickson.jvm.item04.model.Person;

/**
 * 应用程序加载器
 */
public class ApplicationClassLoaderDemo {

    public static void main(String[] args) {
        // 自定义类对象
        ClassLoader personClassLoader = Person.class.getClassLoader();
        System.out.println("personClassLoader = " + personClassLoader);

        // 第三方类对象(hutool)
        ClassLoader validatorClassLoader = Validator.class.getClassLoader();
        System.out.println("validatorClassLoader = " + validatorClassLoader);
    }

}
