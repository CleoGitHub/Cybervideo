package View;

import javax.swing.JOptionPane;

public class Dialog {
	public static void showError(String s) {
		JOptionPane.showMessageDialog(null, s, null, JOptionPane.ERROR_MESSAGE);
	}
	public static void showSuccess(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}
