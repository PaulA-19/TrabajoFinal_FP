package Consola;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class Game {

	private ArrayList<Reino> reinos = new ArrayList<Reino>();
	private Mapa tabla = new Mapa();

	public Game() {
		reinos.add(new Reino("Reino1", Color.blue));
		reinos.add(new Reino("Reino2", Color.red));
	}

	public Game(int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			reinos.add(new Reino("Reino_" + i));
		}

	}

	public ArrayList<Reino> getReinos() {
		return reinos;
	}

	public void setReinos(ArrayList<Reino> reinos) {
		this.reinos = reinos;
	}

	public Reino getReino1() {
		return getReinos().get(0);
	}

	public Reino getReino2() {
		return getReinos().get(1);
	}

	@Override
	public String toString() {
		String text = "";
		for (Reino reino : reinos) {
			text += reino.toString();
		}
		return text;
	}

	public Mapa getTabla() {
		return tabla;
	}

	public void setTabla(Mapa tabla) {
		this.tabla = tabla;
	}

}
