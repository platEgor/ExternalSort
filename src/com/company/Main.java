package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

    public static void sortInd(RandomAccessFile f, int[] ind) throws IOException {
        int x,y,k,t;
        for(int i = 0; i < ind.length-1; i++){
            f.seek(ind[i]);
            x = f.readByte();
            k=i;
            for(int j = i+1; j < ind.length; j++){
                f.seek(ind[j]);
                y = f.readByte();
                if(y<x){
                    k=j; x=y;
                }
            }
            t=ind[k];
            ind[k]=ind[i];
            ind[i]=t;
        }
    }

    public static void main(String[] args) throws IOException {
        Person[] people = new Person[20];
        for(int i = 0; i < people.length; i++){
            people[i] = new Person(String.valueOf(i), (int) (Math.random()*20));
            System.out.printf("%s %4d \n", people[i].n, people[i].c);
        }
        int n = 20;
        RandomAccessFile d = new RandomAccessFile("1.txt", "rw");
        int[] ind = new int[n];
        for(int i = 0; i < ind.length; i++) d.write((int)Math.round(65+ Math.random()*25));
        for(int i = 0; i < ind.length; i++){
            ind[i]=i;
            System.out.printf("%4d",i);
        }
        System.out.println();
        d.seek(0);
        for(var i=0; i<ind.length;i++) System.out.printf("%4d", d.read());
        System.out.println();
        sortInd(d, ind);

        for(int i = 0; i < ind.length; i++){
            System.out.printf("%4d",ind[i]);
        }
        System.out.println();
        for(var i=0; i<ind.length;i++) {
            d.seek(ind[i]);
            System.out.printf("%4d", d.read());
        }
        d.close();
    }
}
class Person{
    String n;
    int c;
    Person(String N, int C){
        n=N;
        c=C;
    }
}