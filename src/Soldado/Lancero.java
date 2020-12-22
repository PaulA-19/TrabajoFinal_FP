package Soldado;

import java.awt.Color;
import java.util.Random;

import Consola.UnidadesDeMapa;

// falta implementar los ataques, de soldados

public class Lancero extends Soldado {
	// Vida, ataque,defenza
	public static int cantidad = 0;
	private static int aumentarDefensa = 2;

	private static int ataque = 10;
	private static int defensa = 5;
	private static int vidaMin = 5;
	private static int vidaMax = 8;

	// Para ramdon
	private Random rd = new Random();

	private int longitudLanza = 5;

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
		setNivelDefensa(getNivelDefensa() + aumentarDefensa);
		setActitud(Soldado.DEFENZA);

	}

	@Override
	public String mostrarDatos() {

		String text = "";
		text += "Nombre: " + getName() + "\n";
		text += super.mostrarDatos();
		return text;
	}

	@Override
	public void atacarOponente(UnidadesDeMapa oponente) {
		super.atacarOponente(oponente);
	}

	@Override
	public void actitudDefender() {
		schiltrom();
		super.actitudDefender();
	}

	public String toString() {
		return super.toString() + "\nLongitud Lanza:\t" + longitudLanza;
	}

}