package Soldado;

import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

import Consola.UnidadesDeMapa;

public class SuperCaballero extends Caballero implements SuperSoldado {

	private static int cantidad = 0;

	// random
	private Random rd = new Random();

	// datos personales
	private static int ataque = 15;
	private static int defensa = 10;
	private static int vidaMax = 15;
	private static int vidaMin = 12;

	public SuperCaballero(String nameReino, String nameEjercito, Color c) {
		super(nameReino, nameEjercito, c, "SuperCaballero", cantidad, vidaMax, vidaMin, defensa, ataque);
		cantidad++;
	}

	public SuperCaballero(String nameReino, String nameEjercito, Color c, String name) {
		super(nameReino, nameEjercito, c, "SuperCaballero", cantidad, vidaMax, vidaMin, defensa, ataque);
		setName(name);
		cantidad++;
	}

	// Super Soldado
	@Override
	public void intentaEvolucionar() {
		if (getBatallaGanada() > 5) {
			setNivelVida(getNivelVida() + AUMENTAR_VIDA_EVOLUCIONADA);
			setNivelVidaActual(getNivelVida());
			setNivelAtaque(getNivelAtaque() + AUMENTAR_ATAQUE_EVOLUCIONADA);
			setNivelDefensa(getNivelDefensa() + AUMENTAR_DEFENSA_EVOLUCIONADA);
			JOptionPane.showMessageDialog(null,
					"El soldado " + getName() + "Supero 5 batallas ganadas\nPor lo tanto evoluciona");
		}
	}

	@Override
	public void atacarOponente(UnidadesDeMapa oponente) {
		if (isMontado()) {
			super.atacarOponente(oponente);
			super.atacarOponente(oponente);
			super.atacarOponente(oponente);

		} else {
			super.atacarOponente(oponente);
			super.atacarOponente(oponente);

		}
	}

	@Override
	public void lanzar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String mostrarDatos() {

		String text = "";
		text += super.mostrarDatos();
		text += "Numero de Objeto Lanzar: " + getNumObjetoLanzar() + "\n";

		return text;
	}

	// get and Set
	public static int getCantidad() {
		return cantidad;
	}

	public static int getAtaque() {
		return ataque;
	}

	public static int getDefensa() {
		return defensa;
	}

	public static int getVidaMax() {
		return vidaMax;
	}

	public static int getVidaMin() {
		return vidaMin;
	}

	public static void setCantidad(int cantidad) {
		SuperCaballero.cantidad = cantidad;
	}

	public static void setAtaque(int ataque) {
		SuperCaballero.ataque = ataque;
	}

	public static void setDefensa(int defensa) {
		SuperCaballero.defensa = defensa;
	}

	public static void setVidaMax(int vidaMax) {
		SuperCaballero.vidaMax = vidaMax;
	}

	public static void setVidaMin(int vidaMin) {
		SuperCaballero.vidaMin = vidaMin;
	}

	@Override
	public int getNumObjetoLanzar() {
		// TODO Auto-generated method stub
		return 0;
	}

}
