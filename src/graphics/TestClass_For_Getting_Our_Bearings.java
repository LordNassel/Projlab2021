package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Ez egy sima boh�ckod�s oszt�ly, hogy tesztelj�k hogyan megy ez. J� es�llyel t�rl�sre ker�l majd
public class TestClass_For_Getting_Our_Bearings {


    public void thethingtocall() {

        ImageFrame frame = new ImageFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }


    //itt ezt jel�lj�k majd meg, hogy ez egy githubr�l szerzett k�dsnippet
    class ImageFrame extends JFrame {
        public ImageFrame() {
            setTitle("ImageTest");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

            ImageComponent component = new ImageComponent();
            add(component);
            getContentPane().validate();
            getContentPane().repaint();
        }

        public static final int DEFAULT_WIDTH = 300;
        public static final int DEFAULT_HEIGHT = 200;
    }

    class ImageComponent extends JComponent {
        private static final long serialVersionUID = 1L;
        private Image image;

        public ImageComponent() {
            try {
                File image2 = new File("c:\\image.png");
                image = ImageIO.read(image2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paintComponent(Graphics g) {
            if (image == null) return;
            int imageWidth = image.getWidth(this);
            int imageHeight = image.getHeight(this);

            g.drawImage(image, 50, 50, this);

            for (int i = 0; i * imageWidth <= getWidth(); i++)
                for (int j = 0; j * imageHeight <= getHeight(); j++)
                    if (i + j > 0) g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
        }

    }
}
