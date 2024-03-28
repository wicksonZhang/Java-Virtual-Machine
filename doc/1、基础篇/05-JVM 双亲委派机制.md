## 内容大纲





## 什么是双亲委派机制

双亲委派机制：指的是在 JVM 中的一种类加载规范，其中子类加载器会向上委托父类加载器进行加载，保证类加载的顺序和安全性。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202403272211510.png" alt="image-20240327221122469" style="zoom:100%;float:left" />



## 打破双亲委派机制

### 为什么要打破双亲委派机制？

在某些特定的场景下，我们需要实现特定的一些功能，可能需要打破双亲委派机制。例如，Tomcat 为了支持 Servlet 规范中的 Web 应用程序中的类加载需求。

* Tomcat中打破双亲委派机制属于自定义类加载器的方式。这种自定义类加载器可以为每个Web应用程序创建一个独立的类加载器实例，使得每个Web应用程序**都有自己独立的类加载路径和类加载环境，从而实现类加载的隔离**。这样也就避免了类的冲突或者安全性问题。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202403272242786.png" alt="image-20240327224241731" style="zoom:80%;float:left" />



### 打破类加载器有哪几种方式？

目前打破双亲委派机制方法有如下几种：

1. **自定义类加载器**：编写自定义的类加载器，在加载类的时候修改双亲委派机制的逻辑。主要就是继承 ClassLoader 类并且重写 findClass() 
2. **使用线程上下文类加载器**（Thread Context ClassLoader）：在某些情况下，线程上下文类加载器可以用来打破双亲委派机制。
3. **OSGi 框架**：OSGi 是一种动态模块化的 Java 框架，它提供了一种在运行时动态加载和卸载模块的机制



## 自定义类加载器

自定义类加载器：主要通过继承 ClassLoader 类，并重写其中的一些方法来实现自定义的类加载逻辑。自定义类加载器在某些场景下非常有用，比如应用程序服务器、插件化系统、动态代理等。

如下是 ClassLoader 四个核心方法

```java
public abstract class ClassLoader {
    
    // 类加载的入口，提供了双亲委派机制。内部会调用 findClass 
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    // 类加载器子类实现，获取二进制数据调用 defineClass，比如 URLClassLoader 会根据文件路径获取类文件中的而精致数据
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }
 
    // 做一些类名的校验，然后调用虚拟机底层的方法将字节码信息加载到虚拟机中
    protected final Class<?> defineClass(byte[] b, int off, int len) throws ClassFormatError {
        return defineClass(null, b, off, len, null);
    }
    
    // 执行类生命周期中的连接阶段
    private native void resolveClass0(Class<?> c);
    
}
```





