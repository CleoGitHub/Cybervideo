package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;

import Model.EventType;
import Model.CarteAbonnement;
import Model.CarteBancaire;
import Model.CyberVideo;
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
	private JScrollPane locationsScrollPanel;
    private DefaultTableModel locationsModel;
    private JPanel panelCrediterBancaire;
    private JLabel caSolde;
    private Button buttonAbonnement;
	
	public VueMonCompte(Controller c, CyberVideo model) {
        super(c, model);
        this.cartesBancaires = model.getCartesBancaires();
        this.cartesBancairesDispo = this.cartesBancaires;
        this.cartesAbonnements= model.getCartesAbonnements();
        this.cartesAbonnementsDispo = this.cartesAbonnements;

		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		model.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if(e.getPropertyName() == EventType.PAYMENT.toString()) {
					drawPanelAbo();
					loadLocations();
				} else if(e.getPropertyName() == EventType.ABONNEMENT.toString()) {
					drawView();
				} else if(e.getPropertyName() == EventType.RENDU.toString()) {
					loadLocations();
				}
			}
		});
		
		drawView();
	}

	private void drawView() {
		drawCards();
		drawLocations();
	}
	
	private void drawCards() {
		if(cardsPanel != null)
			remove(cardsPanel);
		cardsPanel = new JPanel(new GridLayout(1, 2));
		add(cardsPanel);
		
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
			JPanel insertPanel = new JPanel(new BorderLayout());
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
			insertPanel.add(cbList, BorderLayout.CENTER);
			insertPanel.add(buttonAbo, BorderLayout.EAST);
			panelBancaire.add(insertPanel, BorderLayout.CENTER);
		} else {
			JPanel cbOptions = new JPanel();
			cbOptions.setLayout(new BoxLayout(cbOptions,BoxLayout.Y_AXIS));
			JPanel cbInfo = new JPanel(new GridLayout(1,2));
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
			Button buttonHistorique = new Button("ressources/images/button-thick-long.png", "Voir l'historique");
	        NavigationListener listener = new NavigationListener(getController());
	        buttonHistorique.setId(NavigationListener.HISTORIQUE);
	        buttonHistorique.addMouseListener(listener);
	        buttonHistorique.addMouseListener(new MouseInputAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		getController().setHistoriqueCard(cb);
	        	}
	        });
			cbInfo.add(cbLabel);
			JPanel panel = new JPanel(new BorderLayout());
			JPanel buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS));
			
			

			buttonsPanel.add(buttonRetirer);
			buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
			buttonsPanel.add(buttonHistorique);
			
			if(this.ca == null) {
				buttonAbonnement = new Button("ressources/images/button-thick-long.png", "S'abonner");
				buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
				buttonsPanel.add(buttonAbonnement);
				setNavigationListeners();
			}
			
			panel.add(buttonsPanel, BorderLayout.EAST);
			cbInfo.add(panel);
			cbOptions.add(cbInfo);
			panelBancaire.add(cbOptions, BorderLayout.SOUTH);
			drawCrediterCarteAbonnement(cbOptions);
		}
		
		panelBancaire.add(labelBancaire, BorderLayout.NORTH);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=1;
		cardsPanel.add(panelBancaire, BorderLayout.WEST);
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
			JPanel insertPanel = new JPanel(new BorderLayout());
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
			insertPanel.add(caList, BorderLayout.CENTER);
			insertPanel.add(buttonAbo, BorderLayout.EAST);
			panelAbo.add(insertPanel, BorderLayout.SOUTH);
		} else {
			JPanel caOptions = new JPanel(new BorderLayout());
			JPanel caInfo = new JPanel();
			JPanel caButtons = new JPanel();
			caButtons.setLayout(new BoxLayout(caButtons,BoxLayout.Y_AXIS));
			caInfo.setLayout(new BoxLayout(caInfo,BoxLayout.Y_AXIS));
			JLabel caLabel = new JLabel(ca.getLibelle());
			caSolde = new JLabel("Solde : " + Integer.toString(ca.getSolde()) + "€");
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
			Button buttonHistorique = new Button("ressources/images/button-thick-long.png", "Voir l'historique");
	        NavigationListener listener = new NavigationListener(getController());
	        buttonHistorique.setId(NavigationListener.HISTORIQUE);
	        buttonHistorique.addMouseListener(listener);
	        buttonHistorique.addMouseListener(new MouseInputAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		getController().setHistoriqueCard(ca);
	        	}
	        });
			caInfo.add(caLabel);
			caInfo.add(caSolde);
			caButtons.add(buttonRetirer);
			caButtons.add(Box.createRigidArea(new Dimension(0, 5)));
			caButtons.add(buttonHistorique);
			caOptions.add(caInfo, BorderLayout.WEST);
			caOptions.add(caButtons, BorderLayout.EAST);
			panelAbo.add(caOptions, BorderLayout.CENTER);
		}
		panelAbo.add(labelAbo, BorderLayout.NORTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=2;
		cardsPanel.add(panelAbo, BorderLayout.EAST);
		revalidate();
		repaint();
	}
	
	public void drawLocations() {
		if(locationsScrollPanel != null)
			remove(locationsScrollPanel);
		
		locationsModel = new DefaultTableModel(new Object[] {"Date début", "Nombre de jours", "Status", "Carte", "Film", "Genres", "Code Barre"}, 0);
        locations = new JTable(locationsModel);  
    
        locationsScrollPanel = new JScrollPane(locations);
        add(locationsScrollPanel);
        loadLocations();
	}
	
	public void loadLocations() {
		locationsModel.setRowCount(0);
		
		if(ca != null) {
			chargerLocations(ca.getLocationsEnCours(),ca.getLibelle());
		}
		if(cb != null) {
			chargerLocations(cb.getLocationsEnCours(),cb.getLibelle());
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
				loc.getDvdLoue().getFilm().getGenres(),
				loc.getDvdLoue().getCodeBarre()
		});
	}
	
	public void drawCrediterCarteAbonnement(JPanel panel) {
		if(panelCrediterBancaire != null)
			panel.remove(panelCrediterBancaire);
		
		if(ca != null && cb != null) {
			
			panelCrediterBancaire = new JPanel(new BorderLayout());
			
			JTextField montanAjoutCarteAbonne = new JTextField();
			panelCrediterBancaire.add(montanAjoutCarteAbonne,BorderLayout.CENTER);
			
			JLabel labelCrediter = new JLabel("Créditer la carte d'abonnement");
			panelCrediterBancaire.add(labelCrediter,BorderLayout.NORTH);
			
			Button validerCreditCarte = new Button("ressources/images/button-thick-long.png", "Valider");
			panelCrediterBancaire.add(validerCreditCarte, BorderLayout.EAST);
			
			validerCreditCarte.addMouseListener(new MouseInputAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String texte = montanAjoutCarteAbonne.getText();
					try {
						ca.ajouterSolde(Integer.parseInt(texte));
						montanAjoutCarteAbonne.setText("");
						drawView();
						Dialog.showSuccess("Vous venez d'ajouter " + texte + " à votre carte abonnement");
					} catch(Exception ex) {
						Dialog.showError(ex.getMessage());
					}
				}
			});		
			
			panel.add(panelCrediterBancaire);
		}
	}
	
	public void setCb(CarteBancaire cb) {
		this.cb = cb;
		drawView();
	}
	
	public void setCa(CarteAbonnement ca) {
		this.ca = ca;
		drawView();
	}
	
	private void setNavigationListeners() {
        NavigationListener listener = new NavigationListener(controller);
//        // TODO: add listener to navigation buttons
        buttonAbonnement.setId(NavigationListener.ABONNEMENT);
        buttonAbonnement.addMouseListener(listener);
    }
}
