package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A SpecialFrame osztály.
 * A Win és Lose Screenekért felel
 */
public class SpecialFrame extends View{
    /**
     * Frame.
     */
    JFrame frame;

    /**
     * Panel.
     */
    JPanel panel;

    /**
     * Képernyõre kirajzolt kép.
     */
    protected Image image;

    /**
     * Konstrukor.
     */
    public SpecialFrame(){
        frame = new JFrame();
        panel = new JPanel() {
            @Override
            public Dimension getPreferredSize()
                {
                    return new Dimension(1920,1080);
                }
        };

        frame.setSize(1920,1080);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    /**
     * Win Screen
     */
    public void WinScreen() throws IOException {
        try{
            BufferedImage image = ImageIO.read(AsteroidView.class.getResource("/kep/youWin.png"));
            ImageIcon tmp = new ImageIcon(image);
            Image img = tmp.getImage();
            Image newimg = image.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            this.image = newimg;
            tmp = new ImageIcon(newimg);
            if(tmp != null)
            {
                icon = tmp;
                this.setIcon(icon);
            }
            frame.setContentPane(new JLabel(new ImageIcon(img)));
            frame.setTitle("You Win!");
            frame.add(panel);
            panel.setVisible(true);
            frame.setVisible(true);
        }

        catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Lose Screen
     */
    public void LoseScreen() throws IOException {
        try{
            BufferedImage image = ImageIO.read(AsteroidView.class.getResource("/kep/youLost.png"));
            ImageIcon tmp = new ImageIcon(image);
            Image img = tmp.getImage();
            Image newimg = image.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            this.image = newimg;
            tmp = new ImageIcon(newimg);
            if(tmp != null)
            {
                icon = tmp;
                this.setIcon(icon);
            }
            frame.setContentPane(new JLabel(new ImageIcon(img)));
            frame.setTitle("You Lost!");
            frame.add(panel);
            panel.setVisible(true);
            frame.setVisible(true);
        }
        catch (IOException exp) {
            exp.printStackTrace();
        }
    }
}
