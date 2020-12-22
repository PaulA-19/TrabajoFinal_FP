package Consola;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Soldado.Soldado;

public class Reino implements Mapeable, Serializable {

	// Datos de clase
	private static int cantidad = 0;

	// Para el random
	private static Random rd = new Random();

	// Datos Personales
	private ArrayList<UnidadesDeMapa> ejercitos = new ArrayList<UnidadesDeMapa>();
	private String name;
	private Color c;
	private char simbolo;
	private char actitud = UnidadesDeMapa.NEUTRO;

	// Datos generales
	private int id; // ojo
	private boolean vive = true;
	private String tipo; // OJO

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
	private void llenarAutomatico() {
		Random rd = new Random();
		int cantidad = rd.nextInt(11); // [0,10]
		for (int i = 0; i < cantidad; i++) {
			ejercitos.add(new Ejercito(name, c));
		}
	}

	public void actualizar(String name, Color c, char actitud) {
		setName(name);
		setC(c);
		setActitud(actitud);
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;

			ejer.actualizar(name, c, actitud);
		}
	}

	public void addNewEjercito() {
		ejercitos.add(new Ejercito(name, c));
	}

	public int nivelVida() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;
			total += ejer.nivelVida();
		}
		return total;
	}

	public int nivelVidaVivos() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;
			if (ejer.isVive()) {
				total += ejer.nivelVida();
			}
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

	public double promedioNivelVidaVivos() {
		int total = nivelVidaVivos();
		return ((double) total / getNumEjercitosVivos());
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

	public int getNumEjercitosVivos() {
		int cantidad = 0;
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;
			if (ejer.isVive()) {
				cantidad++;
			}
		}
		return cantidad;
	}

	public int getNumSoldados() {
		int total = 0;
		for (UnidadesDeMapa unidad : ejercitos) {
			Ejercito ejercito = (Ejercito) unidad;
			total += ejercito.numSoldados();
		}
		return total;
	}

	public int getNumSoldadosVivos() {
		int total = 0;
		for (UnidadesDeMapa unidad : ejercitos) {
			Ejercito ejercito = (Ejercito) unidad;
			total += ejercito.numSoldadosVivos();
		}
		return total;
	}

	public void beneficiado() {
		for (UnidadesDeMapa ejercito : ejercitos) {
			Ejercito ejer = (Ejercito) ejercito;
			ejer.beneficiado();
		}
	}

	@Override
	public String toString() {
		String text = "Nombre: " + name + "\n";
		for (UnidadesDeMapa unidad : ejercitos) {
			Ejercito ejer = (Ejercito) unidad;
			text += ejer.toString();
		}
		return text;
	}

	public String[] nombresEjercitos() {
		String[] nombres = new String[ejercitos.size()];
		int i = 0;
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;
			nombres[i] = ejer.getName();
			i++;
		}

		return nombres;
	}

	public Ejercito obtenerEjer(String name) {
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;

			if (ejer.getName().equalsIgnoreCase(name)) {
				return ejer;
			}
		}
		return null;
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
		if (unidad != null) {
			ejercitos.remove(unidad);

		}
	}

	@Override
	public String mostrarDatos() {
		String text = "";
		text += "Reino: " + name + "\n";
		text += "Cantidad Total de Ejercitos: " + getNumEjercitos() + "\n";
		text += "Cantidad Total de Ejercitos VIVOS: " + getNumEjercitosVivos() + "\n";
		text += "Total Soldados: " + getNumSoldados() + "\n";
		text += "Total Soldados VIVOS: " + getNumSoldadosVivos() + "\n";
		text += "Total Nivel de Vida Actual: " + nivelVidaActual() + "\n\n";

		text += "Nivel Ataque: " + nivelAtaque() + "\n";
		text += "Nivel Defensa: " + nivelDefensa() + "\n";
		return text;

	}

	public Color getColor() {
		return c;
	}

	// Set and Get --------------------------

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public boolean isVive() {

		if (getUnidades().size() == 0) {
			return false;
		}

		boolean posibleValor = false;
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;
			posibleValor = posibleValor || ejer.isVive();
		}

		return posibleValor;
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

	public String datosPuntuales() {
		String text = String.format("%02d", getUnidades().size()) + "-"
				+ (String.format("%02d", promedioNivelVida()) + "-" + (String.format("%02d", promedioNivelAtaque())));

		return text;
	}

	public char getActitud() {
		return actitud;
	}

	public void setActitud(char actitud) {
		for (UnidadesDeMapa unidadesDeMapa : ejercitos) {
			Ejercito ejer = (Ejercito) unidadesDeMapa;
			ejer.setActitud(actitud);
		}

		this.actitud = actitud;
	}

}
