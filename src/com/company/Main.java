package com.company;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
            //swap(ind[k], ind[i])
            t=ind[k];
            ind[k]=ind[i];
            ind[i]=t;
        }
    }

    public static void main(String[] args) throws IOException {
        Person[] people = new Person[20];

        int n = 20;
        RandomAccessFile d = new RandomAccessFile("1.txt", "rw");
        for(int i = 0; i < people.length-1; i++){
            people[i] = new Person(String.valueOf((i+1)*11), (int) (Math.random()*20));
            int j=0;
            while(people[i].n[j]!=(char)0) System.out.print(people[i].n[j++]);
            System.out.println();
//            System.out.print("Name: ");
//            System.out.print(people[i].n);
//            System.out.println(" Age: " + people[i].c);
            for(j=0; j < 10; j++){
                d.write(people[i].n[j]);
            }
            d.writeInt(people[i].c);

        }
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

        for (int j : ind) {
            System.out.printf("%4d", j);
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

    char[] n = new char[10];
    int c;

    Person(String N, int C){
        for(int i = 0; i < N.length(); i++) n[i] = N.charAt(i);
        c=C;
    }
}