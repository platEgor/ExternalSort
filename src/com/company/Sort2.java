package com.company;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Sort2 {

    public static void sortInd(RandomAccessFile f, int[] ind) throws IOException {
        int x,y,k,t;
        for(int i = 0; i < ind.length-1; i+=1){
            f.seek((ind[i]+1)*10 + ind[i]*4);
            x = f.readInt();
            //System.out.println("x = " + x);
            k=i;
            for(int j = i+1; j < ind.length; j+=1){
                f.seek((ind[j]+1)*10+ind[j]*4);
                y = f.readInt();
                //System.out.println("y = " + y);
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
        int n = 5;
        Person[] people = new Person[n];
        RandomAccessFile d = new RandomAccessFile("2.txt", "rw");
        for(int i = 0; i < people.length-1; i++){
            people[i] = new Person(String.valueOf((i+1)*11), 5-i);
            int j=0;
//            while(people[i].n[j]!=(char)0) System.out.print(people[i].n[j++]);
//            System.out.println();
//            System.out.print("Name: ");
//            System.out.print(people[i].n);
//            System.out.println(" Age: " + people[i].c);
            for(j=0; j < 10; j++){
                d.write(people[i].n[j]);
            }
            d.writeInt(people[i].c);

        }
        int[] ind = new int[n];
        for(int i = 0; i < ind.length; i++){
            ind[i]=i;
            //System.out.printf("%4d",i);
        }
        System.out.println();
        //for(var i=0; i<ind.length;i++) System.out.printf("%4d", d.read());
        sortInd(d, ind);
        for (int j = 0; j < ind.length; j++) {
            System.out.printf("%4d", ind[j]);
        }
        System.out.println();
        for(var i=0; i<ind.length;i++) {
            d.seek((ind[i]+1)*10 + ind[i]*4);
            System.out.printf("%4d", d.readInt());
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