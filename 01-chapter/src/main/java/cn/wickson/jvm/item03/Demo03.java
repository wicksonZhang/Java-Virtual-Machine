package cn.wickson.jvm.item03;

/**
 * @author ZhangZiHeng
 * @date 2024-03-21
 */
public class Demo03 {

    public static void main(String[] args) {
        // new B02();
        System.out.println("B02.a = " + B02.a);
    }

}

class A02 {
    static int a;

    static {
        a = 1;
    }
}

class B02 extends A02 {
    static {
        a = 2;
    }
}