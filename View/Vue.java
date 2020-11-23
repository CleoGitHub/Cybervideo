package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import java.awt.*;

public abstract class Vue extends JPanel {
	private Controller c;
	
    public Vue(Controller c) {
        super();
        this.c = c;
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
		setBorder(new  EmptyBorder(5,5,5,5));
    }

	public Controller getController() {
		return c;
	}
}
