package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.CyberVideo;

import java.awt.*;

public abstract class Vue extends JPanel {
	Controller controller;
	CyberVideo model;
	
    public Vue(Controller controller, CyberVideo model) {
        super();
        this.controller = controller;
        this.model = model;
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
		setBorder(new  EmptyBorder(5,5,5,5));
    }

	public Controller getController() {
		return controller;
	}
}
