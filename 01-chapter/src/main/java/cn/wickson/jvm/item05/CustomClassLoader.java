package cn.wickson.jvm.item05;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader {

    private final String classPath;

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classData = loadClassData(name);
            // 使用defineClass方法来定义一个类，这个方法会检查是否已经加载了这个类
            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Class not found: " + name, e);
        }
    }

    private byte[] loadClassData(String name) throws IOException {
        // 将包路径中的"."替换成文件路径的"/"
        String filePath = classPath + File.separator + name.replace('.', File.separatorChar) + ".class";
        InputStream inputStream = Files.newInputStream(Paths.get(filePath));
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue;
        try {
            while ((nextValue = inputStream.read()) != -1) {
                byteStream.write(nextValue);
            }
        } finally {
            inputStream.close();
        }
        return byteStream.toByteArray();
    }
}