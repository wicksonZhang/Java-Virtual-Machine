package cn.wickson.classloader;

public class MyLoggerProvider1 implements Logger {

    public void log(String message) {
        // 具体的日志记录逻辑
        System.out.println("MyLoggerProvider1.log ==> " + message);
    }

}