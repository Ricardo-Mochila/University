
import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Fusion1Graphics extends Main1Graphics {

     //Every global variable that's need for the aplication //
    //                                                     //

    private int n, c;
    private int[][] grid;
    private ArrayList<Integer> removable;
    private ArrayList<String> passed;
    public boolean state = false;
    public int score, rowIndex, columnIndex;
    public JButton jbutton = new JButton();
    JFrame jframe = new JFrame();


    public void input_num() {

        // This method ask's for an input about the size of the grid

        try {
            JOptionPane pergunta = new JOptionPane();
            n = Integer.parseInt(pergunta.showInputDialog(null, "What's the size of the grid N x N: \nBetween 3-50"));
            if (n < 3 || n>50){
                throw new Exception(); // If the user tries to input values lower then 3 or bigger then 50, the program generates an Exception and
            }                         //  while the values aren't between the expected it will keep asking the user for the a new input

        } catch (Exception e) {
            input_num();        // It will call the method again in case of an exception
        }

    }


    public void input_color() {

        // This method ask's for an input about the amount of colors in the grid

        try {
            JOptionPane pergunta1 = new JOptionPane();
            c = Integer.parseInt(pergunta1.showInputDialog(null, "How many colors do you wanna play with 2-9: "));

            if (c < 2 || c > 9) {
                throw new Exception(); // If the user tries to input values lower then 2 or bigger then 9, the program generates an Exception and
            }                         //  while the values aren't between the expected it will keep asking the user for the a new input

        } catch (Exception e) {
            input_color();      // It will call the method again in case of an exception
        }
    }

    public void set_grid() {

        Random random = new Random();      //This method generates a grid with a bidimensional array n x n, n being the number that the user chose
        grid = new int[n][n];             //in the first JOptionPane

        jframe.setTitle("Fusion");
        jframe.setVisible(true);
        jframe.setSize(800, 800);

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = random.nextInt(c) + 1; //Add a random value between 1 and c, c being the value that the user chose in the second JOptionPane
            }
        }
    }


    public void get_grid(){


        //This method creates a jframe and and creates buttons with a specific color
        JMenu jmenu = new JMenu("Score: " + String.valueOf(score));

        JMenuBar jmenubar = new JMenuBar();
        jmenubar.add(jmenu);
        jframe.setJMenuBar(jmenubar);


        jframe.setLayout(new GridLayout(n, n, 8, 8));
        int i = 0;
        for (int row = 0; row < grid.length; row++) {
            System.out.print(i + " ▶  ");

            for (int col = 0; col < grid[row].length; col++) {
                System.out.print(grid[row][col] + " ");
                button(row,col);
            }
            System.out.println();
            i++;
        }


    }


    public void button(int row, int col){

        //This Method creates a button with the capacity of a mouse click, the button as a specific color

        jbutton = new JButton();

        jframe.add(jbutton);
        jbutton.setOpaque(true);
        jbutton.setBorderPainted(false);
        graphics(grid[row][col]);

        jbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rowIndex = row ;
                columnIndex = col;
                System.out.println("rowIndex " + rowIndex + " columnIndex " + columnIndex);
                pop(rowIndex,columnIndex);

            }

        });

    }


    public void graphics(int numeros) {

        //This method give the color to the buttons

        if (numeros == 1) {
            jbutton.setBackground(Color.white);
        } else if (numeros == 2) {
            jbutton.setBackground(Color.red);
        } else if (numeros == 3) {
            jbutton.setBackground(Color.blue);
        } else if (numeros == 4) {
            jbutton.setBackground(Color.cyan);
        } else if (numeros == 5) {
            jbutton.setBackground(Color.yellow);
        } else if (numeros == 6) {
            jbutton.setBackground(Color.green);
        } else if (numeros == 7) {
            jbutton.setBackground(Color.pink);
        } else if (numeros == 8) {
            jbutton.setBackground(Color.orange);
        } else if (numeros == 9) {
            jbutton.setBackground(Color.magenta);
        }

    }


    public boolean gameOver(int pos1, int pos2) {

        //This method looks at all the positions, if the are 3 of the same color next to each other in the same row or col, it will return true
        //In case there are no possible plays it will retrun false

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

        //This method sees if there are 3 of the same color next to each other, if there are, it will remove them and give them the color of the one that's on top
        //In case there are no colors on top of the one that was removed, it will create a random color between 1 and c, and sees if there more possible plays calling
        //The gameOver function already explained before
        Random random = new Random();

        removable = new ArrayList<>(n * n);
        passed = new ArrayList<>(n * n);
        int checkerH = checkingHorizontal(rowIndex, columnIndex, grid[rowIndex][columnIndex]);
        int a;
        int b;
        int counter = 0;
        int conta = 0;
        if (checkerH >= 3) {
            System.out.println(removable);
            for (int i = 0; i < removable.size(); i++) {
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
        }

        counter += checkingVertical(nrow,ncol);
        score += Math.pow(counter, 2);
        jframe.getContentPane().removeAll();
        if(state) {
            gameOver(0,0);
            get_grid();

        }
        else {
            JOptionPane.showMessageDialog(null,"There are no more possible plays\n"+"Score: "+score);

            System.exit(0);
        }


    }

}
