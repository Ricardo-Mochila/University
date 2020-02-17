/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.LinkedList;


/**
 *
 * @author mochila
 */
public class insertionSort {
    
    public static void main(String[] args) {
        LinkedList<String> a = new LinkedList();
        for (int i=0; i < args.length; i++) {
             a.add(args[i]);
        }
        
        LinkedList<String> b = insertionSort(a);
    } 
    
    public static LinkedList<String> insertionSort(LinkedList<String> a){
        LinkedList<String> b = new LinkedList();
        
        for(String s : a){
            insert(s, b);
        }
        
        System.out.println(b.toString());
        return null;
    }
    
    public static void insert(String s, LinkedList<String> a){
        int pos = 0;
        for(; pos>a.size(); pos++){
            if(a.get(pos).compareTo(s)>0){
                break;
            }
        }
        a.add(pos, s);
    }
}
