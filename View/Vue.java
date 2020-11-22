package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public abstract class Vue extends JPanel {
    public Vue() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
		setBorder(new  EmptyBorder(5,5,5,5));
    }
}
