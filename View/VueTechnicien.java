package View;

import Controller.Controller;
import Model.Film;
import Patterns.Observateur;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueTechnicien extends Vue {

    private JTable films;
    private DefaultTableModel filmsModel;
    private Button suppFilmBtn;
    private Button insererFilmBtn;

    public VueTechnicien(Controller controller) {
        super(controller);

        setBackground(Color.WHITE);

        // creation du films model/pane
        filmsModel = new DefaultTableModel(new Object[] {"titre", "genres", "date", "realisateur", "acteurs"}, 0);
        films = new JTable(filmsModel);
        initTableModel();
        JScrollPane filmsPane = new JScrollPane(films);

        // ajouter/supprimer buttons
        suppFilmBtn = new Button("ressources/images/button-thick.png", "-");
        insererFilmBtn = new Button("ressources/images/button-thick.png", "+");
        insererFilmBtn.setId(NavigationListener.GESTION);
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionsPanel.add(insererFilmBtn);
        actionsPanel.add(suppFilmBtn);

        // films container
        JPanel filmsContainer = new JPanel(new BorderLayout());
        filmsContainer.add(filmsPane);
        filmsContainer.add(actionsPanel, BorderLayout.SOUTH);


        setListners();
        add(filmsContainer);

    }

    private void initTableModel() {
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
                film.getActeurs().toString()
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
                // delete film using controller
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

    }
}
