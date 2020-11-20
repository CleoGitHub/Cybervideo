import View.CyberVideoGUI;

import javax.swing.*;

public class Main implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }

    @Override
    public void run() {
        CyberVideoGUI dlub = new CyberVideoGUI();
        dlub.setVisible(true);
    }
}
