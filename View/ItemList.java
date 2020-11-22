package View;

import java.awt.Dimension;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.DVD;
import Model.Film;

public class ItemList<T, E extends JPanel> extends JPanel {
	private ArrayList<T> items;
	private ArrayList<E> lines = new ArrayList<>();
	private Class<E> clazzLine;
	private Class<T> clazz;
	private Controller c;
	
	
	public ItemList(String desc, ArrayList<T> items, Controller c, Class<T> clazz, Class<E> clazzLine) {
		super(new StackLayout());
		this.items = items;
		this.c = c;
		this.clazz = clazz;
		this.clazzLine = clazzLine;

		JLabel label = new JLabel(desc);
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, 24));
		add(label);
		
		for(T item : items) {
			addItem(item);
		}
	}
	
	public E buildLine(T t, Controller c) {
		try {
			return clazzLine.getConstructor(clazz, Controller.class).newInstance(t, c);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
	public void addItem(T t) {
		E tl = buildLine(t, c);
		lines.add(tl);
		add(tl);
		add(Box.createRigidArea(new Dimension(0, 5)));
	}
	
	public void setItems(ArrayList<T> arrayOfT) {
		removeAll();
		this.items.clear();
		this.lines.clear();
		for(T t : arrayOfT) {
			addItem(t);
		}
		revalidate();
		repaint();
	}
}
