package Soldado;

import java.awt.Color;
import java.util.Random;

public class Arquero extends Soldado {
	private static int ataque = 7;
	private static int defensa = 3;
	private static int vidaMin = 3;
	private static int vidaMax = 5;

	public static int cantidad = 0;

	// Para ramdon
	private Random rd = new Random();

	private int numFlechas = 100;

	public Arquero(String nameReino, String nameEjercito, Color c) {
		super(nameReino, nameEjercito, c, Soldado.NEUTRO, "Arquero", cantidad);
		setNivelVida(rd.nextInt(vidaMax - vidaMin) + vidaMin);
		setNivelAtaque(ataque);
		setNivelDefensa(defensa);
		setNivelVidaActual(getNivelVida());

		cantidad++;

	}

	// Para superArquero
	public Arquero(String nameReino, String nameEjercito, Color c, String tipo, int cantidad, int vidaMax, int vidaMin,
			int defensa, int ataque) {
		super(nameReino, nameEjercito, c, Soldado.NEUTRO, tipo, cantidad);
		setNivelVida(rd.nextInt(vidaMax - vidaMin) + vidaMin);
		setNivelAtaque(ataque);
		setNivelDefensa(defensa);
		setNivelVidaActual(getNivelVida());

		Arquero.cantidad++;

	}

	public void dispararFlecha() {
		if (numFlechas > 0) {
			numFlechas--;
			System.out.println("Flecha disparada");
		} else {
			System.out.println("No tiene mas flechas");
		}

	}

	@Override
	public String toString() {
		return super.toString() + "\nNumero Flechas:\t" + numFlechas;
	}

	@Override
	public String mostrarDatos() {
		String test = getName();
		return test;
	}

}
