package Controller;

import Model.CyberVideo;
import View.VueAccueil;
import View.VueTechnecien;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private CyberVideo model;
    private List<JPanel> vuesPile;
    private JPanel contenuPane;

    // vues
    private VueAccueil vueAccueil;
    private VueTechnecien vueTechnecien;

    public Controller(CyberVideo model) {
        this.model = model;
        vuesPile = new ArrayList<JPanel>();
        contenuPane = new JPanel(new BorderLayout());

        // creations du vues
        vueAccueil = new VueAccueil(this);
        vueTechnecien = new VueTechnecien(this);

        // insere les vues dans le JlayredPane
        vuesPile.add(vueAccueil);
        contenuPane.add(vueAccueil);
        contenuPane.revalidate();
        contenuPane.repaint();



        // TODO: creat panels
    }

    // getters & setters

    public JPanel getContenuPane() {
        return contenuPane;
    }

    public VueAccueil getVueAccueil() {
        return vueAccueil;
    }

    public VueTechnecien getVueTechnecien() {
        return vueTechnecien;
    }

    public void setOnTop(JPanel panel) {
        contenuPane.removeAll();
        contenuPane.add(panel);
        contenuPane.revalidate();
        contenuPane.repaint();

    }

    public void vuePrec() {
        System.out.println(vuesPile.size());
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
    }

    // TODO: actions utilisant le model

}
