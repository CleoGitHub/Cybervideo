package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;

public class Button extends JLabel {

    private int id;

    public Button(String imagePath, String text, int largeur, int hauteur) {
        this(imagePath);
        this.setText(text);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setPreferredSize(new Dimension(largeur, hauteur));
    }

    public Button(String imagePath, String text) {
        this(imagePath);
        this.setForeground(java.awt.Color.WHITE);
        this.setText(text);
        this.setHorizontalTextPosition(JLabel.CENTER);
    }

    public Button(String imagePath) {
        this();
        setForeground(Color.WHITE);
        try {
            this.setIcon(new ImageIcon(imagePath));
        } catch (Exception e) {
            System.err.println("can't read image, " + e.getMessage());
        }
    }

    public Button() {
        super();
        id = 0;
    }


    // methodes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}