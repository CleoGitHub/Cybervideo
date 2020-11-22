package View;

import javax.swing.*;
import java.awt.*;

public abstract class Vue extends JPanel {
    public Vue() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
    }
}
