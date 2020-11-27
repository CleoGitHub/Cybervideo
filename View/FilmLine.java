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
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import Controller.Controller;
import Model.DVD;
import Model.Film;

public class FilmLine extends JPanel {
	Film film;
	Controller c;
	private Button info;
	private JPanel buttonsPanel;
	
	public FilmLine(Film film, Controller c) {
		super(new BorderLayout());
		
		this.c = c;
		this.film = film;
		
		this.setBackground(Color.white);
		setBorder(new EmptyBorder(5,5,5,5));
		
		JLabel titreFilm = new JLabel(film.getTitre());
		add(titreFilm, BorderLayout.NORTH);
		
		buttonsPanel = new JPanel(new BorderLayout());
		buttonsPanel.setOpaque(false);
		add(buttonsPanel, BorderLayout.EAST);
		
		// Bouton Ajouter au panier
		DVD d;
		if((d = c.getFirstAvailabeDVD(film)) != null) {
			Button addPanier = new Button("ressources/images/button-thick-long.png","Ajouter au panier");
			buttonsPanel.add(addPanier, BorderLayout.WEST);
			addPanier.addMouseListener(new MouseInputAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						c.ajouterPanier(film);
						// Check if there is an other DVD available after adding one
						if(c.getFirstAvailabeDVD(film) == null) {
							// If not remove the button
							buttonsPanel.remove(addPanier);
							buttonsPanel.revalidate();
							buttonsPanel.repaint();
							addNotAvailableLabel();
						}
					} catch (Exception e1) {
						Dialog.showError(e1.getMessage());
						e1.printStackTrace();
					}
				}
			});
		} else {
			addNotAvailableLabel();
		}

		// Bouton Info
		info = new Button("ressources/images/button-thick.png","+ d'infos");
		buttonsPanel.add(info, BorderLayout.EAST);
		info.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.setFilmVueInfoFilm(film);
			}				
		});
		
		// Filler
		buttonsPanel.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.CENTER);

		
		setNavigationListeners();
	}

    private void setNavigationListeners() {
        NavigationListener listener = new NavigationListener(c);
        info.setId(NavigationListener.INFO_FILM);
        info.addMouseListener(listener);
    }
    
    public void addNotAvailableLabel() {
		JLabel notAvailable = new JLabel("Pas de DVD disponible.");
		buttonsPanel.add(notAvailable, BorderLayout.WEST);
    }
}
