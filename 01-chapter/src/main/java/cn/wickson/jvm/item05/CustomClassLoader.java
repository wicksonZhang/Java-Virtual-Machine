package cn.wickson.jvm.item05;

import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {

    private String byteCodePath;

    public CustomClassLoader(String byteCodePath) {
        this.byteCodePath = byteCodePath;
    }

    public CustomClassLoader(ClassLoader parent, String byteCodePath) {
        super(parent);
        this.byteCodePath = byteCodePath;
    }

    // 加密密钥
    private static final byte[] KEY = {0x01, 0x02, 0x03, 0x04, 0x05};

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 读取加密的类文件
            byte[] encryptedClassData = loadEncryptedClassData(name);
            // 解密类文件
            byte[] decryptedClassData = decryptClassData(encryptedClassData);
            // 定义类
            return defineClass(name, decryptedClassData, 0, decryptedClassData.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Class not found: " + name, e);
        }
    }

    // 从文件中加载加密的类数据
    private byte[] loadEncryptedClassData(String className) throws IOException {
        // 这里假设加密的类文件位于特定路径下，实际情况根据需要进行修改
        try (FileInputStream fis = new FileInputStream(byteCodePath + className + ".txt")) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            return buffer;
        }
    }

    // 解密类数据
    private byte[] decryptClassData(byte[] encryptedData) {
        // 这里简单地对每个字节进行异或运算来模拟解密操作，实际情况下可以使用更加安全的加密算法
        byte[] decryptedData = new byte[encryptedData.length];
        for (int i = 0; i < encryptedData.length; i++) {
            decryptedData[i] = (byte) (encryptedData[i] ^ KEY[i % KEY.length]);
        }
        return decryptedData;
    }

}
