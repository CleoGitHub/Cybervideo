package View;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

import Controller.Controller;
import Exceptions.DVDNotFoundException;
import Exceptions.DVDNotRentedException;

public class VueRendreDVD extends Vue {

	public VueRendreDVD(Controller c) {
		super(c);
		
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		JPanel rendrePanel = new JPanel();
		rendrePanel.setLayout(new BoxLayout(rendrePanel,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Scannez le code barre de votre DVD");
		JCheckBox endommageCheckBox = new JCheckBox("Endommagé");
		JTextField codeBarreField = new JTextField();
		Button rendreBtn = new Button("ressources/images/button-thick.png",  "Rendre");
		rendreBtn.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int codeBarre = Integer.parseInt(codeBarreField.getText());
				try {
					getController().rendreDVD(codeBarre, endommageCheckBox.isSelected());
					Dialog.showSuccess("DVD Rendu !");
					codeBarreField.setText("");
					endommageCheckBox.setSelected(false);
				} catch (DVDNotFoundException | DVDNotRentedException e1) {
					Dialog.showError(e1.getMessage());
				}
			}
		});
		rendrePanel.add(label);
		rendrePanel.add(codeBarreField);
		rendrePanel.add(endommageCheckBox);
		rendrePanel.add(rendreBtn);
		panel.add(rendrePanel);
		add(panel, BorderLayout.CENTER);
	}

	
}
