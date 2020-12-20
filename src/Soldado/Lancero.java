package Soldado;

import java.awt.Color;
import java.util.Random;

// falta implementar los ataques, de soldados

public class Lancero extends Soldado {
	// Vida, ataque,defenza
	public static int cantidad = 0;

	private static int ataque = 10;
	private static int defensa = 5;
	private static int vidaMin = 5;
	private static int vidaMax = 8;

	// Para ramdon
	private Random rd = new Random();

	private int longitudLanza;

	public Lancero(String nameReino, String nameEjercito, Color c) {
		super(nameReino, nameEjercito, c, Soldado.NEUTRO, "Lancero", cantidad);
		setNivelVida(rd.nextInt(vidaMax - vidaMin) + vidaMin);
		setNivelAtaque(ataque);
		setNivelDefensa(defensa);
		setNivelVidaActual(getNivelVida());

		cantidad++;

	}

	// Para superLancero
	public Lancero(String nameReino, String nameEjercito, Color c, String tipo, int cantidad, int vidaMax, int vidaMin,
			int defensa, int ataque) {
		super(nameReino, nameEjercito, c, Soldado.NEUTRO, tipo, cantidad);
		setNivelVida(rd.nextInt(vidaMax - vidaMin) + vidaMin);
		setNivelAtaque(ataque);
		setNivelDefensa(defensa);
		setNivelVidaActual(getNivelVida());

		Lancero.cantidad++;

	}

	public void schiltrom() {
		System.out.println("schiltrom activado");
		setNivelDefensa(getNivelDefensa() + 1);
		setActitud(Soldado.DEFENZA);

	}

	public String toString() {
		return super.toString() + "\nLongitud Lanza:\t" + longitudLanza;
	}

	@Override
	public String mostrarDatos() {
		// TODO Auto-generated method stub
		return null;
	}

}