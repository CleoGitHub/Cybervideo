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

public class VueInfoFilm extends Vue {

	private Controller controller;
	private JPanel dvdsList;
	
    public VueInfoFilm(Controller controller) {
        super();
        this.controller = controller;
        

        setNavigationListeners();
    }
    

    private void setNavigationListeners() {
        NavigationListener listner = new NavigationListener(controller);
    }
}
