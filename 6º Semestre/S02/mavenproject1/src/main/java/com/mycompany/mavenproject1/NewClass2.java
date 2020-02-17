/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.Scanner;

/**
 *
 * @author mochila
 */
public class NewClass2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            int value = scanner.nextInt();
        }catch(Exception InputMismatchException ){
            System.out.println("You needed to insert an integer");
        }finally{
            System.out.println("Done");
        }
    }
}
