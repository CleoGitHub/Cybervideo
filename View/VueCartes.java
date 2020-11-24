package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;

import Controller.Controller;
import Model.Carte;
import Model.Film;

public class VueCartes extends Vue {

	
    public VueCartes(Controller controller) {
        super(controller);
        this.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JPanel slotsPanel = new JPanel();
        slotsPanel.setLayout(new BorderLayout());
        JLabel labelCarteBancaire = new JLabel("Carte bancaire insérée: ");
        JLabel labelCarteAbonnement = new JLabel("Carte Abonnement insérée: ");
        slotsPanel.add(labelCarteBancaire, BorderLayout.WEST);
        slotsPanel.add(labelCarteAbonnement, BorderLayout.EAST);
        
        panel.add(slotsPanel,BorderLayout.NORTH);
        
        panel.setVisible(true);  
        
        JPanel panelCartesBancaires = new ItemList<Carte, CarteLine>("Les cartes disponibles", controller.getCartes(), controller, Carte.class, CarteLine.class);
        panel.add(panelCartesBancaires, BorderLayout.CENTER);
        
        this.add(panel, BorderLayout.CENTER);
        setNavigationListeners();
    }
    

    private void setNavigationListeners() {
        NavigationListener listner = new NavigationListener(getController());
    }
}
