package com.raiden.homework;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: Raiden
 * Date: 2019/6/13
 */
public class Test {
    static SimpleQueue queue=new SimpleQueue();
    public static void main(String[] args) {
        for (int i=0; i<20; i++) {
            new PutThread("p"+i, i).start();
            new TakeThread("t"+i).start();
        }
    }

    static class PutThread extends Thread {
        private int num;
        public PutThread(String name, int num) {
            super(name);
            this.num = num;
        }
        public void run() {
            try {
                Thread.sleep(100);
                queue.put(num);
                System.out.println(System.currentTimeMillis()+" Put:"+num);
            } catch (Exception e) {
            }
        }
    }

    static class TakeThread extends Thread {
        public TakeThread(String name) {
            super(name);
        }
        public void run() {
            try {
//                Thread.sleep(10);
                Integer num = (Integer)queue.take();
                System.out.println(System.currentTimeMillis()+" Take:"+num);
            } catch (Exception e) {
            }
        }
    }
}
