package Controller;

import Model.CyberVideo;
import View.*;

import javax.swing.*;

import java.util.ArrayList;
import java.awt.*;

public class Controller {
    private CyberVideo model;
    private ArrayList<JPanel> vuesPile;
    private JPanel contenuPane;

    // =================
    // Vues
    private CyberVideoGUI frame;
    private VueAccueil vueAccueil;
    private VueTechnicien vueTechnicien;

    public Controller() {
        this.model = new CyberVideo();
        vuesPile = new ArrayList<JPanel>();
        contenuPane = new JPanel(new BorderLayout());

        // creations des vues
        vueAccueil = new VueAccueil(this);
        vueTechnicien = new VueTechnicien(this);

        start();
    }
    
	public void start() {
		frame = new CyberVideoGUI(this);
		frame.setVisible(true);
        vueSuiv(vueAccueil);
	}

    // getters & setters
    public JPanel getContenuPane() {
        return contenuPane;
    }

    public VueAccueil getVueAccueil() {
        return vueAccueil;
    }

    public VueTechnicien getVueTechnicien() {
        return vueTechnicien;
    }

    public void setOnTop(JPanel panel) {
        contenuPane.removeAll();
        contenuPane.add(panel);
        contenuPane.revalidate();
        contenuPane.repaint();
    }

    public void vuePrec() {
        if (vuesPile.size() > 1) {
            int dernier = vuesPile.size()-1;
            JPanel precPanel = vuesPile.get(dernier-1);
            setOnTop(precPanel);
            vuesPile.remove(dernier);
        }
    }

    public void vueSuiv(JPanel panel) {
        setOnTop(panel);
        vuesPile.add(panel);
        frame.pack();
    }

    public void setVue(JPanel panel) {
        vuesPile.clear();
        vuesPile.add(vueAccueil);
        setOnTop(panel);
    }

    // TODO: actions utilisant le model

}
