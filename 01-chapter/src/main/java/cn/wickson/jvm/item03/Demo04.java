package cn.wickson.jvm.item03;

/**
 * @author ZhangZiHeng
 * @date 2024-03-21
 */
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
