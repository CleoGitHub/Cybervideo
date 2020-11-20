import Model.CyberVideo;

import javax.swing.*;

import Controller.Controller;

import java.util.List;

public class CyberVideoGUI implements Runnable {
    private Controller controller;

    public CyberVideoGUI() {
    	CyberVideo model = new CyberVideo();
    	
    	// TODO: Initialiser le modèle
    	
    	this.controller = new Controller(model);
    }

    @Override
    public void run() {
    	this.controller.start();
    }
    
	public static void main(String[] argv) {
		CyberVideoGUI cvg = new CyberVideoGUI();
		cvg.run();
	}
}
