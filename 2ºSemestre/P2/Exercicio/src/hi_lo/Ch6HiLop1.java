/*
    Introduction to OOP with Java (5th Ed), McGraw-Hill

    Wu/Otani
    
    Chapter 6 Sample Development: Hi Lo Game

    File: Step1/Ch6HiLo.java

*/

import java.util.*;


class Ch6HiLop1 {

    private static enum Response {YES, NO}
    
    private Scanner scanner;
    
    //Main Method
    public static void main (String[] args) {
        Ch6HiLop1 hiLo = new Ch6HiLop1( );
        hiLo.start();
    }
    
//--------------------------------
//  Constructors
//--------------------------------

    // Default constructor.
    public Ch6HiLop1( ) {
        
        scanner = new Scanner(System.in);        
    }

//-------------------------------------------------
//    Public Methods:
//
//      void    start    (    )
//
//------------------------------------------------

    /**
     * Top level method that calls other private methods
     * to play the Hi-Lo games.
     */
    public void start ( ) {
        Response answer;

        describeRules();

        answer = prompt("Do you want to play a Hi-Lo game?");

        while (answer == Response.YES) {

            generateSecretNumber( );

            playGame();

            answer = prompt("Do you want to play another Hi-Lo game?");
        }
        
        System.out.println("Thank you for playing Hi-Lo.");
  }

//-------------------------------------------------
//    Private Methods:
//
//    void    describeRules         (        )
//    void    generateSecretNumber  (        )
//    void    playGame              (        )
//    int     prompt                (String  )
//
//------------------------------------------------

    // Provides a brief explanation of the program to the user.
    private void describeRules( ) {
        System.out.println("Inside describeRules");         //TEMP
    }

    // Generates a random number between 1 and 100.
    private void generateSecretNumber( ) {
        System.out.println("Inside generateSecretNumber");   //TEMP
    }

    // Plays one Hi-Lo game.
    private void playGame( ) {

        System.out.println("Inside playGame");              //TEMP
    }

    
    private Response prompt(String question) {

        String input;

        Response response = Response.NO;

        System.out.print(question + " (Yes - y; No - n): ");

        input = scanner.next();

        if (input.equals("Y") || input.equals("y")) {
            response = Response.YES;
        }

        return response;
    }
}
