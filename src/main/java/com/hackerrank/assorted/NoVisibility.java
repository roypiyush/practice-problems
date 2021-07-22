package com.hackerrank.assorted;

public class NoVisibility {
    private static boolean ready;
    private static int number;

    public static void main(String[] args) throws InterruptedException {
        ready = true;
        new ReaderThread().start();
        number = 42;

    }

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

}
