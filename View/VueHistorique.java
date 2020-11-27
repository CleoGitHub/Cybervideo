package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Carte;
import Model.CarteBancaire;
import Model.CyberVideo;
import Model.Location;

public class VueHistorique extends Vue {

	Carte c;
	private JTable locations;
	JLabel label;
	private JScrollPane locationsScrollPanel;
    private DefaultTableModel locationsModel;
    
	public VueHistorique(Controller controller, CyberVideo model) {
		super(controller, model);
		
		setLayout(new BorderLayout());
		drawView();
	}
	
	private void drawView() {
		label = new JLabel("Historique de la carte " + (c == null ? "" : c.getLibelle()));
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, 24));
		add(label, BorderLayout.NORTH);
		
		drawLocations();
	}
	
	public void drawLocations() {
		if(locationsScrollPanel != null)
			remove(locationsScrollPanel);
		
		locationsModel = new DefaultTableModel(new Object[] {"Carte", "Date début", "Nombre de jours", "Status", "Film", "Genres", "Code Barre"}, 0);
        locations = new JTable(locationsModel);  
    
        locationsScrollPanel = new JScrollPane(locations);
        add(locationsScrollPanel);
        loadLocations();
	}
	
	public void loadLocations() {
		locationsModel.setRowCount(0);
		if(c != null)
			chargerLocations(c.getHistorique());
	}
	
	public void chargerLocations(ArrayList<Location> locations) {
		for(Location loc : locations) {
			addLocation(loc);
		}
	}
	
	public void addLocation(Location loc) {
		locationsModel.addRow(new Object[]{
				loc.getCarteLoueur().getLibelle(),
				loc.getDateDebut().toString(),
				loc.getNbJours(),
				loc.estRendu() ? "RENDU" : "A RENDRE",
				loc.getDvdLoue().getFilm().getTitre(),
				loc.getDvdLoue().getFilm().getGenres(),
				loc.getDvdLoue().getCodeBarre()
		});
	}

	public void setCard(Carte c) {
		this.c = c;
		label.setText("Historique de la carte " + (c == null ? "" : c.getLibelle()));
		loadLocations();
	}

}
