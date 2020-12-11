package test;

import java.util.Random;

import Consola.Batallar;

public abstract class Soldado implements Batallar{

	private static Random rd = new Random();

	// Atributos de clase
	public static int cant = 0;
	public static int ID = 0;

	// Atributos de instancia
	private String nombre;
	protected int nivelAtaque;
	protected int nivelDefensa;
	protected int nivelVida;
	private int velocidad;
	private char actitud = 'D'; // Tres posibles casos , puede ser char (D,O,H)
	private boolean vive = true;
	private int id;
	private boolean atacar;
	private int fila = -1;
	private int columna = -1;
	private String simbolo; // Inicial del tipo de soldado
	private char simboloEjercito; // Inicial del ejercito
	protected String reino;
	private String tipo; // tipo de soldado

	// Constructores---------------------
	public Soldado() {
	}

	public Soldado(String tipo, String nameReino, int cantidad) {
		Random rd = new Random();
		this.nombre = tipo + "_" + Integer.toString(cantidad);
		this.tipo = tipo;
		this.id = ID;
		this.reino = nameReino;
		this.simboloEjercito = reino.toUpperCase().charAt(0);
		this.simbolo = tipo.toUpperCase().substring(0, 1);
		this.simbolo = " " + simbolo; // formato para la tabla
		ID++;
		cant++;
	}

	// Batalla----------------
	public static Soldado[] batallar(Soldado s1, Soldado s2) { // (ganador perdedor)
		Soldado ganador, perdedor;
		int[] vidasBeneficiadas = beneficiadoBatalla(s1, s2);
		int vida1 = vidasBeneficiadas[0];
		int vida2 = vidasBeneficiadas[1];

		double total = vida1 + vida2;
		double vida1Decimal = vida1 / total;
		double vida2Decimal = vida2 / total;

		double elegido = Math.random();

		if (elegido < Math.min(vida1Decimal, vida2Decimal)) {
			if (Math.min(s1.getNivelVida(), s2.getNivelVida()) == s1.getNivelVida()) {
				mostrarGanador(s1);
				ganador = s1;
				perdedor = s2;
			} else {
				mostrarGanador(s2);
				ganador = s2;
				perdedor = s1;
			}

		} else {
			if (Math.max(s1.getNivelVida(), s2.getNivelVida()) == s1.getNivelVida()) {
				mostrarGanador(s1);
				ganador = s1;
				perdedor = s2;
			} else {
				mostrarGanador(s2);
				ganador = s2;
				perdedor = s1;
			}
		}

		Soldado[] resultado = { ganador, perdedor };
		return resultado;
	}

	private static void mostrarGanador(Soldado sol) {
		System.out.println("Ganador ejercito: " + sol.getReino() + "\tNombre Soldado: " + sol.getNombre());

	}

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

	// Metodos Anteriores ----------------------------------------------

	public String toString() {
		return "Nombre: " + nombre + "\tNivel de vida: " + getNivelVida() + "\tNive de ataque: " + nivelAtaque
				+ "\tNive de defensa: " + nivelDefensa + "\tPosicion: " + "(" + (getFila() + 1) + ", "
				+ (getColumna() + 1) + ")";
	}

	public abstract void atacar();

	public abstract void defender();

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

	public static int getID() {
		return ID;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNivelDefensa() {
		return nivelDefensa;
	}

	public int getNivelVida() {
		return nivelVida;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public char getActitud() {
		return actitud;
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

	public String getSimbolo() {
		return simbolo;
	}

	public char getSimboloEjercito() {
		return simboloEjercito;
	}

	public String getReino() {
		return reino;
	}

	public String getTipo() {
		return tipo;
	}

	public static void setID(int iD) {
		ID = iD;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNivelDefensa(int nivelDefensa) {
		this.nivelDefensa = nivelDefensa;
	}

	public void setNivelVida(int nivelVida) {
		this.nivelVida = nivelVida;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void setActitud(char actitud) {
		this.actitud = actitud;
	}

	public void setVive(boolean vive) {
		this.vive = vive;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAtacar(boolean atacar) {
		this.atacar = atacar;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public void setSimboloEjercito(char simboloEjercito) {
		this.simboloEjercito = simboloEjercito;
	}

	public void setReino(String reino) {
		this.reino = reino;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
