

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Fusion extends Main {

    private int n, c;
    private int[][] grid;
    private ArrayList<Integer> removable;
    private ArrayList<String> passed;
    public boolean state = false;
    public int score, choice,row,col;


    public void input_num() {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nWhat's the size of the grid N x N: ");
            n = scanner.nextInt();

        } catch (Exception p) {
            System.out.println("Only integers please\n");
            input_num();
        }

    }


    public void input_color() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("How many colors do you wanna play with 2-9: ");
            c = scanner.nextInt();

            if (c < 2 || c > 9) {
                throw new Exception();
            }

        } catch (Exception a) {
            System.out.println("The Value must be between 2 and 9");
            input_color();
        }
    }

    public void set_grid() {

        Random random = new Random();
        grid = new int[n][n];

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[row].length; col++) {
                 grid[row][col] = random.nextInt(c)+1;

            }


        }

    }

    public void get_grid() {
        System.out.print("    ");
        for (int j = 0; j < grid.length; j++) {

            System.out.print(" "+j);

        }
        System.out.println();
        System.out.print("    ");
        for (int j = 0; j < grid.length; j++) {

            System.out.print(" ▼");

        }
        System.out.println("\n");

        int i = 0;
        for (int row = 0; row < grid.length; row++) {
            System.out.print(i + " ▶  ");

            for (int col = 0; col < grid[row].length; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
            i++;
        }

    }

    public boolean gameOver(int pos1, int pos2) {


        if (pos2 < grid.length - 2) {
            if (grid[pos1][pos2] == grid[pos1][pos2 + 1]) {

                if (grid[pos1][pos2] == grid[pos1][pos2 + 2]) {

                    state = true;
                    return state;
                }
            }

        }

        if (pos1 < grid.length - 2) {
            if (grid[pos1][pos2] == grid[pos1 + 1][pos2]) {
                if (grid[pos1][pos2] == grid[pos1 + 2][pos2]) {
                    state = true;
                    return state;
                }
            }

        }
        state = false;
        if (!state) {
            if (pos2 < grid.length - 1 && pos1 < grid.length) {
                pos2 += 1;

                gameOver(pos1, pos2);
            } else if (pos2 == grid.length - 1 && pos1 < grid.length - 1) {
                pos1 += 1;
                pos2 = 0;

                gameOver(pos1, pos2);
            } else if (pos2 == grid.length - 1 && pos1 == grid.length - 1) {
                state = false;
            }
        }

        return state;

    }


    public int checkingHorizontal(int nrow, int ncol, int prev) {

        //This method sees if there are any colors next to the one chose, while it found the same color next to the previous it will keep running and adding 1
        //In the case there are none of the same color next to the one chosen it will return 0

        if (nrow < 0 || nrow >= grid.length) {
            return 0;
        } else if (ncol < 0 || ncol >= grid.length) {
            return 0;
        } else if (grid[nrow][ncol] != prev) {
            return 0;
        } else if (passed.contains(nrow + "," + ncol)) {
            return 0;
        } else {

            removable.add(nrow);
            removable.add(ncol);
            passed.add(nrow + "," + ncol);
            return checkingHorizontal(nrow, ncol + 1, prev) + checkingHorizontal(nrow, ncol - 1, prev) + 1;
        }
    }

    public int checkingVertical(int nrow, int ncol) {
        Random random = new Random();
        int baixo = 0, cima = 0, counter = 0;
        while(nrow < grid.length-1 && grid[nrow][ncol] == grid[nrow+1][ncol]){
            baixo += 1;
            nrow += 1;
        }
        while(nrow > 0 && grid[nrow][ncol] == grid[nrow-1][ncol]){
            cima += 1;
            nrow -= 1;

        }
        if(cima>1){
            counter = 1;
            while(counter <= cima){

                counter += 1;
            }
        }

        while(nrow > 0 && cima > 1){
            grid[nrow+cima][ncol] = grid[nrow-1][ncol];
            nrow -= 1;

        }
        if(nrow == 0){
            while(cima > 0){
                grid[nrow+cima][ncol] = random.nextInt(c)+1;
                cima -= 1;
            }

        }
        return counter;

    }

    public void pop(int nrow, int ncol) {
        Random random = new Random();
        removable = new ArrayList<>(n * n);
        passed = new ArrayList<>(n * n);
        int check = checkingHorizontal(nrow, ncol, grid[nrow][ncol]);
        int a;
        int b;
        int counter = 0;
        try {
            if (check >= 3) {
                for (int i = 0; i < removable.size(); i++)
                    if (i % 2 == 0) {
                        a = removable.get(i);
                        b = removable.get(i + 1);

                        while (a > 0) {
                            grid[a][b] = grid[a - 1][b];
                            a -= 1;
                        }

                        if (a == 0) {
                            grid[a][b] = random.nextInt(c) + 1;
                        }
                        counter += 1;
                    }
            }
            counter += checkingVertical(nrow,ncol);
            score += Math.pow(counter, 2);
        }
        catch (Exception e){
            selection();
        }

    }
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("------- Menu ------\n1- Play The Game\n2- Exit");
            System.out.print("Choose your option: ");
            choice = scanner.nextInt();
            if (choice != 1 && choice != 2 ){
                throw new Exception();
            }
        }
        catch (InputMismatchException f){
            System.out.println("Only Integers 1 or 2");
            menu();

        }
        catch (Exception e){
            System.out.println("That's not an option");
            menu();
        }

    }

    public void selection(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("\nRow: ");
            row = scanner.nextInt();
            if (row >= grid.length) {
                throw new Exception("The row you selected isn't in the grid");
            }
            System.out.print("Col: ");
            col = scanner.nextInt();
            if (col >= grid.length) {
                throw new Exception("The col you selected isn't in the grid");
            }

        } catch (InputMismatchException j) {
            System.out.println("Please insert only integers");
            selection();


        } catch (Exception g) {
            System.out.println(g.getMessage());
            selection();


        }

    }


    public void runner() throws IOException{
        Scanner scanner = new Scanner(System.in);
        menu();

        if (choice == 1) {
            input_num();
            input_color();
            set_grid();
            gameOver(0, 0);
            while (!state) {
                set_grid();
                gameOver(0, 0);
            }
            while (state) {
                get_grid();
                selection();
                pop(row, col);
                System.out.println("\nScore: " + score);
                System.out.println();
                gameOver(0, 0);

            }

            if (!state) {
                System.out.println("\nThere are no more possible plays");
                runner();
            }

        }

        else if (choice == 2) {
            System.out.println("Thanks for playing");
            System.exit(0);
        }

    }

}