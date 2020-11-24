package View;

import Controller.Controller;
import Patterns.Observateur;

import java.awt.*;

public class VueGestionFilms extends Vue {

    public VueGestionFilms(Controller controller) {
        super(controller);
        setBackground(Color.CYAN);
    }

}
