package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import Controller.Controller;
import Exceptions.ForbiddenGenreException;
import Exceptions.LocationCountExceededException;
import Exceptions.NoCardInSlotException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.PanierEmptyException;
import Model.CarteAbonnement;
import Model.CarteBancaire;
import Model.DVD;
import Model.Film;
import Model.Panier;

public class VuePanier extends Vue {

	private Panier panier;
	private JPanel dvdsList;
	
	private JLabel labelTotalB;
	private JLabel labelTotalA;
	
    public VuePanier(Controller controller, Panier panier) {
        super(controller);
        this.setLayout(new BorderLayout());
        this.panier = panier;
        drawView();
    }
    
    public void updateDVDs() {
    	((ItemList<DVD, DVDLine>) dvdsList).drawView();
    	int n = panier.getDvds().size();
    	labelTotalB.setText("Total : " + CarteBancaire.prixParJour * n);
    	labelTotalA.setText("Total abonnée : " + CarteAbonnement.prixParJour * n);
    	revalidate();
    	repaint();
    }
    
    private void drawView() {
        dvdsList = new ItemList<DVD, DVDLine>("Votre Panier", panier.getDvds(), getController(), DVD.class, DVDLine.class);
        add(dvdsList, BorderLayout.CENTER);
        
    	JPanel panel = new JPanel();
    	panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	JPanel panelTotals = new JPanel();
    	panelTotals.setLayout(new BoxLayout(panelTotals, BoxLayout.Y_AXIS));
    	int n = panier.getDvds().size();
    	int totalA = CarteAbonnement.prixParJour * n;
    	int totalB = CarteBancaire.prixParJour * n;
    	
    	JPanel totalBPanel = new JPanel(new BorderLayout());
    	
    	labelTotalB = new JLabel("Total : " + totalB);
    	labelTotalB.setFont(new Font(labelTotalB.getFont().getName(), Font.BOLD, 24));
    	labelTotalB.setAlignmentX(Component.RIGHT_ALIGNMENT);

    	Button buttonPayerB = new Button("ressources/images/button.png", "Payer");
    	buttonPayerB.addMouseListener(new MouseInputAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			payer(true);
    		}
    	});
    	
    	totalBPanel.add(labelTotalB, BorderLayout.WEST);
    	totalBPanel.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.CENTER);
    	totalBPanel.add(buttonPayerB, BorderLayout.EAST);
    	panelTotals.add(totalBPanel);
    	panelTotals.add(Box.createRigidArea(new Dimension(0, 10)));

    	
    	JPanel totalAPanel = new JPanel(new BorderLayout());

    	labelTotalA = new JLabel("Total abonné : " + totalA);
    	labelTotalA.setFont(new Font(labelTotalA.getFont().getName(), Font.BOLD, 24));
    	labelTotalA.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	
    	Button buttonPayerA = new Button("ressources/images/button.png", "Payer en tant qu'abonné");
    	buttonPayerA.addMouseListener(new MouseInputAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			payer(false);
    		}
    	});
    	
    	totalAPanel.add(labelTotalA, BorderLayout.WEST);
    	totalAPanel.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.CENTER);
    	totalAPanel.add(buttonPayerA, BorderLayout.EAST);
    	panelTotals.add(totalAPanel);
    	
    	panel.add(panelTotals);
    	panel.add(Box.createRigidArea(new Dimension(20, 0)));
    	add(panel, BorderLayout.SOUTH);
    }
    
    private void payer(boolean withCb) {
		try {
			getController().payer(withCb);
			Dialog.showSuccess("Paiement effectué !");
		} catch (LocationCountExceededException | NotEnoughMoneyException | PanierEmptyException | ForbiddenGenreException e1) {
			Dialog.showError(e1.getMessage());
		} catch (NoCardInSlotException e1) {
			Dialog.showError(e1.getMessage());
			getController().vueSuiv(getController().getVueMonCompte());
		}
    }

}
