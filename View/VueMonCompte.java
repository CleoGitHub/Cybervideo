package View;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.Controller;
import Model.CarteAbonnement;
import Model.CarteBancaire;

public class VueMonCompte extends Vue {

	private CarteBancaire cb;
	private CarteAbonnement ca;
	
	public VueMonCompte(Controller c) {
		super(c);
		setLayout(new BorderLayout());
		
		drawView();
	}

	public void drawView() {
		JPanel loginButtons = new JPanel();
		add(loginButtons, BorderLayout.CENTER);
		if(ca == null) {
			if(cb == null) {
				JLabel cbLabel = new JLabel("Insérer Carte Bancaire", SwingConstants.CENTER);
				loginButtons.add(cbLabel);
			}
			JLabel caLabel = new JLabel("Insérer Carte d'Abonnement", SwingConstants.CENTER);
			loginButtons.add(caLabel);
		}
	}
	
	public void setCb(CarteBancaire cb) {
		this.cb = cb;
	}
	
	public void setCa(CarteAbonnement ca) {
		this.ca = ca;
	}
}
