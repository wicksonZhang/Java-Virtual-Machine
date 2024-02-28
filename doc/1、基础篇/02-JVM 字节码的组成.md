## 内容大纲

> 本章节示例代码：

本章节主要讲解的两个核心部分是：字节码的组成、字节码的常见工具。具体内容如下：



## 如何获取字节码文件

### 通过 `javac` 获取

例如我们首先将如下 `java` 代码通过指令 `javac` 编译成字节码文件，然后再通过 `Notepad++` 以 16 进制格式打开源文件。

* `Calculator.java` 源文件如下

```java
/**
 * Java To Class
 */
public class Calculator {
    private int result;

    public Calculator() {
        this.result = 0;
    }

    public void add(int a, int b) {
        this.result = a + b;
    }

    public void subtract(int a, int b) {
        this.result = a - b;
    }

    public int getResult() {
        return this.result;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.add(5, 3);
        System.out.println("Result of addition: " + calc.getResult());

        calc.subtract(10, 4);
        System.out.println("Result of subtraction: " + calc.getResult());
    }
}
```

* 通过 `javac` 进行编译 `Calculator.java`

```shell
D:\idea-project\Java-Virtual-Machine\01-chapter\src\main\java\cn\wickson\jvm\item01>dir /a /w
...
Calculator.java

D:\idea-project\Java-Virtual-Machine\01-chapter\src\main\java\cn\wickson\jvm\item01>javac Calculator.java

D:\idea-project\Java-Virtual-Machine\01-chapter\src\main\java\cn\wickson\jvm\item01>dir /a /w
...
Calculator.class   Calculator.java
```

* 通过 `Notepad++` 以 16 进制打开 `Calculator.class` 文件
  * 具体操作信息：[如何在notepad++中以16进制显示内容](https://blog.csdn.net/u010178611/article/details/88900412)

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402282232496.png" alt="image-20240228223233468" style="zoom:100%;float:left" />



### 通过 `jclasslib` 获取

> `jclasslib`：这个是 `Github` 上开发的一款查看字节码的插件。
>
> `GIthub`：https://github.com/ingokegel/jclasslib
>
> `Idea` 插件：Plugins --> Marketplace --> jclasslib

* Windows 本地展示

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402282241424.png" alt="image-20240228224134386" style="zoom:100%;float:left" />

* Idea 插件展示

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402282250188.png" alt="image-20240228225031165" style="zoom:100%;float:left" />



## 字节码组成部分

通过上面 `jclasslib` 的插件信息，我们可以看到字节码信息分为如下 6 个部分：

- 基本信息
- 常量池
- 接口
- 字段
- 方法
- 属性

### 基本信息

**基本信息**：主要包含 魔数、字节码文件对应的 java 版本号、访问标识、父类和接口。

* **魔数**：字节码文件的头 4 个字节，是一个固定的值：`ca fe ba be`。

* **主副版本号**：主副版本号指的是编译字节码文件的 `JDK` 版本号，主要的作用是判断当前字节码的版本和运行时的 JDK 是否兼容。
  * 如何使用：我们在 `主副版本号 - 44` 就是当前版本号。
  * 对应案例：[jdk版本冲突Unsupported major.minor version错误定位](https://developer.aliyun.com/article/128001)



### 常量池

**常量池**：常量池是一组常量的集合，包括字面值常量、符号引用、类和接口名等。



### 字段

**字段表（Field Table）**：描述类的字段，包括字段名称、类型、访问修饰符等。



### 方法

**方法表（Method Table）**：描述类的方法，包括方法名称、参数列表、返回类型、访问修饰符等。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402282315340.png" alt="image-20240228231520318" style="zoom:100%;float:left" />

* 对应的 java 代码

```java
    public void add(int a, int b) {
        this.result = a + b;
    }
```

* 对应字节码信息

| 字节码 | 指令                                                      | 注释                                                         |
| ------ | --------------------------------------------------------- | ------------------------------------------------------------ |
| 0      | aload_0                                                   | 将对象引用（this）加载到操作数栈                             |
| 1      | iload_1                                                   | 将第一个整数型本地变量加载到操作数栈，**对应 int a**         |
| 2      | iload_2                                                   | 将第二个整数型本地变量加载到操作数栈，**对应 int b**         |
| 3      | iadd                                                      | 将操作数栈顶的两个整数相加，并将结果压入操作数栈，**对应 a + b** |
| 4      | putfield #2 <cn/wickson/jvm/item01/Calculator.result : I> | 将操作数栈顶的整数值存储到Calculator类的result字段中，**对应 this.result =** |
| 7      | return                                                    | 返回方法                                                     |



### 属性

**属性表（Attribute Table）**：包含了各种附加信息，如代码行号、异常处理器、注解等。





## 阿里 Arthas