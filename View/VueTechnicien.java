package View;

import Controller.Controller;
import Model.Film;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueTechnicien extends Vue {

    private JTable films;
    private DefaultTableModel filmsModel;
    private Button suppFilmBtn;
    private Button insererFilmBtn;
    private Button refreshBtn;

    public VueTechnicien(Controller controller) {
        super(controller);

        setBackground(Color.WHITE);

        // creation du films model/pane
        filmsModel = new DefaultTableModel(new Object[] {"titre", "genres", "date", "realisateur", "acteurs", "DVDs disponible"}, 0);
        films = new JTable(filmsModel);
        chargerFilms();
        JScrollPane filmsPane = new JScrollPane(films);

        // ajouter/supprimer buttons
        suppFilmBtn = new Button("ressources/images/button-thick.png", "-");
        insererFilmBtn = new Button("ressources/images/button-thick.png", "+");
        refreshBtn = new Button("ressources/images/button-thick.png", "Actualiser");
        refreshBtn.addMouseListener(new MouseInputAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		refreshModel();
        	}
        });

        insererFilmBtn.setId(NavigationListener.GESTION);
        JPanel actionsPanel = new JPanel(new BorderLayout());
        JPanel rightActionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel leftActionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionsPanel.add(rightActionPanel, BorderLayout.EAST);
        actionsPanel.add(leftActionPanel, BorderLayout.WEST);
        rightActionPanel.add(insererFilmBtn);
        rightActionPanel.add(suppFilmBtn);
        leftActionPanel.add(refreshBtn);

        // films container
        JPanel filmsContainer = new JPanel(new BorderLayout());
        filmsContainer.add(filmsPane);
        filmsContainer.add(actionsPanel, BorderLayout.SOUTH);


        setListners();
        add(filmsContainer);

    }

    public void refreshModel() {
		filmsModel.setRowCount(0);
		chargerFilms();
    }

    private void chargerFilms() {
        ArrayList<Film> films = getController().getFilms();
        for (Film film : films)
            insererFilm(film);
    }

    private void insererFilm(Film film) {
        filmsModel.addRow(new Object[] {
                film.getTitre(),
                film.getGenres().toString(),
                film.getDate().toString(),
                film.getRealisateur().getNom(),
                film.getActeurs().toString(),
                film.getDvdsDisponiblesCount()
        });
    }

    private void supprimerSelectionnes() {
        int filmSelectionnes[] = films.getSelectedRows();
        int nb_films = filmSelectionnes.length;

        if (filmSelectionnes.length == 0)
            return;

        int input = 0;
        if (nb_films > 1) {
            input = JOptionPane.showConfirmDialog(
                    null,
                    "Voulez-vous supprimer " + nb_films + " éléments ?");
        }
        if (input == 0)
            for (int i = 0; i < nb_films; i++) {
                filmsModel.removeRow(filmSelectionnes[i]-i);
                getController().supprimerFilm(filmSelectionnes[i]-i);
            }
    }

    private void setListners() {
        // suppBtn listner
        MouseListener suppListner = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                supprimerSelectionnes();
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
        suppFilmBtn.addMouseListener(suppListner);

        // insBtn listner
        MouseListener listener = new NavigationListener(getController());
        insererFilmBtn.addMouseListener(listener);

        // Jtable listner (modifier films)
        KeyListener kListner = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int film = films.getSelectedRow();
                    // TODO: update film
                    System.out.println(film);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        films.addKeyListener(kListner);
    }
}
