package Consola;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {

	ArrayList<Reino> reinos = new ArrayList<Reino>();
	Mapa tabla = new Mapa();

	public Game() {
		reinos.add(new Reino("Reino1"));
		reinos.add(new Reino("Reino2"));
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

	@Override
	public String toString() {
		String text = "";
		for (Reino reino : reinos) {
			text += reino.toString();
		}
		return text;
	}
}
