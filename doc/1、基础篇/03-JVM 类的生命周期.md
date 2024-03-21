## 内容大纲

> 本章节示例代码：https://github.com/wicksonZhang/Java-Virtual-Machine/tree/main/01-chapter/src/main/java/cn/wickson/jvm/item03

![03-类的生命周期](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn@master/images/202403211139100.png)

---

## JVM-类的生命周期

在 JVM 中类的生命周期主要分为 **加载、连接、初始化、使用和卸载** 这五个部分，这五个部分具体内容如下：

* **加载阶段**：主要将类字节码文件加载进 JVM 中。
* **连接阶段**：这个部分主要包括验证、准备和解析。
* **初始化阶段**：JVM 执行类的初始化代码，这些代码通常包括 静态变量赋值 和 静态代码块。
* **使用**：类加载完成并经过初始化后，就可以被应用程序使用。
* **卸载**：当类加载器不再需要某个类时，会触发类卸载过程。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn@master/images/202403191548149.png" alt="image-20240319154818102" style="zoom:100%;float:left" />

---



## JVM-类的加载阶段

在类加载阶段主要做的工作是将类的字节码数据加载到 JVM 中，具体内容如下：

- **查找对应加载类的字节码文件**：类加载器负责根据类的全限定名去文件系统、网络等地方查找对应的字节码文件

- **类加载器加载完成之后，会分配内存空间给字节码文件**

- **字节码文件的信息会保存到方法区中**：类加载器加载类后，会将类的元数据信息存储到方法区中，包括类的结构信息、字段信息、方法信息等。

   <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202403202214184.png" alt="image-20240320221457096" style="zoom:80%;float:left" />

- **JVM 在堆中会创建一个与方法区中的类元数据相对应的 `java.lang.Class` 对象**：这个类会包含运行时状态、方法的字节码等。这个对象是 Java 反射机制的基础.

   <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202403202217397.png" alt="image-20240320221721340" style="zoom:80%;float:left" />



---

## JVM-类的连接阶段

JVM 连接阶段主要分为了三个部分，分别是 验证、准备、解析，具体内容如下：

* **验证**：这个阶段主要判断字节码文件是否满足 JVM 规范，例如文件格式验证、元数据验证等等
* **准备**：这个阶段主要是给类的静态变量分配内存空间并设置初始值，例如数值类型的默认值为 0 ，引用类型的默认值为 null。
* **解析**：这个阶段主要是将符号引用转化为直接引用的过程，例如将类、方法和字段的符号引用转化为内存地址或偏移量。

### 验证

验证阶段是确保加载的字节码文件符合 JVM 规范的过程：

* 文件格式校验，是否是以 **0XCAFEBABE** 开头

  <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402282232496.png" alt="image-20240228223233468" style="zoom:100%;float:left" />
  
* 元信息验证，例如所有的类必须有父类

  <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202403202255512.png" alt="image-20240320225510481" style="zoom:100%;float:left" />
  
* 程序指令校验，例如方法内的指令执行到一般强制跳到其他方法中

* 符号引用验证，例如是否访问了其他类的 **private** 方法 

  > 符号引用和直接引用：https://blog.csdn.net/weixin_42447959/article/details/81675512



### 准备

准备阶段是为类的静态变量分配内存空间并设置初始值的过程，例如每一种数据类型都有对应的默认值。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202403202301553.png" alt="image-20240320230101504" style="zoom:70%;float:left" />



### 解析

解析阶段是将类的符号引用转化为直接引用的过程。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202403202303082.png" alt="image-20240320230345045" style="zoom:100%;float:left" />

---



## JVM-类的初始化阶段

初始化阶段是执行类的**初始化代码（静态变量赋值和静态代码块）**的过程。

* 初始化阶段会执行 **静态代码块中的代码**，并**为静态变量赋值**，执行顺序与代码顺序一致。

如下是代码中通过 `jclasslib` 编译的一段代码，具体信息如下：

* [0] <init> : 构造方法
* [1] main：`Main` 方法
* [2] <clinit>：初始化阶段执行

![image-20240320233452568](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202403202334610.png)

| 字节码位置 | 指令         | 注释                                |
| ---------- | ------------ | ----------------------------------- |
| 0          | iconst_1     | 将 int 型常量值 1 推送至栈顶        |
| 1          | putstatic #2 | 将栈顶 int 型数值存入指定类的静态域 |
| 4          | iconst_2     | 将 int 型常量值 2 推送至栈顶        |
| 5          | putstatic #2 | 将栈顶 int 型数值存入指定类的静态域 |
| 8          | return       | 从当前方法返回 void                 |



### 类的初始化

如下几种方式会导致类的初始化：

1. 访问一个类的静态变量或者静态方法。
2. 调用 Class.forName(String className)。
3. new 一个该类的对象。
4. 执行 main() 的当前类



如下面试题信息，包含了上诉的这种情况

```java
public class Demo02 {

    public static void main(String[] args) {
        System.out.print("A");
        new Demo02();
        new Demo02();
    }

    public Demo02() {
        System.out.print("B");
    }

    {
        System.out.print("C");
    }

    static {
        System.out.print("D");
    }
}
```

* 打印的结果信息如下

```txt
DACBCB
```



### 注意事项

在某些特定情况下并不会导致初始化指令的执行，具体内容如下：

1. 无静态代码块且无静态变量赋值语句。
2. 有静态变量的声明，但是没有赋值语句。
3. 静态变量的定义使用 final 关键字，这类变量会在准备阶段直接进行初始化。

如下面试题信息，包含了上诉的这种情况

```java
public class Demo03 {

    public static void main(String[] args) {
        new B02();
        System.out.println("B02.a = " + B02.a); // 2
    }

}

class A02 {
    static int a = 0;

    static {
        a = 1;
    }
}

class B02 extends A02 {
    static {
        a = 2;
    }
}
```

我们修改如下内容

```java
public class Demo03 {

    public static void main(String[] args) {
        // new B02();
        System.out.println("B02.a = " + B02.a); // 1
    }

}

class A02 {
    static int a;

    static {
        a = 1;
    }
}

class B02 extends A02 {
    
    // 无静态变量赋值语句
    
    static {
        a = 2;
    }
}
```



## 练习题

提示内容如下：

* 静态变量的定义使用 final 关键字，这类变量会在准备阶段直接进行初始化。
* 访问一个类的静态变量或者静态方法会导致类的初始化。

```java
public class Demo04 {

    public static void main(String[] args) {
        System.out.println(Demo04_A.a);
    }

}

class Demo04_A {
    
    public static final int a = Integer.valueOf(1);

    static {
        System.out.println("init Demo04_A ");
    }
    
}
```

