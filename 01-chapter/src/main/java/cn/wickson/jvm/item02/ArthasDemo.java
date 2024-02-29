package cn.wickson.jvm.item02;


public class ArthasDemo {

    public static void main(String[] args) throws InterruptedException {
        int index = 0;
        while (true) {
            index++;
            if (index % 5 == 0) {
                Thread.sleep(1000);
            }
        }
    }

}
