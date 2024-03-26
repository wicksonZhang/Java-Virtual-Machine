package cn.wickson.jvm.item04;

import cn.wickson.classloader.Logger;

import java.util.ServiceLoader;

/**
 * @author ZhangZiHeng
 * @date 2024-03-26
 */
public class ExtensionClassLoaderDemo {

    public static void main(String[] args) {
        // 使用ServiceLoader动态加载Logger接口的实现
        ServiceLoader<Logger> loader = ServiceLoader.load(Logger.class);
        for (Logger logger : loader) {
            logger.log("Hello, World!");
        }
    }

}
