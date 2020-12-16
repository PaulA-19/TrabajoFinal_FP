package Consola;

import Soldado.Soldado;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Ejercito extends UnidadesDeMapa implements Mapeable {

	// Datos de clase
	private static int cantidad = 0;

	// Para el random
	private static Random rd = new Random();

	// Datos Personales
	private ArrayList<UnidadesDeMapa> soldados = new ArrayList<UnidadesDeMapa>();
	private String name;
	private int id;
	private String nameReino;
	private char simbolo;

	// Constructores
	public Ejercito(String nameReino, Color c) {
		this(nameReino, c, "Ejercito_" + cantidad);
	}

	public Ejercito(String nameReino, Color c, String name) {
		this.nameReino = nameReino;
		setColor(c);
		this.name = name;
		simbolo = name.toUpperCase().charAt(0);
		llenarAutomatico();
		cantidad++;
	}

	// Metodos propios de la Clase

	// falta mostrar datos

	public void llenarAutomatico() { // falta arreglar
		int cantidad = rd.nextInt(11); // [0,10]
		for (int i = 0; i < cantidad; i++) {
			soldados.add(new Soldado(nameReino, name, getColor()));
		}
	}

	public void addNewSoldados() {// Soldado abstracto
		soldados.add(new Soldado(nameReino, name, getColor()));
	}

	public int nivelVida() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;
			total += sol.getNivelVida();
		}
		return total;
	}

	public int nivelVidaActual() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;
			total += sol.getNivelVidaActual();
		}
		return total;
	}

	public int nivelAtaque() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;
			total += sol.getNivelAtaque();
		}
		return total;
	}

	public int nivelDefensa() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;
			total += sol.getNivelDefensa();
		}
		return total;
	}

	public double promedioAtaque() {
		int total = nivelAtaque();
		return ((double) total / getUnidades().size());
	}

	public double promedioDefensa() {
		int total = nivelDefensa();
		return ((double) total / getUnidades().size());
	}

	public double promedioNivelVida() {
		int total = nivelVida();
		return ((double) total / getUnidades().size());
	}

	public double promedioNivelVidaActual() {
		int total = nivelVidaActual();
		return ((double) total / getUnidades().size());
	}

	@Override
	public String toString() {
		String text = "nombre Ejercito: " + name + "\n";
		for (Object object : soldados) {
			Soldado sol = (Soldado) object; // casting a soldado
			text += sol.toString();
		}
		return text;
	}

	public void beneficiado() {
		for (UnidadesDeMapa soldado : soldados) {
			Soldado sol = (Soldado) soldado;
			sol.beneficiado();
		}
	}

	public int numSoldados() {
		return soldados.size();
	}

	public void limpiarEjercito() {
		soldados.clear();
	}

	public void derrrotado() {
		setVive(false);
	}

	// Mapeable
	@Override
	public ArrayList<UnidadesDeMapa> getUnidades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUnidad(UnidadesDeMapa unidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUnidad(UnidadesDeMapa unidad) {
		// TODO Auto-generated method stub

	}

	// UnidadesDeMapa
	@Override
	public int sumaVida() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumaVidaActual() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumaAtaque() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumaDefensa() {
		// TODO Auto-generated method stub
		return 0;
	}

	// Get and Set
	public String getName() {
		return name;
	}

	public String getNameReino() {
		return nameReino;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSoldados(ArrayList<UnidadesDeMapa> soldados) {
		this.soldados = soldados;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNameReino(String nameReino) {
		this.nameReino = nameReino;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

}
