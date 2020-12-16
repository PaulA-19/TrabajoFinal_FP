package Soldado;

import java.awt.Color;
import java.util.Random;

import Consola.UnidadesDeMapa;

public class Soldado extends UnidadesDeMapa {

	// Actitudes
	private final static char ATAQUE = 'a';
	private final static char NEUTRO = 'n';
	private final static char DEFENZA = 'd';

	// Datos de clase
	private static int cantidad = 0;

	// Para el random
	private static Random rd = new Random();

	// Datos Personales
	private String name;
	private int nivelAtaque;
	private int nivelDefensa;
	private int nivelVida;
	private int nivelVidaActual;
	private char actitud = Soldado.NEUTRO;

	// Datos generales
	private int id;
	private String nameReino;
	private String nameEjercito;
	private String tipo; // tipo de soldado
	private char simbolo; // del reino y ejercito

	// Constructores---------------------
	public Soldado(String nameReino, String nameEjercito, Color c) {
		this(nameReino, nameEjercito, c, Soldado.NEUTRO);
	}

	public Soldado(String nameReino, String nameEjercito, Color c, char actitud) {
		this(nameReino, nameEjercito, c, actitud, "", 0);
	}

	public Soldado(String nameReino, String nameEjercito, Color c, char actitud, String tipo, int cantidadTipo) {
		this.name = tipo + "_" + Integer.toString(cantidadTipo);
		this.tipo = tipo;
		this.nameReino = nameReino;
		this.nameEjercito = nameEjercito;
		this.actitud = actitud;
		this.simbolo = nameEjercito.toUpperCase().charAt(0);
		cantidad++;
	}

	// Batalla---------------- Aun no echo

	private static int[] beneficiadoBatalla(Soldado s1, Soldado s2) {
		int[] vidas = { s1.getNivelVida(), s2.getNivelVida() };

		if ((s1 instanceof Caballero && s2 instanceof Arquero) || (s1 instanceof Arquero && s2 instanceof Caballero)) {
			System.out.println("Beneficiado Caballero");
			if (s1 instanceof Caballero) {
				vidas[0]++;
				if (s1 instanceof SuperCaballero) {
					vidas[0]++;
				}
			} else {
				vidas[1]++;
				if (s2 instanceof SuperCaballero) {
					vidas[1]++;
				}
			}

		} else if ((s1 instanceof Lancero && s2 instanceof Caballero)
				|| (s2 instanceof Lancero && s1 instanceof Caballero)) {
			if ((s1 instanceof Lancero) && !(s2 instanceof SuperCaballero)) {
				vidas[0]++;

			} else if (!(s1 instanceof SuperCaballero)) {
				vidas[1]++;
			}
		} else if ((s1 instanceof Arquero && s2 instanceof Lancero)
				|| (s2 instanceof Arquero && s1 instanceof Lancero)) {
			if (s1 instanceof Arquero) {
				vidas[0]++;

			} else {
				vidas[1]++;
			}

		} else if ((s1 instanceof Caballero && s2 instanceof Espadachin)
				|| (s2 instanceof Caballero && s1 instanceof Espadachin)) {
			// no se da restricciones para el caballero especial
			if (s1 instanceof Caballero) {
				vidas[0]++;

			} else {
				vidas[1]++;
			}

		} else if ((s1 instanceof Espadachin && s2 instanceof Lancero)
				|| (s1 instanceof Espadachin && s2 instanceof Lancero)) {
			if (s1 instanceof Espadachin) {
				vidas[0]++;
				if (s1 instanceof SuperEspadachin) {
					vidas[0]++;
				}
			} else {
				vidas[1]++;
				if (s2 instanceof SuperEspadachin) {
					vidas[1]++;
				}
			}

		} else if ((s1 instanceof SuperEspadachin && s2 instanceof Espadachin && !(s2 instanceof SuperEspadachin))
				|| (s2 instanceof SuperEspadachin && s1 instanceof Espadachin && !(s1 instanceof SuperEspadachin))) {
			if (s1 instanceof SuperEspadachin) {
				vidas[0]++;

			} else {
				vidas[1]++;
			}

		}

		else if ((s1 instanceof SuperCaballero && s2 instanceof Caballero && !(s2 instanceof SuperCaballero))
				|| (s2 instanceof SuperCaballero && s1 instanceof Caballero && !(s1 instanceof SuperCaballero))) {
			if (s1 instanceof SuperCaballero) {
				vidas[0]++;

			} else {
				vidas[1]++;
			}
		}

		return vidas;

	}

	// Metodos propios de Clase-----------------------
	public void beneficiado() {
		setNivelVidaActual(getNivelVidaActual() + 1);
	}

	public void morir() {
		setVive(false);
	}

	@Override
	public String toString() {
		return "Nombre: " + name + "\tNivel de vida: " + getNivelVida() + "\tNive de ataque: " + nivelAtaque
				+ "\tNive de defensa: " + nivelDefensa + "\tPosicion: " + "(" + (getFila() + 1) + ", "
				+ (getColumna() + 1) + ")";
	}

	// UnidadesDeMapa --------------------

	@Override
	public int sumaVida() {
		return getNivelVida();
	}

	@Override
	public int sumaAtaque() {
		return getNivelAtaque();
	}

	@Override
	public int sumaDefensa() {
		return getNivelDefensa();
	}

	@Override
	public int sumaVidaActual() {
		return getNivelVidaActual();
	}

	// ---------------- get and set --------------------------

	public String getName() {
		return name;
	}

	public int getNivelAtaque() {
		return nivelAtaque;
	}

	public int getNivelDefensa() {
		return nivelDefensa;
	}

	public int getNivelVida() {
		return nivelVida;
	}

	public int getNivelVidaActual() {
		return nivelVidaActual;
	}

	public char getActitud() {
		return actitud;
	}

	public int getId() {
		return id;
	}

	public String getNameReino() {
		return nameReino;
	}

	public String getNameEjercito() {
		return nameEjercito;
	}

	public String getTipo() {
		return tipo;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNivelAtaque(int nivelAtaque) {
		this.nivelAtaque = nivelAtaque;
	}

	public void setNivelDefensa(int nivelDefensa) {
		this.nivelDefensa = nivelDefensa;
	}

	public void setNivelVida(int nivelVida) {
		this.nivelVida = nivelVida;
	}

	public void setNivelVidaActual(int nivelVidaActual) {
		this.nivelVidaActual = nivelVidaActual;
	}

	public void setActitud(char actitud) {
		this.actitud = actitud;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNameReino(String nameReino) {
		this.nameReino = nameReino;
	}

	public void setNameEjercito(String nameEjercito) {
		this.nameEjercito = nameEjercito;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

}