package cn.wickson.classloader;

public class MyLoggerProvider implements Logger {

    public void log(String message) {
        // 具体的日志记录逻辑
        System.out.println("Logging: " + message);
    }

}