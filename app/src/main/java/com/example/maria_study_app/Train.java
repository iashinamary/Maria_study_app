package com.example.maria_study_app;

public class Train {

    final Object monitor = new Object();
    volatile int num = 0;
    volatile int[] arr;


    public void atomic(){
        synchronized (monitor) {
            arr = new int[]{1};
        }
    }

    public void nonAtomic(){
        synchronized (monitor) {
            arr = new int[1];
            arr[0] = 5;
        }
    }

    public void ff(){
        synchronized (monitor) {
            for (int i = 0; i < arr.length; i++) {

            }
        }
    }
}
