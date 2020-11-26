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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;

import Controller.Controller;
import Model.CarteAbonnement;
import Model.CarteBancaire;

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

	
	public VueMonCompte(Controller c, ArrayList<CarteBancaire> cartesBancaires, ArrayList<CarteAbonnement> cartesAbonnements) {
		super(c);
        this.cartesBancaires = cartesBancaires;
        this.cartesBancairesDispo = this.cartesBancaires;
        this.cartesAbonnements= cartesAbonnements;
        this.cartesAbonnementsDispo = this.cartesAbonnements;
        
		setLayout(new BorderLayout());
		
		drawView();
	}

	private void drawView() {
		drawCards();
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
	
	public void setCb(CarteBancaire cb) {
		this.cb = cb;
		drawPanelBancaire();
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
	}
}
