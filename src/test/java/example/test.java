package example;

import java.util.Calendar;
import java.util.List;
public class test {

    public static void main(String[] args) {




//        Thread tt = new testnode();
//        tt.setName("tt");
//        tt.start();
//
//        try {
//            for (int i = 0; i < 10; i++) {
//                System.out.println(Thread.currentThread().getName() +"--"+Calendar.getInstance().getTime()+ "---" + i);
//                Thread.sleep(500);
//
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("kkkllal");


    }
}

    class testnode extends Thread {
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "---" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }