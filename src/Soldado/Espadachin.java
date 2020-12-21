package Soldado;

import java.awt.Color;
import java.util.Random;

// POdemos añadir otr INTERFACE

public class Espadachin extends Soldado {
	public static int cantidad = 0;

	private static int ataque = 10;
	private static int defensa = 8;
	private static int vidaMin = 8;
	private static int vidaMax = 10;

	private int longitudEspada;
	private boolean muro = false;

	// Para ramdon
	private Random rd = new Random();

	public Espadachin(String nameReino, String nameEjercito, Color c) {
		super(nameReino, nameEjercito, c, Soldado.NEUTRO, "Espadachin", cantidad);
		setNivelVida(rd.nextInt(vidaMax - vidaMin) + vidaMin);
		setNivelAtaque(ataque);
		setNivelDefensa(defensa);
		setNivelVidaActual(getNivelVida());

		cantidad++;

	}

	// Para superEspadachin
	public Espadachin(String nameReino, String nameEjercito, Color c, String tipo, int cantidad, int vidaMax,
			int vidaMin, int defensa, int ataque) {
		super(nameReino, nameEjercito, c, Soldado.NEUTRO, tipo, cantidad);
		setNivelVida(rd.nextInt(vidaMax - vidaMin) + vidaMin);
		setNivelAtaque(ataque);
		setNivelDefensa(defensa);
		setNivelVidaActual(getNivelVida());

		Espadachin.cantidad++;

	}

	public void crearMuroEscudo() {
		muro = true;
		System.out.println("Muro de escudo creado");

	}

	@Override
	public String toString() {
		return super.toString() + "\nLongitud Espada:\t" + longitudEspada;
	}

	@Override
	public String mostrarDatos() {

		String text = "";
		text += "Nombre: " + getName() + "\n";
		text += super.mostrarDatos();
		return text;
	}

}
