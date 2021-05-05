package view;

import javax.swing.*;
import java.awt.*;

public class Specialframe extends JPanel {
    //Ez a frame létrehozása
    JFrame frame;
    JLabel message_label = new JLabel();
    JLabel  lblPic = new javax.swing.JLabel();
    JPanel panel;
    public Specialframe(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250,250);
        //Nem hasznalt jelenleg

        panel = new JPanel() {
            @Override
            public Dimension getPreferredSize()
            {
                return new Dimension(300,300);
            }
        };
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.add(message_label,BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void WinScreen(){
        message_label.setText("Gratulalunk nyertel, nem semmi");
        frame.setTitle("Nyertel");
        frame.add(panel);
        panel.setVisible(true);



    }

    public void LoseScreen(){
        message_label.setText("Sajnos vesztettél");
        frame.setTitle("Vesztettel");
        frame.add(panel);
        panel.setVisible(true);
    }
}
