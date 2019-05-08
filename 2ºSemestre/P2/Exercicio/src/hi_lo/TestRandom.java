/*
 
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani
    
    Chapter 6 Sample Development: HiLo Game (Step 3)

    File: Step3/TestRandom.java

*/

/*
  Test the random number generation routine for the
  HiLo program. This test program generates 1000
  random numbers between 1 and 100. If a number
  outside of the range is generate, then the
  program terminates immediately. This abnormal
  termination is never expected to occur.
 */

import java.util.*;

class TestRandom {

    public static void main ( String args[] ) {

        int    N = 1000, count = 0, number;
        double X;
        
        Random random = new Random();

        //Experiment by increasing the value for N. You can try
        //1000, 10000, 10000, 100000, 1000000, ...
        //With N == 1000, the program terminates with the "Okay"
        //message instantaneously (from a human's perspective).
        //As you increase the value, the program takes longer
        //and longer time till termination.

        do {

            count++;

            number = random.nextInt(100) + 1;

        } while ( count < N &&
                  1 <= number && number <= 100 );

        if ( number < 1 || number > 100 ) {
            System.out.println("Error: " + number);

        } else {
            System.out.println("Okay");
        }
    }
}