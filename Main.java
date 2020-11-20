import View.CyberVideoGUI;

import javax.swing.*;

import Controller.Controller;

public class Main implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }

    @Override
    public void run() {
    	Controller c = new Controller();
    }
}
