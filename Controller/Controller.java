package Controller;

import Model.CyberVideo;
import View.*;

import javax.swing.*;

import java.awt.Dimension;
import java.util.ArrayList;

public class Controller {
    private CyberVideo model;
    private ArrayList<JPanel> vuesPile = new ArrayList<>();
    private JLayeredPane contenuPane;
    private VueCyberVideo frame;
    
    // ==========
    // Les vues
    private VueAccueil vueAccueil;
    
    public Controller(CyberVideo model) {
        this.model = model;
        
        // TODO: create panels
        vueAccueil = new VueAccueil();
    }
    
    public void start() {
        // TODO: créer/excuter Jframe
    	frame = new VueCyberVideo();
    	this.suivant(vueAccueil);
    	
    	frame.setVisible(true);
    	frame.setSize(new Dimension(300, 300));
    }
    
    public void suivant(JPanel p) {
    	// Remove the previous panel
    	if(vuesPile.size() > 0) {
    		JPanel oldP = vuesPile.get(vuesPile.size()-1);
    		oldP.setVisible(false);    		
    	}
    	// Add the new JPanel p from Vue v
    	vuesPile.add(p);
    	frame.add(p);
    	p.setVisible(true);
    }
    
    public void prev() {
    	// There is an available previous view if the size of the array is > 1
    	if(vuesPile.size() > 1) {
    		JPanel oldP = vuesPile.get(vuesPile.size()-1);
    		JPanel newP = vuesPile.get(vuesPile.size()-2);
    		oldP.setVisible(false);
    		newP.setVisible(true);
    		vuesPile.remove(oldP);
    	}
    }
    

    // TODO: void prev(): changer la vue du contenuPane à la précedenté


    // TODO: actions utilisant le model

}
