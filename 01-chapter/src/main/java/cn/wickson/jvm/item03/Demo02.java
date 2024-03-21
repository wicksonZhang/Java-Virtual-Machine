package cn.wickson.jvm.item03;

/**
 * @author ZhangZiHeng
 * @date 2024-03-21
 */
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
        System.out.println("C");
    }

    static {
        System.out.println("D");
    }
}
