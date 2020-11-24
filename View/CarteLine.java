package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;

import Controller.Controller;
import Model.Carte;
import Model.CarteAbonnement;
import Model.CarteBancaire;

public class CarteLine extends JPanel {
	Controller c;
	private Button inserer;
	private JPanel buttonsPanel;
	private Carte carte;
	
	
	public CarteLine(Carte carte, Controller c) {
		super(new BorderLayout());
		
		this.c = c;
		this.carte = carte; 
		
		this.setBackground(Color.white);
		setBorder(new EmptyBorder(5,5,5,5));
		
		JLabel code = new JLabel("Carte " + (carte instanceof CarteBancaire ?  "Bancaire" : "Abonnement") + carte.getNoCarte());
		add(code, BorderLayout.NORTH);
		
		buttonsPanel = new JPanel(new BorderLayout());
		buttonsPanel.setOpaque(false);
		add(buttonsPanel, BorderLayout.EAST);
		

		if(carte instanceof CarteBancaire) {
			if(c.getSlotCarteBancaire() != (CarteBancaire) this.carte && this.c.getSlotCarteBancaire() == null) {
				Button insererCarte = new Button("ressources/images/button-thick-long.png","Insérer");
				buttonsPanel.add(insererCarte, BorderLayout.WEST);
				insererCarte.addMouseListener(new MouseInputAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							c.insererCarteBancaire((CarteBancaire) carte);							
							buttonsPanel.remove(insererCarte);
							buttonsPanel.repaint();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JLabel retourInsertion = new JLabel(e1.getMessage());
							add(retourInsertion, BorderLayout.SOUTH);
							repaint();
						}
					}
				});
			}
		} else {
			if(c.getSlotCarteAbonnement() != (CarteAbonnement) this.carte  && this.c.getSlotCarteAbonnement() == null) {
				Button insererCarte = new Button("ressources/images/button-thick-long.png","Insérer");
				buttonsPanel.add(insererCarte, BorderLayout.WEST);
				insererCarte.addMouseListener(new MouseInputAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							c.insererCarteAbonnement((CarteAbonnement) carte);							
							buttonsPanel.remove(insererCarte);
							buttonsPanel.repaint();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JLabel retourInsertion = new JLabel(e1.getMessage());
							add(retourInsertion, BorderLayout.SOUTH);
							repaint();
						}
					}
				});
			}
		}
		
		// Filler
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.CENTER);
	}
}
