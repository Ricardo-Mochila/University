/*package fusion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

class FusionGraphic extends MainGraphics {

    private int n;
    private int[][] grid;
    private ArrayList<String> removable;
    private ArrayList<String> passed;

    public void table() {

        JOptionPane pergunta = new JOptionPane();
        n = Integer.parseInt(pergunta.showInputDialog(null, "What's the size of the grid N: "));

        JFrame jframe = new JFrame();
        jframe.setTitle("fusion");
        jframe.setVisible(true);
        jframe.setSize(800, 800);
        jframe.setLayout(new GridLayout(n, n, 3, 3));


        JButton jbutton = new JButton();


        Random random = new Random();
        grid = new int[n][n];



        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[row].length; col++) {

                JLabel btn = new JLabel();
                int numeros = (random.nextInt(8) + 2);
                btn.setText(numeros + "");
                jbutton = new JButton();

                jbutton.setPreferredSize(new Dimension(55, 55));
                jframe.add(jbutton);
                //jbutton.setOpaque(true);
                //jbutton.setBorderPainted(false);
                jbutton.add(btn);

                graphics(numeros, jbutton);

            }
            System.out.println();

        }

    }

    public void jogada(int nrow, int ncol, JButton jbutton){
        table();
        pop(nrow, ncol);

    }

    public void graphics(int numeros, JButton jbutton) {

        if (numeros == 2) {
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

    public void pop(int nrow, int ncol) {

        removable = new ArrayList<>(n * n);
        passed = new ArrayList<>(n * n);
        int checker = checking(nrow, ncol, grid[nrow][ncol]);
        grid[0][0] = 0;
        int row = 0;
        int col = 0;

        if (checker >= 3) {

            System.out.println(removable.toString());
            System.out.println(passed.toString());

        }


    }


    public int checking(int nrow, int ncol, int prev) {

        if (nrow < 0 || nrow >= grid.length) {
            return 0;
        } else if (ncol < 0 || ncol >= grid.length) {
            return 0;
        } else if (grid[nrow][ncol] != prev) {
            return 0;
        } else if (passed.contains("" + nrow + "," + ncol)) {
            return 0;
        } else {

            removable.add(nrow + " " + ncol);
            passed.add(nrow + " " + ncol);
            return checking(nrow, ncol + 1, prev) + checking(nrow, ncol - 1, prev) + checking(nrow - 1, ncol, prev) + checking(nrow + 1, ncol, prev) + 1;
        }
    }



    class ButtonListener implements ActionListener {
        ButtonListener() {

        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Button1")) {
                System.out.println("Button1 has been clicked");
            }
        }
    }



}
*/

