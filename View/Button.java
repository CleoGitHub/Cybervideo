package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;

public class Button extends JLabel {

    Button(String imagePath, String text, int largeur, int hauteur) {

        super();

        this.setText(text);
        this.setHorizontalTextPosition(JLabel.CENTER);

        try {
            this.setIcon(new ImageIcon(imagePath));
        } catch (Exception e) {
            System.err.println("can't read image");
        }

        this.setPreferredSize(new Dimension(largeur, hauteur));
    }

    Button(String imagePath, String text) {

        super();

        this.setForeground(java.awt.Color.WHITE);
        this.setText(text);
        this.setHorizontalTextPosition(JLabel.CENTER);

        try {
                this.setIcon(new ImageIcon(imagePath));
            } catch (Exception e) {
                System.err.println("can't read image");
            }

    }

    Button(String imagePath) {

        super();

        try {
                this.setIcon(new ImageIcon(imagePath));
            } catch (Exception e) {
                System.err.println("can't read image");
            }

    }
}