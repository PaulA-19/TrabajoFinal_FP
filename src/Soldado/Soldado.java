package Soldado;

import java.awt.Color;
import java.util.Random;

import Consola.Batalla;
import Consola.UnidadesDeMapa;
//falta implementar los ataques, de soldados

public abstract class Soldado extends UnidadesDeMapa implements Batalla {

	// Actitudes
	public final static char ATAQUE = 'a';
	public final static char NEUTRO = 'n';
	public final static char DEFENZA = 'd';

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

	private boolean atacar = false;
	private boolean defender = false;
	private Object cant;

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
		setColor(c);
		cantidad++;
	}

	// Batalla---------------- Aun no echo

	public static UnidadesDeMapa[] batalla(UnidadesDeMapa unidad1, UnidadesDeMapa unidad2) {

		Soldado ganador, perdedor;
		Soldado s1 = (Soldado) unidad1;
		Soldado s2 = (Soldado) unidad2;

		while (s1.isVive() && s2.isVive()) {
			pelear(s1, s2);
		}

		if (s1.isVive()) {
			ganador = s1;
			perdedor = s2;
		} else {
			ganador = s2;
			perdedor = s1;
		}

		ganador.actitudNormal();
		perdedor.actitudNormal();

		Soldado[] resultado = { ganador, perdedor };
		return resultado;

	}

	public static void pelear(Soldado s1, Soldado s2) {

		// IMplementar una metrtic PARA BATALLA
		s2.morir();

		System.out.println("Quitar 4");
		s1.quitarVidaDefensa(4);

		System.out.println(s1);
	}

	private static void mostrarGanador(Soldado sol) {
		System.out.println("Ganador ejercito: " + sol.getNameReino() + "\tNombre Soldado: " + sol.getName());

	}

	// Completar
	private static int[] beneficiadoBatalla(Soldado s1, Soldado s2) {
		int[] vidas = { s1.getNivelVidaActual(), s2.getNivelVidaActual() };

		if ((s1 instanceof Caballero && s2 instanceof Arquero) || (s1 instanceof Arquero && s2 instanceof Caballero)) {
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

	public void quitarVidaDefensa(int cant) {
		int nuevo = getNivelDefensa() - cant;
		setNivelDefensa(nuevo);

	}

	public void quitarVida(int cant) {
		int nuevo = getNivelVidaActual() - cant;
		setNivelVidaActual(nuevo);

	}

	public void morir() {
		setVive(false);
		setNivelVidaActual(0);
		setNivelDefensa(0);
	}

	@Override
	public String toString() {
		return "Nombre: " + name + "\nNivel de vida actual:\t" + getNivelVidaActual() + "\nNive de ataque:\t"
				+ getNivelAtaque() + "\nNive de defensa:\t" + getNivelDefensa();
	}

	// Batalla

	@Override
	public void actitudAtacar() {
		setNivelAtaque(getNivelAtaque() + 1);
		setNivelDefensa(getNivelDefensa() - 1);
		setAtacar(true);
	}

	@Override
	public void actitudDefender() {
		setNivelAtaque(getNivelAtaque() - 1);
		setNivelDefensa(getNivelDefensa() + 1);
		setDefender(true);
	}

	@Override
	public void actitudNormal() {

		if (atacar) {
			setNivelAtaque(getNivelAtaque() - 1);
			setNivelDefensa(getNivelDefensa() + 1);
			setAtacar(false);
		}

		if (defender) {
			setNivelAtaque(getNivelAtaque() + 1);
			setNivelDefensa(getNivelDefensa() - 1);
			setAtacar(false);
		}

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
		if (nivelAtaque > 0) {
			this.nivelAtaque = nivelAtaque;
		}

	}

	public void setNivelDefensa(int nivelDefensa) {
		if (nivelDefensa > 0) {
			this.nivelDefensa = nivelDefensa;
		} else {
			quitarVida(Math.abs(nivelDefensa));
			this.nivelDefensa = 0;
		}

	}

	public void setNivelVida(int nivelVida) {
		this.nivelVida = nivelVida;
	}

	public void setNivelVidaActual(int nivelVidaActual) {
		if (nivelVidaActual <= 0) {
			setVive(false);
			this.nivelVidaActual = 0;

		} else {
			this.nivelVidaActual = nivelVidaActual;
		}
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

	public boolean isAtacar() {
		return atacar;
	}

	public boolean isDefender() {
		return defender;
	}

	public void setAtacar(boolean atacar) {
		this.atacar = atacar;
	}

	public void setDefender(boolean defender) {
		this.defender = defender;
	}

}