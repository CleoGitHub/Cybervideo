package View;

import Controller.Controller;
import Patterns.Observateur;

import javax.swing.*;
import java.awt.*;

public class VueTechnicien extends Vue {
    public VueTechnicien(Controller controller) {
        super(controller);
        setBackground(Color.RED);

    }
}
