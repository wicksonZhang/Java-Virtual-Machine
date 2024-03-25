## 内容大纲

> 本章节示例代码：



---

## 什么是类加载器？

类加载器：主要将类的字节码加载到内存中，并且在运行时将其转换为 Java 对象的过程。

![image-20240228211308931](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402282113106.png)



## 类加载器的分类

在Java中，类加载器（ClassLoader）根据其加载类的来源和加载策略的不同，可以分为以下几种主要类型：

**启动类加载器（Bootstrap Class Loader）**

* 启动类加载器：主要负责加载 Java 的核心类库，例如 `java.lang` 包中的类。
* 注意：**启动类加载器是虚拟机底层（C++）实现的类，并不是 Java 类，无法再 Java 中获取对其的引用**。

**扩展类加载器（Extension Class Loader）**

* 扩展类加载器：主要负责加载 java 的扩展类库，位于JRE 的 `lib/ext` 目录下的 JAR 文件中的类。
* 注意：**扩展类加载器是由 Java 实现的一部分，可以通过 Java 代码获取对其的引用**。

**应用程序类加载器（Application Class Loader）**

* 应用程序类加载器：主要负责加载应用程序的类路径（classpath）下的类，通常是应用程序的类和第三方库的类，是 `sun.misc.Launcher$AppClassLoader`类的实例，在JVM启动时创建。
* 注意：**扩展类加载器是由 Java 实现的一部分，通常是 Java 开发者自定义类加载器的父加载器。**

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202403252341774.png" alt="image-20240325234103733" style="zoom:100%;float:left" />















