package Consola;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Reino> reinos = new ArrayList<Reino>();
	private Mapa tabla = new Mapa();
	private static String[] colores = { "rojo", "verde", "azul", "amarillo" };
	private static ArrayList<String> reinosPosibles = new ArrayList<String>();
	private static ArrayList<Color> coloresPosibles = new ArrayList<Color>();
	private static Game game = null;
	private static boolean guardado = false;
	private static String ultimoGuardado = "";

	public Game() {
		llenarPosibleColores();
		llenarPosibleReinos();
		reinos.add(new Reino(elegirTipo(reinosPosibles), elegirColor(coloresPosibles)));
		reinos.add(new Reino(elegirTipo(reinosPosibles), elegirColor(coloresPosibles)));

	}

	public void actualizar() {
		getReino1().actualizar();
		getReino2().actualizar();

	}

	private static void llenarPosibleReinos() {
		reinosPosibles.add("Inglaterra");
		reinosPosibles.add("Francia");
		reinosPosibles.add("Sacro Imperio Romano Germanico");
		reinosPosibles.add("Aragon-Castilla");
		reinosPosibles.add("Moros");

	}

	private static void llenarPosibleColores() {

		coloresPosibles.add(Color.red);
		coloresPosibles.add(Color.green);
		coloresPosibles.add(Color.blue);
		coloresPosibles.add(Color.yellow);

	}

	public static String elegirTipo(ArrayList<String> posibles) {
		Random rd = new Random();
		int opcion = rd.nextInt(posibles.size());
		return posibles.remove(opcion);
	}

	public static Color elegirColor(ArrayList<Color> posibles) {
		Random rd = new Random();
		int opcion = rd.nextInt(posibles.size());
		return posibles.remove(opcion);
	}

	public Game(int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			reinos.add(new Reino("Reino_" + i));
		}

	}

	public ArrayList<Reino> getReinos() {
		return reinos;
	}

	public static void guardarJuego(String name) {
		ObjectOutputStream fileOut;
		try {
			fileOut = new ObjectOutputStream(new FileOutputStream("./games/" + name));
			System.out.println(Game.getGame());
			Game g = Game.getGame();
			fileOut.writeObject(g);
			System.out.println("Todo bien");
			fileOut.close();
			JOptionPane.showMessageDialog(null, "Guardado exitosamente");
			guardado = true;
			ultimoGuardado = name;

		} catch (Exception e) {
			e.getMessage();
			JOptionPane.showMessageDialog(null, "Ocurrio un error, vuelve a intentarlo:" + e.getMessage());
		}
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

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Game.game = game;
	}

	public static String[] getColores() {
		return colores;
	}

	public static boolean isGuardado() {
		return guardado;
	}

	public static String getUltimoGuardado() {
		return ultimoGuardado;
	}

	public static void setColores(String[] colores) {
		Game.colores = colores;
	}

	public static void setGuardado(boolean guardado) {
		Game.guardado = guardado;
	}

	public static void setUltimoGuardado(String ultimoGuardado) {
		Game.ultimoGuardado = ultimoGuardado;
	}

}
