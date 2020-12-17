package Consola;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import Soldado.Soldado;

public class Reino implements Mapeable {

	// Datos de clase
	private static int cantidad = 0;

	// Para el random
	private static Random rd = new Random();

	// Datos Personales
	private ArrayList<UnidadesDeMapa> ejercitos = new ArrayList<UnidadesDeMapa>();
	private String name;
	private Color c;

	// Datos generales
	private int id; // ojo
	private boolean vive = true;
	private String tipo; // OJO
	private char simbolo;

	// Constructores

	public Reino(String name) {
		this(name, null);

	}

	public Reino(Color c) {
		this("Reino_" + Integer.toString(cantidad), c);

	}

	public Reino(String name, Color c) {
		this.name = name;
		this.c = c;
		simbolo = name.toUpperCase().charAt(0);
		llenarAutomatico();
		cantidad++;
	}

	// Metodos propios de la Clase
	public void addNewEjercito() {
		ejercitos.add(new Ejercito(name, c));
	}

	private void llenarAutomatico() {
		Random rd = new Random();
		int cantidad = rd.nextInt(11); // [0,10]
		for (int i = 0; i < cantidad; i++) {
			ejercitos.add(new Ejercito(name, c));
		}
	}

	public String mostratDatos() {
		String text = "";
		text += "Reino: " + name + "\n";
		text += "Cantidad Total de Ejercitos: " + getNumEjercitos() + "\n";
		text += "Total Soldados: " + getNumSoldados() + "\n";
		text += "Total Nivel de Vida: " + nivelVida() + "\n";

		return text;

	}

	public int nivelVida() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;
			total += ejer.nivelVida();
		}
		return total;
	}

	public int nivelVidaActual() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;
			total += ejer.nivelVidaActual();
		}
		return total;
	}

	public int nivelAtaque() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;
			total += ejer.nivelAtaque();
		}
		return total;
	}

	public int nivelDefensa() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;
			total += ejer.nivelDefensa();
		}
		return total;
	}

	public double promedioNivelVida() {
		int total = nivelVida();
		return ((double) total / getUnidades().size());
	}

	public double promedioNivelVidaActual() {
		int total = nivelVidaActual();
		return ((double) total / getUnidades().size());
	}

	public double promedioNivelAtaque() {
		int total = nivelAtaque();
		return ((double) total / getUnidades().size());
	}

	public double promedioNivelDefensa() {
		int total = nivelDefensa();
		return ((double) total / getUnidades().size());
	}

	public int getNumEjercitos() {
		return ejercitos.size();
	}

	public int getNumSoldados() {
		int total = 0;
		for (UnidadesDeMapa unidad : ejercitos) {
			Ejercito ejercito = (Ejercito) unidad;
			total += ejercito.numSoldados();
		}
		return total;
	}

	public void beneficiado() {
		for (UnidadesDeMapa ejercito : ejercitos) {
			Ejercito ejer = (Ejercito) ejercito;
			ejer.beneficiado();
		}
	}

	// -----------

	@Override
	public String toString() {
		String text = "Nombre: " + name + "\n";
		for (UnidadesDeMapa unidad : ejercitos) {
			Ejercito ejer = (Ejercito) unidad;
			text += ejer.toString();
		}
		return text;
	}

	// Interface Mapeable
	@Override
	public void addUnidad(UnidadesDeMapa unidad) {
		ejercitos.add(unidad);

	}

	@Override
	public ArrayList<UnidadesDeMapa> getUnidades() {
		return ejercitos;
	}

	@Override
	public void deleteUnidad(UnidadesDeMapa unidad) {
		ejercitos.remove(unidad);
	}

	// Set and Get

	public String getName() {
		return name;
	}

	public Color getC() {
		return c;
	}

	public int getId() {
		return id;
	}

	public boolean isVive() {
		return vive;
	}

	public String getTipo() {
		return tipo;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setEjercitos(ArrayList<UnidadesDeMapa> ejercitos) {
		this.ejercitos = ejercitos;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVive(boolean vive) {
		this.vive = vive;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

}