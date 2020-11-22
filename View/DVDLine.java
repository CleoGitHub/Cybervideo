package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import Controller.Controller;
import Model.DVD;
import Model.Film;

public class DVDLine extends JPanel {
	DVD dvd;
	Controller c;
	
	public DVDLine(DVD dvd, Controller c) {
		super(new BorderLayout());
		this.c = c;
		this.dvd = dvd;
		
		this.setBackground(Color.white);
		setBorder(new EmptyBorder(5,5,5,5));
		
		JLabel titreFilm = new JLabel(dvd.getFilm().getTitre());
		add(titreFilm, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
		add(buttonsPanel, BorderLayout.EAST);
		
		// Bouton Retirer du panier
		JButton retirerPanier = new JButton("-");
		buttonsPanel.add(retirerPanier);
		retirerPanier.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.retirerPanier(dvd);
				removeItself();
			}
		});
	}
	
	public void removeItself() {
		JPanel parent = (JPanel) getParent();
		parent.remove(this);
		parent.revalidate();
		parent.repaint();
	}
}
