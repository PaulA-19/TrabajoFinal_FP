package Soldado;

import java.awt.Color;
import java.util.Random;

import Consola.UnidadesDeMapa;

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
		if (numFlechas >= 0) {
			numFlechas--;
		}

	}

	@Override
	public String toString() {
		return super.toString() + "\nNumero Flechas:\t" + numFlechas;
	}

	@Override
	public String mostrarDatos() {

		String text = "";
		text += "Nombre: " + getName() + "\n";
		text += super.mostrarDatos();
		text += "Numero de Flechas: " + numFlechas + "\n";
		return text;
	}

	@Override
	public void atacarOponente(UnidadesDeMapa oponente) {
		Soldado oponeSol = (Soldado) oponente;
		int ataque = this.getNivelAtaque();

		if (numFlechas > 0) {
			Soldado oponenteSol = (Soldado) oponente;
			oponenteSol.quitarVidaDefensa(numAleatorio(ataque - FALLO_ATAQUE_SOL, ataque + FALLO_ATAQUE_SOL));
			dispararFlecha();
		}

	}

	public int getNumFlechas() {
		return numFlechas;
	}

	public void setNumFlechas(int numFlechas) {
		this.numFlechas = numFlechas;
	}

}
