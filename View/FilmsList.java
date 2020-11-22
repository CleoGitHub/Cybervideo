package View;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.Film;

public class FilmsList extends JPanel {
	private ArrayList<Film> films;
	private ArrayList<FilmLine> filmLines = new ArrayList<>();
	private Controller c;
	
	public FilmsList(ArrayList<Film> films, Controller c) {
		super(new StackLayout());
		this.films = films;
		this.c = c;
		
		for(Film film : films) {
			addFilm(film);
		}
	}
		
	public void addFilm(Film f) {
		FilmLine fl = new FilmLine(f, c);
		filmLines.add(fl);
		add(fl);
		add(Box.createRigidArea(new Dimension(0, 5)));
	}
	
	public void setFilms(ArrayList<Film> films) {
		removeAll();
		this.films.clear();
		filmLines.clear();
		for(Film film : films) {
			addFilm(film);
		}
		revalidate();
		repaint();
	}
}
