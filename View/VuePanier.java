package View;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import Controller.Controller;
import Model.DVD;
import Model.Film;
import Model.Panier;

public class VuePanier extends Vue {

	private Panier panier;
	private JPanel dvdsList;
	
    public VuePanier(Controller controller, Panier panier) {
        super(controller);
        this.setLayout(new BorderLayout());
        
        this.panier = panier;
        
        dvdsList = new ItemList<DVD, DVDLine>("Votre Panier", panier.getDvds(), controller, DVD.class, DVDLine.class);
        add(dvdsList, BorderLayout.CENTER);
    }
    
    public void updateDVDs() {
    	((ItemList<DVD, DVDLine>) dvdsList).drawView();
    }
   
}
