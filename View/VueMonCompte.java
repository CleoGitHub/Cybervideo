package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.CarteAbonnement;
import Model.CarteBancaire;
import Model.Location;

public class VueMonCompte extends Vue {
	private ArrayList<CarteBancaire> cartesBancaires;
	private ArrayList<CarteBancaire> cartesBancairesDispo;
	
	private ArrayList<CarteAbonnement> cartesAbonnements;
	private ArrayList<CarteAbonnement> cartesAbonnementsDispo;
	
	private CarteBancaire cb;
	private CarteAbonnement ca;
	
	private JPanel cardsPanel;
	private JPanel panelAbo;
	private JPanel panelBancaire;
	private JTable locations;
    private DefaultTableModel locationsModel;
    private JPanel panelCrediterBancaire;

	
	public VueMonCompte(Controller c, ArrayList<CarteBancaire> cartesBancaires, ArrayList<CarteAbonnement> cartesAbonnements) {
		super(c);
        this.cartesBancaires = cartesBancaires;
        this.cartesBancairesDispo = this.cartesBancaires;
        this.cartesAbonnements= cartesAbonnements;
        this.cartesAbonnementsDispo = this.cartesAbonnements;


		locationsModel = new DefaultTableModel(new Object[] {"Date début", "Nombre de jours", "status", "carte", "film", "genres"}, 0);
        locations = new JTable(locationsModel);  

        JScrollPane locationsScrollPanel = new JScrollPane(locations);
        
		setLayout(new BorderLayout());
        add(locationsScrollPanel);
		
		drawView();
	}

	private void drawView() {
		drawCards();
		drawLocations();
	}
	
	private void drawCards() {
		cardsPanel = new JPanel(new GridBagLayout());
		add(cardsPanel, BorderLayout.NORTH);
		
		drawPanelBancaire();
		drawPanelAbo();
	}
	
	private void drawPanelBancaire() {
		if(panelBancaire != null)
			cardsPanel.remove(panelBancaire);
		panelBancaire = new JPanel(new BorderLayout());
		panelBancaire.setBorder(new EmptyBorder(5,5,5,5));
		panelBancaire.setBackground(Color.white);
		JLabel labelBancaire = new JLabel("Carte Bancaire :");
		
		if(cb == null) {
			JPanel insertPanel = new JPanel();
			insertPanel.setOpaque(false);
			
			// Build cards string array
			ArrayList<String> cbStrings = new ArrayList<>();
			for(CarteBancaire cb : cartesBancairesDispo) {
				cbStrings.add(cb.getLibelle());
			}
			
			JComboBox cbList = new JComboBox(cbStrings.toArray());
			
			// Insert card button
			Button buttonAbo = new Button("ressources/images/button-thick-long.png", "Insérer");
			buttonAbo.addMouseListener(new MouseInputAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					CarteBancaire cb = cartesBancairesDispo.get(cbList.getSelectedIndex());
					try {
						getController().insererCarteBancaire(cb);
						setCb(cb);
					} catch (Exception e1) {
						Dialog.showError(e1.getMessage());
					}
				}
			});
			insertPanel.add(cbList);
			insertPanel.add(buttonAbo);
			panelBancaire.add(insertPanel, BorderLayout.SOUTH);
		} else {
			JPanel cbOptions = new JPanel();
			JLabel cbLabel = new JLabel(cb.getLibelle());
			Button buttonRetirer = new Button("ressources/images/button-thick-long.png", "Retirer");
			buttonRetirer.addMouseListener(new MouseInputAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						getController().insererCarteBancaire(null);
						setCb(null);
					} catch (Exception e1) {
						Dialog.showError(e1.getMessage());
					}
				}
			});
			cbOptions.add(cbLabel);
			cbOptions.add(buttonRetirer);
			panelBancaire.add(cbOptions, BorderLayout.SOUTH);
		}
		
		panelBancaire.add(labelBancaire, BorderLayout.NORTH);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=1;
		cardsPanel.add(panelBancaire, gbc);
		revalidate();
		repaint();
	}

	
	private void drawPanelAbo() {
		if(panelAbo != null)
			cardsPanel.remove(panelAbo);
		panelAbo = new JPanel(new BorderLayout());
		panelAbo.setBackground(Color.white);
		panelAbo.setBorder(new EmptyBorder(5,5,5,5));
		JLabel labelAbo = new JLabel("Abonnement :");
		
		if(ca == null) {
			
			JPanel insertPanel = new JPanel();
			insertPanel.setOpaque(false);
			
			// Build cards string array
			ArrayList<String> caStrings = new ArrayList<>();
			for(CarteAbonnement ca : cartesAbonnementsDispo) {
				caStrings.add(ca.getLibelle());
			}
			
			JComboBox caList = new JComboBox(caStrings.toArray());
			
			// Insert card button
			Button buttonAbo = new Button("ressources/images/button-thick-long.png", "Insérer");
			buttonAbo.addMouseListener(new MouseInputAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					CarteAbonnement ca = cartesAbonnementsDispo.get(caList.getSelectedIndex());
					try {
						getController().insererCarteAbonnement(ca);
						setCa(ca);
					} catch (Exception e1) {
						Dialog.showError(e1.getMessage());
					}
				}
			});
			insertPanel.add(caList);
			insertPanel.add(buttonAbo);
			panelAbo.add(insertPanel, BorderLayout.SOUTH);
		} else {
			JPanel caOptions = new JPanel();
			JLabel caLabel = new JLabel(ca.getLibelle());
			Button buttonRetirer = new Button("ressources/images/button-thick-long.png", "Retirer");
			buttonRetirer.addMouseListener(new MouseInputAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						getController().insererCarteAbonnement(null);
						setCa(null);
					} catch (Exception e1) {
						Dialog.showError(e1.getMessage());
					}
				}
			});
			caOptions.add(caLabel);
			caOptions.add(buttonRetirer);
			panelAbo.add(caOptions, BorderLayout.SOUTH);
		}
		panelAbo.add(labelAbo, BorderLayout.NORTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=2;
		cardsPanel.add(panelAbo, gbc);
		revalidate();
		repaint();
	}
	
	public void drawLocations() {
		locationsModel.setRowCount(0);
		
		if(ca != null) {
			chargerLocations(ca.getLocationsEnCours(),"abonnemnet");
		}
		if(cb != null) {
			chargerLocations(cb.getLocationsEnCours(),"bancaire");
		} 
		
	}
	
	public void chargerLocations(ArrayList<Location> locations, String carte) {
		for(Location loc : locations) {
			addLocation(loc, carte);
		}
	}
	
	public void addLocation(Location loc, String carte) {
		locationsModel.addRow(new Object[]{
				loc.getDateDebut().toString(),
				loc.getNbJours(),
				loc.estRendu() ? "RENDU" : "A RENDRE",
				carte,
				loc.getDvdLoue().getFilm().getTitre(),
				loc.getDvdLoue().getFilm().getGenres()
		});
	}
	
	public void drawCrediterCarteAbonenement() {
		if(panelCrediterBancaire != null)
			cardsPanel.remove(panelCrediterBancaire);
		
		if(ca != null && cb != null) {
			
			panelCrediterBancaire = new JPanel(new BorderLayout());
			
			JTextField montanAjoutCarteAbonne = new JTextField();
			panelCrediterBancaire.add(montanAjoutCarteAbonne,BorderLayout.CENTER);
			
			JLabel labelCrediter = new JLabel("Ajouter a la carte abonnement");
			panelCrediterBancaire.add(labelCrediter,BorderLayout.NORTH);
			
			Button validerCreditCarte = new Button("ressources/images/button-thick-long.png", "Valider");
			panelCrediterBancaire.add(validerCreditCarte, BorderLayout.SOUTH);
			
			validerCreditCarte.addMouseListener(new MouseInputAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String texte = montanAjoutCarteAbonne.getText();
					if(texte.length() > 0 && Integer.parseInt(texte) > 10) {
						try {
							ca.ajouterSolde(Integer.parseInt(texte));
							montanAjoutCarteAbonne.setText("");
							Dialog.showSuccess("Vous venez d'ajouter " + texte + " a votre carte abonnement");
						} catch(Exception ex) {
							Dialog.showError(ex.getMessage());
						}
					} else {
						Dialog.showError("Le montant minimum est de 10 pour recharger une carte abonnement");
					}
				}
			});		
			
			cardsPanel.add(panelCrediterBancaire);
		}
	}
	
	public void setCb(CarteBancaire cb) {
		this.cb = cb;
		drawPanelBancaire();
		drawLocations();
		drawCrediterCarteAbonenement();
		// this.cartesAbonnementsDispo = cb == null ? this.cartesAbonnements : cb.getAbonnements();
		// drawPanelAbo();
	}
	
	public void setCa(CarteAbonnement ca) {
		this.ca = ca;
		
		/* if(ca == null) { // Cas où la carte abonnement a été retirée
			this.cartesBancairesDispo = new ArrayList<CarteBancaire>(Arrays.asList(ca.getCb()));
			drawPanelBancaire();
		}
		*/
		
		drawPanelAbo();
		drawLocations();
		drawCrediterCarteAbonenement();
	}
}
