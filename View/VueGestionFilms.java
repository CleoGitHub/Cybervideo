package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueGestionFilms extends Vue {

    private JComboBox<String> realisateursBox;
    private JComboBox<String> acteursBox;
    private DefaultComboBoxModel<String> realisateursModel;
    private DefaultComboBoxModel<String> acteursModel;
    private JList acteurSelectionnes;
    private DefaultListModel<String> acteurSelecModel;
    private JTextField titre;
    private JTextField date;
    private Button insererActeur;
    private Button suppActeur;
    private Button selecActeur;
    private Button annulerActeur;
    private Button insererRealisateur;
    private Button suppRealisateur;
    private Button insererFilm;

    private int dvds;

    public VueGestionFilms(Controller controller) {
        super(controller);

        ArrayList<String> acteurs = controller.getActeurs();
        ArrayList<String> realisateurs = controller.getRealisateurs();

        // acteurs panel
        acteursModel = new DefaultComboBoxModel<String>(acteurs.toArray(new String[acteurs.size()]));
        acteursBox = new JComboBox<>(acteursModel);

        insererActeur = new Button("ressources/images/button-thick.png", "+");
        suppActeur = new Button("ressources/images/button-thick.png", "-");
        JPanel acteursButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acteursButtons.add(insererActeur);
        acteursButtons.add(suppActeur);

        acteurSelecModel = new DefaultListModel<>();
        for (String act : acteurs)
            acteurSelecModel.addElement(act);
        acteurSelectionnes = new JList(acteurSelecModel);

        selecActeur = new Button("ressources/images/button-thick.png", "select");
        annulerActeur = new Button("ressources/images/button-thick.png", "supprimer");
        JPanel acteurSelecButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acteurSelecButtons.add(selecActeur);
        acteurSelecButtons.add(annulerActeur);

        JPanel acteurSelecPanel = new JPanel(new BorderLayout());
        acteurSelecPanel.add(new JLabel("Acteurs selectionnés :"), BorderLayout.NORTH);
        acteurSelecPanel.add(acteurSelectionnes);
        acteurSelecPanel.add(acteurSelecButtons, BorderLayout.SOUTH);


        JPanel acteursPanel = new JPanel(new BorderLayout());
        acteursPanel.add(new JLabel("Acteurs: "), BorderLayout.NORTH);
        acteursPanel.add(acteursBox);
        acteursPanel.add(acteursButtons, BorderLayout.EAST);
        acteursPanel.add(acteurSelecPanel, BorderLayout.SOUTH);


        // realisateurs panel
        realisateursModel = new DefaultComboBoxModel<String>(realisateurs.toArray(new String[realisateurs.size()]));
        realisateursBox = new JComboBox<>(realisateursModel);
        JPanel realisateursPanel = new JPanel(new BorderLayout());
        insererRealisateur = new Button("ressources/images/button-thick.png", "+");
        suppRealisateur= new Button("ressources/images/button-thick.png", "-");
        JPanel realisateursButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        realisateursButtons.add(insererRealisateur);
        realisateursButtons.add(suppRealisateur);
        JPanel realisateurPanel = new JPanel(new BorderLayout());
        realisateurPanel.add(new JLabel("Realisateur: "), BorderLayout.NORTH);
        realisateurPanel.add(realisateursBox);
        realisateurPanel.add(realisateursButtons, BorderLayout.EAST);

        // titre & date
        titre = new JTextField();
        JPanel titrePanel = new JPanel(new BorderLayout());
        titrePanel.add(new JLabel("Titre: "), BorderLayout.NORTH);
        titrePanel.add(titre);

        date = new JTextField();
        JPanel datePanel = new JPanel(new BorderLayout());
        datePanel.add(new JLabel("Date: "), BorderLayout.NORTH);
        datePanel.add(date);

        JPanel container = new JPanel(new StackLayout());
        container.add(Box.createRigidArea(new Dimension(0, 15)));
        container.add(titrePanel);
        container.add(Box.createRigidArea(new Dimension(0, 15)));
        container.add(datePanel);
        container.add(Box.createRigidArea(new Dimension(0, 15)));
        container.add(realisateurPanel);
        container.add(Box.createRigidArea(new Dimension(0, 15)));
        container.add(acteursPanel);
        container.add(Box.createRigidArea(new Dimension(0, 15)));


        add(container);

        // submit
        insererFilm = new Button("ressources/images/button.png", "Inserer Film");
        JPanel insererPanel = new JPanel(new GridBagLayout());
        insererPanel.add(insererFilm);
        add(insererPanel, BorderLayout.SOUTH);



    }

}
