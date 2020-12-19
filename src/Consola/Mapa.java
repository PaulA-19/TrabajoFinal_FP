package Consola;


import java.io.Serializable;
import java.util.*;

import Soldado.Soldado;

public class Mapa implements Serializable{

	// Para el random
	private static Random rd = new Random();

	// Datos de Clase
	private static int cantidad = 0;

	// Puede tener Ejercitos y Soldado
	private UnidadesDeMapa[][] tabla = new UnidadesDeMapa[10][10];
	private String tipo;

	// Constructores -------------------------------
	public Mapa() {

	}

	public Mapa(String tipo) {
		this.tipo = tipo;
		cantidad++;
	}

	public Mapa(String tipo, Mapeable unidades1) {
		this.tipo = tipo;
		for (UnidadesDeMapa unidad : unidades1.getUnidades()) {
			addUnidad(unidad);
		}
		cantidad++;

	}

	public Mapa(String tipo, Mapeable unidades1, Mapeable unidades2) {
		this.tipo = tipo;
		for (UnidadesDeMapa unidad : unidades1.getUnidades()) {
			addUnidad(unidad);
		}

		for (UnidadesDeMapa unidad : unidades2.getUnidades()) {
			addUnidad(unidad);
		}
		cantidad++;
	}

	// Metodos propios de la Clase

	public void addUnidad(UnidadesDeMapa unidad) {
		if (unidad.getFila() < 0 && unidad.getColumna() < 0) { // inicialmente el valor es -1,-1
			int[] libre = getPosicionLibre(); // fila, columna libre
			unidad.setFila(libre[0]);
			unidad.setColumna(libre[1]);
		}

		int fila = unidad.getFila();
		int columna = unidad.getColumna();
		tabla[fila][columna] = unidad;

	}

	public void addUnidad(int fila, int columna, UnidadesDeMapa unidad) {

		tabla[fila][columna] = unidad;

	}

	public void eliminarUnidad(UnidadesDeMapa unidad) {
		if (unidad != null) {

			int fila = unidad.getFila();
			int columna = unidad.getColumna();
			tabla[fila][columna] = null;
		}

	}

	public int[] getPosicionLibre() { // fila, columna
		int fila, columna;
		int[] posiciones = new int[2]; // para que retorne
		do {
			fila = rd.nextInt(tabla.length);
			columna = rd.nextInt(tabla[0].length);

		} while (!espacioLibre(fila, columna));
		posiciones[0] = fila;
		posiciones[1] = columna;
		return posiciones;
	}

	public boolean espacioLibre(int fila, int columna) {
		return (tabla[fila][columna] == null);
	}

	public void limpiarTabla() {
		for (int i = 0; i < tabla.length; i++) {
			for (int j = 0; j < tabla[i].length; j++) {
				tabla[i][j] = null;
			}
		}

	}

	public UnidadesDeMapa getUnidad(int fila, int columna) {
		return tabla[fila][columna];

	}

	public void mostrarTabla() { // OJO
		for (int j = 1; j <= tabla.length; j++) {
			System.out.print(" " + j + " ");

		}
		System.out.println();

		for (int j = 0; j < tabla.length; j++) {
			System.out.print(" _");
		}
		System.out.println();

		for (int i = 0; i < tabla.length; i++) {

			for (int j = 0; j < tabla[i].length; j++) {

				System.out.print("|");
				if (tabla[i][j] == null) {
					System.out.print("_");
				} else {
					UnidadesDeMapa ejer = tabla[i][j];
					System.out.print(ejer.getNameReino().charAt(0));
				}
			}

			System.out.println("| " + (i + 1));
		}
		System.out.println("Leyenda\n<#Solados> -<#NivelVidaTotal><InicialReino>\n");

	}

	// Get and Set
	public UnidadesDeMapa[][] getTabla() {
		return tabla;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTabla(UnidadesDeMapa[][] tabla) {
		this.tabla = tabla;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
