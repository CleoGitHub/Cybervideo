package View;

import Controller.Controller;
import Model.CarteBancaire;
import Model.CyberVideo;
import Model.Genre;
import View.Button;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VueAbonnement extends Vue {

    private JPanel container;
    private CarteBancaire cb;
    private Button valider;
    private Genre[] genres;
    private JTextField nomField;
    private JTextField montantField;
    private ArrayList<JCheckBox> genresList;

    public VueAbonnement(Controller controller, CyberVideo model) {
        super(controller, model);

        // super-contianer
        container = new JPanel(new StackLayout());        
        
        JPanel nomPanel = creerNomPanel();
        ajouterPanel(nomPanel);
        
        JPanel montantPanel = creerMontantPanel();
        ajouterPanel(montantPanel);
        
        JPanel genresPanel = creerGenrePanel();
        ajouterPanel(genresPanel);      
        
        valider = new Button("ressources/images/button.png", "Valider");
        JPanel insererPanel = new JPanel(new GridBagLayout());
        insererPanel.add(valider);
        add(insererPanel, BorderLayout.SOUTH);
        
        valider.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validateForm()) {
					int montant = Integer.parseInt(montantField.getText());
					String nomCarte = nomField.getText();
					ArrayList<Genre> genresInterdit = new ArrayList<Genre>();
					int i = 0;
					for(JCheckBox genre : genresList) {
						if(genre.isSelected()) {
							genresInterdit.add(genres[i]);
						}
						i++;
					}
					try {					
						controller.creerAbonnement(nomCarte, montant, genresInterdit);
						Dialog.showSuccess("Vous venez d'ajouter créer votre abonnement, vous aller recevoir votre carte Cyber Vidéo dans les 7 jours");
						controller.vuePrec();
					} catch(Exception ex) {
						Dialog.showError(ex.getMessage());
					}
				}
			}
		});	
        
        add(new JScrollPane(container));        
    }

    private void ajouterPanel(JPanel panel) {
        container.add(Box.createRigidArea(new Dimension(0, 15)));
        container.add(panel);
    }
    
    private JPanel creerNomPanel() {
    	JPanel nomPanel = new JPanel(new BorderLayout());
    	nomField = new JTextField();
    	JLabel labelNom = new JLabel("Nom de la carte: ");
    	nomPanel.add(labelNom, BorderLayout.NORTH);
    	nomPanel.add(nomField);
    	
    	return nomPanel;
    }
    
    private JPanel creerMontantPanel() {
    	JPanel montantPanel = new JPanel(new BorderLayout());
    	montantField = new JTextField();
    	montantField.setText("15");
    	JLabel montantLabel = new JLabel("montant initiale de la carte (minimum 15 €) : ");
    	montantPanel.add(montantLabel, BorderLayout.NORTH);
    	montantPanel.add(montantField);
    	
    	return montantPanel;
    }

    private JPanel creerGenrePanel() {
        genresList = new ArrayList<>();
        genres = Genre.values();
        JPanel genresContainer = new JPanel(new GridLayout(0, 3));
        for (Genre genre : genres) {
            JCheckBox genreBox = new JCheckBox(genre.toString());
            genresList.add(genreBox);
            genresContainer.add(genreBox);
        }
        JPanel genresPanel = new JPanel(new BorderLayout());
        genresPanel.add(new JLabel("Genres interdit pour la carte:"), BorderLayout.NORTH);
        genresPanel.add(genresContainer);

        return genresPanel;
    }
    
    private boolean validateForm() {
    	String message = "";
		if(Integer.parseInt(montantField.getText()) < 15) {
			message += "Le montant minimum pour une carte est de 15€\n";
		}
		if(nomField.getText().length() == 0) {
			message += "Veuillez saisir un nom pour la carte\n";
		}
		if(message.length() != 0) {
			Dialog.showError(message);
			return false;
		} else {
			return true;
		}
		
    }
}
