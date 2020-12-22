package Soldado;

import java.awt.Color;
import java.util.Random;

import Consola.UnidadesDeMapa;

public class Caballero extends Soldado {
	public static int cantidad = 0;
	private static int ataque = 13;
	private static int defensa = 7;
	private static int vidaMin = 10;
	private static int vidaMax = 12;

	// Para ramdon
	private Random rd = new Random();

	private String[] armas = { "espada", "lanza" };
	private boolean montado = true; // se crea montado de su caballo
	private String armaActual = armas[1]; // como esta montado, tiene lanza

	public Caballero(String nameReino, String nameEjercito, Color c) {
		super(nameReino, nameEjercito, c, Soldado.NEUTRO, "Caballero", cantidad);
		setNivelVida(rd.nextInt(vidaMax - vidaMin) + vidaMin);
		setNivelAtaque(ataque);
		setNivelDefensa(defensa);
		setNivelVidaActual(getNivelVida());

		cantidad++;

	}

	// Para superCaballero
	public Caballero(String nameReino, String nameEjercito, Color c, String tipo, int cantidad, int vidaMax,
			int vidaMin, int defensa, int ataque) {
		super(nameReino, nameEjercito, c, Soldado.NEUTRO, tipo, cantidad);
		setNivelVida(rd.nextInt(vidaMax - vidaMin) + vidaMin);
		setNivelAtaque(ataque);
		setNivelDefensa(defensa);
		setNivelVidaActual(getNivelVida());

		Caballero.cantidad++;

	}

	public void montar() {
		if (!montado) {
			montado = true;
			cambiarArma();
		}
	}

	public void envestir() {

	}

	public void desmontar() {
		if (montado) {
			montado = false;
			cambiarArma();
		}
	}

	public void cambiarArma() {
		if (armaActual == armas[0]) {
			armaActual = armas[1];
		} else {
			armaActual = armas[0];
		}
	}

	@Override
	public String mostrarDatos() {

		String text = "";
		text += "Nombre: " + getName() + "\n";
		text += super.mostrarDatos();
		text += "Arma actual: " + armaActual + "\n";
		return text;
	}

	@Override
	public void atacarOponente(UnidadesDeMapa oponente) {
		if (montado) {
			super.atacarOponente(oponente);
			super.atacarOponente(oponente);

		} else {
			super.atacarOponente(oponente);
		}
	}

	@Override
	public String toString() {
		return super.toString() + "\nArma Actual:\t" + armaActual;
	}

	public boolean isMontado() {
		return montado;
	}

	public void setMontado(boolean montado) {
		this.montado = montado;
	}

}
