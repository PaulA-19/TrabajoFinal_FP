package Soldado;

import java.util.Random;

public class Soldado {

	// Datos de clase
	private static int cantidad = 0;

	// Para el random
	private static Random rd = new Random();

	// Datos Personales
	private String name;
	private int nivelAtaque;
	private int nivelDefensa;
	private int nivelVida;
	protected int nivelActual;

	// Datos generales
	private int id;
	private int fila;
	private int columna;
	private boolean vive = true;
	private String nameReino;
	private String nameEjercito;
	private String tipo; // tipo de soldado
	private char simbolo; // Inicial del tipo de soldado

	// Opcionales
	private int velocidad;
	private char actitud = 'D'; // Tres posibles casos , puede ser char (D,O,H)
	private boolean atacar;
	private char simboloEjercito; // Inicial del ejercito

	// Constructores---------------------
	public Soldado() {
	}

	public Soldado(String tipo, String nameReino, String nameEjercito, int cantidad) {
		Random rd = new Random();
		this.name = tipo + "_" + Integer.toString(cantidad);
		this.tipo = tipo;
		this.id = Soldado.cantidad;
		this.nameReino = nameReino;
		this.nameEjercito = nameEjercito;
//		this.simboloEjercito = reino.toUpperCase().charAt(0);
		this.simbolo = tipo.toUpperCase().charAt(0);
		Soldado.cantidad++;
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

	// --------------------------------------
	@Override
	public String toString() {
		return "Nombre: " + name + "\tNivel de vida: " + getNivelVida() + "\tNive de ataque: " + nivelAtaque
				+ "\tNive de defensa: " + nivelDefensa + "\tPosicion: " + "(" + (getFila() + 1) + ", "
				+ (getColumna() + 1) + ")";
	}

	// Metodos Opcionales ----------------------------------------------

//	public abstract void atacar();
//
//	public abstract void defender();

	public void avanzar(int valor) { // podemos ponder un parametro
		velocidad += valor;
	}

	public void retroceder() {
		if (velocidad > 0) {
			setVelocidad(0);
			setActitud('d');
		} else {
			velocidad--; // retrocede con -1
		}
	}

	public void aumentarVida1() {
		nivelVida++;
	}

	public void hiur() {
		avanzar(2);
	}

	public void morir() {
		vive = false;
	}

	public boolean isVive() {
		return vive;
	}

	public boolean isAtacar() {
		return atacar;
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

	public int getNivelActual() {
		return nivelActual;
	}

	public int getId() {
		return id;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
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

	public int getVelocidad() {
		return velocidad;
	}

	public char getActitud() {
		return actitud;
	}

	public char getSimboloEjercito() {
		return simboloEjercito;
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

	public void setNivelActual(int nivelActual) {
		this.nivelActual = nivelActual;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public void setVive(boolean vive) {
		this.vive = vive;
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

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void setActitud(char actitud) {
		this.actitud = actitud;
	}

	public void setAtacar(boolean atacar) {
		this.atacar = atacar;
	}

	public void setSimboloEjercito(char simboloEjercito) {
		this.simboloEjercito = simboloEjercito;
	}

}