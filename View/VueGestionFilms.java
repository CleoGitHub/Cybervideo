package View;

import Controller.Controller;
import Model.CyberVideo;
import Model.Genre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VueGestionFilms extends Vue {

    private JPanel container;
    private JComboBox<String> realisateursBox;
    private JComboBox<String> acteursBox;
    private DefaultComboBoxModel<String> realisateursModel;
    private DefaultComboBoxModel<String> acteursModel;
    private JList acteurSelectionnes;
    private DefaultListModel<String> acteurSelecModel;
    private ArrayList<JCheckBox> genresList;
    private JTextField titreField;
    private JTextField dateField;
    private JTextField nbDVDsFiels;
    private Button insererActeur;
    private Button suppActeur;
    private Button selecActeur;
    private Button annulerActeur;
    private Button insererRealisateur;
    private Button suppRealisateur;
    private Button insererFilm;
    private Button incDVDbtn;
    private Button decDVDbtn;

    private int dvds;

    public VueGestionFilms(Controller controller, CyberVideo model) {
        super(controller, model);




        // super-contianer
        container = new JPanel(new StackLayout());

        // titre & date
        JPanel titrePanel = creerTitrePanel();
        ajouterPanel(titrePanel);

        JPanel datePanel = creerDatePanel();
        ajouterPanel(datePanel);

        // realisateurs panel
        JPanel realisateurPanel = creerRealisateurPanel();
        ajouterPanel(realisateurPanel);

        // acteurs panel
        JPanel acteursPanel = creerActeursPanel();
        ajouterPanel(acteursPanel);

        // DVDs panel
        JPanel dvdsPanel = creerDVDpanel();
        ajouterPanel(dvdsPanel);

        // genresPanel
        JPanel genresPanel = creerGenrePanel();
        ajouterPanel(genresPanel);

        // ajout du container dans la VurGestionFilms
        add(new JScrollPane(container));

        // submit button
        insererFilm = new Button("ressources/images/button.png", "Inserer Film");
        JPanel insererPanel = new JPanel(new GridBagLayout());
        insererPanel.add(insererFilm);
        add(insererPanel, BorderLayout.SOUTH);

        setListners();
    }

    private JPanel creerTitrePanel() {
        titreField = new JTextField();
        JPanel titrePanel = new JPanel(new BorderLayout());
        titrePanel.add(new JLabel("Titre: "), BorderLayout.NORTH);
        titrePanel.add(titreField);

        return titrePanel;
    }

    private JPanel creerDatePanel() {
        dateField = new JTextField();
        JPanel datePanel = new JPanel(new BorderLayout());
        datePanel.add(new JLabel("Date: (\"dd/MM/yyyy\")"), BorderLayout.NORTH);
        datePanel.add(dateField);

        return datePanel;
    }

    private JPanel creerRealisateurPanel() {
        ArrayList<String> realisateurs = controller.getRealisateurs();
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

        return realisateurPanel;
    }

    private JPanel creerActeursPanel() {
        ArrayList<String> acteurs = controller.getActeurs();
        acteursModel = new DefaultComboBoxModel<String>(acteurs.toArray(new String[acteurs.size()]));
        acteursBox = new JComboBox<>(acteursModel);

        insererActeur = new Button("ressources/images/button-thick.png", "+");
        suppActeur = new Button("ressources/images/button-thick.png", "-");
        JPanel acteursButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acteursButtons.add(insererActeur);
        acteursButtons.add(suppActeur);

        acteurSelecModel = new DefaultListModel<>();
        acteurSelectionnes = new JList(acteurSelecModel);
        acteurSelectionnes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

        return acteursPanel;
    }

    private JPanel creerDVDpanel() {
        nbDVDsFiels = new JTextField("10");
        nbDVDsFiels.setEditable(false);
        incDVDbtn = new Button("ressources/images/button-thick.png", "+");
        decDVDbtn = new Button("ressources/images/button-thick.png", "-");
        JPanel dvdsButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        dvdsButtons.add(incDVDbtn);
        dvdsButtons.add(decDVDbtn);
        JPanel dvdsPanel = new JPanel(new BorderLayout());
        dvdsPanel.add(new JLabel("quantité de DVDs"), BorderLayout.NORTH);
        dvdsPanel.add(nbDVDsFiels);
        dvdsPanel.add(dvdsButtons, BorderLayout.EAST);

        return dvdsPanel;
    }

    private JPanel creerGenrePanel() {
        genresList = new ArrayList<>();
        Genre[] genres = Genre.values();
        JPanel genresContainer = new JPanel(new GridLayout(0, 3));
        for (Genre genre : genres) {
            JCheckBox genreBox = new JCheckBox(genre.toString());
            genresList.add(genreBox);
            genresContainer.add(genreBox);
        }
        JPanel genresPanel = new JPanel(new BorderLayout());
        genresPanel.add(new JLabel("Genres:"), BorderLayout.NORTH);
        genresPanel.add(genresContainer);

        return genresPanel;

    }

    private void ajouterPanel(JPanel panel) {
        container.add(Box.createRigidArea(new Dimension(0, 15)));
        container.add(panel);
    }

    private void ajouterNouvActeur(String nom) {
        getController().ajouterNouvActeur(nom);
        acteursModel.addElement(nom);
    }

    private void supprimerActeur(String nom) {
        getController().supprimerActeur(nom);
        acteursModel.removeElement(nom);
    }

    private void ajouterNouvRealisateur(String nom) {
        getController().ajouterNouvRealisateur(nom);
        realisateursModel.addElement(nom);
    }

    private void supprimerRealisateurr(String nom) {
        getController().supprimerRealisateur(nom);
        realisateursModel.removeElement(nom);
    }

    private void clearInputs() {
        titreField.setText("");
        dateField.setText("");
        acteurSelecModel.clear();
        nbDVDsFiels.setText("10");
    }
    
    private String lireNom(String msg) {
        String nom = JOptionPane.showInputDialog(msg);
        return nom;
    }

    private void selectionnerActeur() {
        String nom = (String)acteursBox.getSelectedItem();
        if (!acteurSelecModel.contains(nom))
            acteurSelecModel.addElement(nom);
    }

    private void annulerActeurSelectionne() {
        String nom = (String)acteurSelectionnes.getSelectedValue();

        if (acteurSelecModel.contains(nom))
            acteurSelecModel.removeElement(nom);
    }

    private void insererNouvFilm() throws ParseException {
        String titre, realisateur;
        int nb_acteurs = acteurSelecModel.getSize();
        String[] acteurs = new String[nb_acteurs];
        for (int i = 0; i < nb_acteurs; i++)
            acteurs[i] = acteurSelecModel.getElementAt(i);
        int dvds = Integer.parseInt(nbDVDsFiels.getText());
        titre = titreField.getText();
        realisateur = (String)realisateursBox.getSelectedItem();
        LocalDate date = LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
        Boolean[] genres = new Boolean[Genre.values().length];
        int i = 0;
        for (JCheckBox genre : genresList)
            genres[i++] = genre.isSelected();

        getController().ajouterFilm(
                titre,
                realisateur,
                acteurs,
                genres,
                date,
                dvds
        );

        // clear
        clearInputs();
        Dialog.showSuccess("Film ajouté !");

    }

    private void incrementerDVDs() {
        int nbdvds = Integer.parseInt(nbDVDsFiels.getText()) + 1;
        nbDVDsFiels.setText(Integer.toString(nbdvds));
    }

    private void decrementerDVDs() {
        int nbdvds = Integer.parseInt(nbDVDsFiels.getText());
        if (nbdvds > 1)
            nbdvds--;
        nbDVDsFiels.setText(Integer.toString(nbdvds));
    }
    
    private void setListners() {
        MouseListener listner = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object source = e.getSource();
                if (source == insererActeur) {
                    String nom = lireNom("Saiser le nom de nouveau Acteur");
                    if (nom != null)
                        ajouterNouvActeur(nom);
                } else if (source == suppActeur) {
                    if (acteurSelecModel.getSize() > 0)
                        supprimerActeur((String)acteursBox.getSelectedItem());
                } else if (source == insererRealisateur) {
                    String nom = lireNom("Saiser le nom de nouveau Realisateur");
                    if (nom != null)
                        ajouterNouvRealisateur(nom);
                } else if (source == suppRealisateur) {
                    if (realisateursModel.getSize() > 0)
                        supprimerRealisateurr((String)realisateursBox.getSelectedItem());
                } else if (source == selecActeur) {
                    selectionnerActeur();
                } else if (source == annulerActeur) {
                    if (!acteurSelectionnes.isSelectionEmpty())
                        annulerActeurSelectionne();
                } else if (source == incDVDbtn) {
                    incrementerDVDs();
                } else if (source == decDVDbtn) {
                    decrementerDVDs();
                } else if (source == insererFilm) {
                    try {
                        if (!titreField.getText().isEmpty() && acteurSelecModel.size() > 0 && realisateursModel.getSize() > 0)
                            insererNouvFilm();
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        insererActeur.addMouseListener(listner);
        suppActeur.addMouseListener(listner);
        insererRealisateur.addMouseListener(listner);
        suppRealisateur.addMouseListener(listner);
        selecActeur.addMouseListener(listner);
        annulerActeur.addMouseListener(listner);
        insererFilm.addMouseListener(listner);
        incDVDbtn.addMouseListener(listner);
        decDVDbtn.addMouseListener(listner);
    }

}
