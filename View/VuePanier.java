package View;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controller;
import Model.DVD;
import Model.Film;

public class VuePanier extends Vue {

	private Controller controller;
	private ArrayList<DVD> dvds;
	private JPanel dvdsList;
	
    public VuePanier(Controller controller, ArrayList<DVD> dvds) {
        super();
        this.controller = controller;
        this.dvds = dvds;
        
        dvdsList = new JPanel(new StackLayout());
        for(DVD dvd : dvds) {
        	JPanel dvdInfo = new JPanel(new BorderLayout());
        	JLabel dvdTitre = new JLabel(dvd.getFilm().getTitre());
        	dvdInfo.add(dvdTitre, BorderLayout.NORTH);
        	dvdsList.add(dvdInfo);
        }
        add(dvdsList);
    }
    
    public void updateDVDs() {
    	remove(dvdsList);
        dvdsList = new JPanel(new StackLayout());
        for(DVD dvd : dvds) {
        	JPanel dvdInfo = new JPanel(new BorderLayout());
        	JLabel dvdTitre = new JLabel(dvd.getFilm().getTitre());
        	dvdInfo.add(dvdTitre, BorderLayout.NORTH);
        	dvdsList.add(dvdInfo);
        }
        add(dvdsList);
    }
}
