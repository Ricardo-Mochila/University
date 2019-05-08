import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main1Graphics {
    public static void main(String[] args) {
        Fusion1Graphics fusionGraphics = new Fusion1Graphics();
        JFrame frame = new JFrame();
        JButton play = new JButton();
        JButton exit = new JButton();

        play.setSize(100,100);
        play.setText("Play");
        play.setLocation(400,400);
        exit.setText("Exit");
        exit.setSize(50,50);
        frame.setLayout(new GridBagLayout());
        frame.add(play);
        frame.add(exit);
        frame.setSize(800,800);
        frame.setVisible(true);
        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                fusionGraphics.input_num();
                fusionGraphics.input_color();
                frame.setVisible(false);
                fusionGraphics.set_grid();
                fusionGraphics.gameOver(0,0);
                while(!fusionGraphics.state){
                    fusionGraphics.set_grid();
                    fusionGraphics.gameOver(0,0);
                }
                fusionGraphics.get_grid();



            }

        });

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                frame.setVisible(false);

            }

        });





    }


}
