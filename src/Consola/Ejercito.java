package Consola;

import Soldado.*;

import java.util.ArrayList;
import java.util.Random;

public class Ejercito implements Mapeable {

	// Datos de clase
	private static int cantidad = 0;

	// Para el random
	private static Random rd = new Random();

	// Datos Personales
	private ArrayList<Object> soldados = new ArrayList<Object>();
	private String name;
	private double promedioAtaque;
	private double promedioDefensa;
	private double promedioNivellVida;
	protected double promedioNivelActual;

	// Datos generales
	private int id;
	private int fila;
	private int columna;
	private boolean vive = true;
	private String nameReino;
	private String tipo; // tipo de soldado
	private char simbolo;

	public Ejercito(String nameEjercito) {
		name = "Ejercito_" + nameEjercito + Integer.toString(cantidad);

		cantidad++;
	}

	public void addSoldados() {
		soldados.add(new Soldado());
	}

	@Override
	public ArrayList<Object> getUnidades() {
		// TODO Auto-generated method stub
		return soldados;
	}

	@Override
	public String toString() {
		String text = "Ejercito name: " + nameEjercito + "\n";
		// TODO Auto-generated method stub
		for (Object soldado : soldados) {
			Soldado sol = (Soldado) soldado;
			text += sol.toString();
		}

		return text;
	}

}
