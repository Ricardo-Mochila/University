/*
    Introduction to OOP with Java (5thEd), McGraw-Hill

    Wu/Otani
    
    Chapter 6 Sample Development: Hi Lo Game (Step 4)

    File: Step4/Ch6HiLo.java

*/

/*
 * This source file is from Step 3. The tasks to be completed
 * in Step 4 are left as a Chapter exercise.
 */

import java.util.*;

class Ch6HiLop4 {

    private static enum Response {YES, NO}
    
    //Max number of guesses allowed 
    private final int MAX_GUESS_ALLOWED = 6;

    //The lower bound for the guess 
    private final int LOWER_BOUND = 1;

    // The upper bound for the guess 
    private final int UPPER_BOUND = 100;
    
    private Scanner scanner;

    // computer generated secret number 
    private int secretNumber;
    
    
    //Main Method
    public static void main (String[] args) {
        Ch6HiLop4 hiLo = new Ch6HiLop4( );
        hiLo.start();
    }
    
    
//--------------------------------
//  Constructors
//--------------------------------

    // Default constructor.
    public Ch6HiLop4( ) {
        
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
        
       /*
         Implementation of this method is left as an exercise.

         Display the game rules only if the user wants to see them.        
       */
    }

    // Generates a random number between 1 and 100.
    private void generateSecretNumber( ) {

        double X = Math.random();

        secretNumber = (int) Math.floor(X*100) + 1;

        System.out.println("Secret Number: " + secretNumber); //TEMP
    }

    //  Gets the next guess from the user
    private int getNextGuess( ) {

        int    input;

        while (true) {
            System.out.print("Next Guess: ");
            input = scanner.nextInt();

            if (LOWER_BOUND <= input && input <= UPPER_BOUND) {
                return input;
            }

            //invalid input; print error message
            System.out.println("Invalid Input: " +
                               "Must be between " + LOWER_BOUND +
                               " and " + UPPER_BOUND);
        }
    }
    
    
    // Plays one Hi-Lo game.
    private void playGame( ) {

        int guessCount = 0;
        int guess;

        do {
            //get the next guess
            guess = getNextGuess();

            guessCount++;

            //check the guess
            if (guess < secretNumber) {

              System.out.println("Your guess is LO");

            } else if (guess > secretNumber) {

                System.out.println("Your guess is HI");
            }

        } while ( guessCount < MAX_GUESS_ALLOWED &&
                  guess != secretNumber );

        //output appropriate message
        if ( guess == secretNumber ) {

            System.out.println("You guessed it in " + guessCount  + " tries.");
            
        } else {

            System.out.println("You lost. Secret No. was " + secretNumber);
        }
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
