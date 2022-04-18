package com.company;

import java.io.File;
import java.io.RandomAccessFile;

public class Main {

    public static void sortInd(File f, int[] ind){

    }

    public static void main(String[] args) {
        int n = 20;
        RandomAccessFile d = new RandomAccessFile("1.dat", "rw");
        int[] ind = new int[n];
        for(int i = 0; i < ind.length; i++){
            ind[i]=i;
            System.out.printf("%4d",i);
        }
        for(int i = 0; i < ind.length; i++){
            d.write(Math.round(65+ Math.random()*25));
        }
        d.seek(0);
        for(var i=0; i<ind.length;i++){
            System.out.printf("%4d" d.read());
        }
        System.out.println();
        d.close();
    }
}
