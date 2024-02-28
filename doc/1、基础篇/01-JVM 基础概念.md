## JVM 解决了什么问题？

JVM（Java Virtual Machine）解决的核心问题之一就是跨平台性，只需要写一份代码即可运行在多个操作系统上。

如下图所示，首先我们将编写好的 `.java` 文件通过 `javac` 编译为 `.class` 字节码文件，然后通过 `JVM` 解释为操作系统能够识别的机器码语言。

![image-20240227205401151](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402272054209.png)



## JVM 是什么？

`JVM（Java Virtual Machine）`：全称为 `Java虚拟机`，JVM主要的作用就是将 `Java` 程序编译成的 `字节码文件` 解释或编译为操作系统能够识别的 `机器码指令`。

![image-20240227210933236](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402272109284.png)



## JVM 核心功能

1. **字节码执行**：JVM负责解释或编译Java程序编译生成的字节码，并将其转换为本地机器码执行。

2. **内存管理**：JVM负责管理Java程序的内存分配和释放。

   * **C内存管理示例**

   ```java
   #include <stdio.h>
   #include <stdlib.h>
   
   int main() {
       // 使用malloc动态分配内存
       int *ptr = (int *)malloc(sizeof(int));
       if (ptr == NULL) {
           printf("内存分配失败\n");
           return 1;
       }
       
       *ptr = 5;
       printf("指针所指向的值为: %d\n", *ptr);
       
       // 释放动态分配的内存
       free(ptr);
       
       return 0;
   }
   ```

   * **Java内存管理示例：**

   ```java
   public class MemoryManagementExample {
       public static void main(String[] args) {
           // 使用Java的自动内存管理机制
           Integer number = new Integer(5);
           
           System.out.println("对象的值为: " + number);
           // Java的垃圾回收器会自动回收不再使用的对象所占用的内存
       }
   }
   ```

3. **即时编译**：JVM中包含**即时编译器（Just-In-Time Compiler，JIT Compiler）**，它能够将字节码动态编译成本地机器码，以提高程序的执行效率。

   ![image-20240227212826118](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402272128171.png)



## JVM 的组成

Java虚拟机（JVM）是一个复杂的系统，由多个组件组成，主要包括以下几个部分：

1. **类加载器子系统（Class Loader Subsystem）**：
   * 核心功能：负责加载Class文件，将其转换为JVM内部表示的对象，并加载到内存中。
   * 组成部分：**启动类加载器（Bootstrap Class Loader）**、**扩展类加载器（Extension Class Loader）**和 **应用程序类加载器（Application Class Loader）**
2. **运行时数据区（Runtime Data Area）**
   * 核心功能：JVM内存管理的主要区域，比如对象的创建和销毁。
   * 组成部分：**方法区（Method Area）**、**堆（Heap）**、**虚拟机栈（Java Virtual Machine Stacks）**、**本地方法栈（Native Method Stack）**
   * **方法区（Method Area）**：存储类的结构信息、静态变量、常量、方法字节码等数据。
   * **堆（Heap）**：存储所有对象实例以及数组对象。
   * **虚拟机栈（Java Virtual Machine Stacks）**：存储线程的方法调用栈、局部变量等信息。
   * **本地方法栈（Native Method Stack）**：与虚拟机栈类似，但是用于执行本地方法。
3. **执行引擎（Execution Engine）**
   * 核心功能：负责执行JVM加载的字节码，同时使用即时编译优化性能。
   * 组成部分：**解释器（Interpreter）**和 **即时编译器（Just-In-Time Compiler，JIT Compiler）**。
   * **解释器（Interpreter）：**逐条解释字节码并执行。
   * **即时编译器（Just-In-Time Compiler，JIT Compiler）：**将字节码编译成本地机器码以提高执行效率。
4. **本地方法接口（Native Interface）**
   * 核心功能：允许Java应用程序调用本地方法（Native Method），即使用C或C++等编程语言编写的方法。

具体 JVM 组成如下图所示

![image-20240228211308931](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402282113106.png)



## JDK、JRE、JVM 有什么区别

**JDK（Java Development Kit）**

* `JDK` 包含了完整的 `Java` 运行环境（`JRE`）
* `JDK` 是 `Java` 开发工具包，提供了用于开发 `Java` 应用程序的工具和资源，包括编译器（`javac`）、运行时库（`Java`标准库）、调试器（`jdb`）、文档生成工具（`javadoc`）等。

**JRE（Java Runtime Environment）**

* `JRE` 是 `Java` 运行时环境，包含了 `Java` 程序运行所需的核心类库、`Java` 虚拟机（`JVM`）和其他运行时所需的支持文件。

**JVM（Java Virtual Machine）**

- `JVM` 是 `Java` 虚拟机，是 `Java` 程序的运行环境，负责解释和执行 `Java` 程序编译生成的字节码。
- `JVM` 提供了内存管理、线程管理、安全检查、即时编译等功能，使得 `Java` 程序能够在不同的操作系统和硬件平台上运行，实现了 `Java` 的跨平台性。

`JDK` 包含了 `JRE` 以及用于开发 `Java` 应用程序的工具，而JRE包含了 `JVM` 以及运行 `Java` 程序所需的核心类库。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202402272151189.png" alt="jdl, jre, jvm" style="zoom:70%;float:left" />