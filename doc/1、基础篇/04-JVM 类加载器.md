## 内容大纲

> 本章节示例代码：



---

## 什么是类加载器？

类加载器：主要将类的字节码加载到内存中，并且在运行时将其转换为 Java 对象的过程。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402282113106.png" alt="image-20240228211308931" style="zoom:80%;" />

---



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

通过 Arthas 进行验证，通过输入 `classloader` 命令可以看到确实存在 `Bootstrap`、`Extension `、`Application ` 三个类加载器。

```sh
[arthas@22540]$ classloader
 name                                                numberOfInstances  loadedCountTotal
 sun.misc.Launcher$AppClassLoader                    1                  13922
 BootstrapClassLoader                                1                  4128
 com.taobao.arthas.agent.ArthasClassloader           1                  1224
 sun.reflect.DelegatingClassLoader                   300                300
 sun.misc.Launcher$ExtClassLoader                    1                  66
 javax.management.remote.rmi.NoCallStackClassLoader  2                  2
 sun.reflect.misc.MethodUtil                         1                  1
Affect(row-cnt:7) cost in 9 ms.
[arthas@22540]$
```



---

## 启动类加载器

启动类加载器：主要负责加载 Java 核心类库（`java.lang` 包中的类），默认会加载 `jre/lib` 下的类文件信息。启动类加载器也是优先级最高的，这样做的好处主要是保证了 Java 程序的安全性。

如下是通过 启动类加载器 加载用户 jar 包：

> 代码如下：
>
> BootStrapClassLoader：01-chapter/BootStrapClassLoader/src/main/java/cn/wickson/classloader/BootStrapClassLoader.java
>
> BootstrapClassLoaderDemo：01-chapter/src/main/java/cn/wickson/jvm/item04/BootstrapClassLoaderDemo.java

* 我们创建 BootStrapClassLoader 类并打包成 jar 包

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn@master/images/202403261728182.png" alt="image-20240326172858138" style="zoom:100%;float:left" />

* 我们通过 启动类加载器 加载 `BootStrapClassLoader-1.0-SNAPSHOT.jar` 包，使用 `-Xbootclasspath/a:/jar包目录/jar包名` 进行扩展

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn@master/images/202403261631341.png" alt="image-20240326163123295" style="zoom:100%;float:left" />

* 打印结果

```
 Initial BootStrapClassLoader ClassLoader 
clazz = class cn.wickson.classloader.BootStrapClassLoader
```



---

## 扩展类加载器

扩展类加载器：主要使 java 平台具备了更好的 **模块化** 和 **扩展性**。总的来说，扩展类加载器的存在补充了启动类加载器的功能。例如，Java的服务提供者接口（Service Provider Interface，SPI）机制。







---

## 应用类加载器











