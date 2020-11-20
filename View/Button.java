package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;

public class Button extends JLabel {

    private int id;

    Button(String imagePath, String text, int largeur, int hauteur) {
        this(imagePath);
        this.setText(text);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setPreferredSize(new Dimension(largeur, hauteur));
    }

    Button(String imagePath, String text) {
        this(imagePath);
        this.setForeground(java.awt.Color.WHITE);
        this.setText(text);
        this.setHorizontalTextPosition(JLabel.CENTER);
    }

    Button(String imagePath) {
        super();
        id = 0;
        try {
            this.setIcon(new ImageIcon(imagePath));
        } catch (Exception e) {
            System.err.println("can't read image, " + e.getMessage());
        }
    }


    // methodes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}