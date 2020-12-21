package Soldado;

import java.awt.Color;
import java.util.Random;

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
	public void evolucionar() {
		// Aqui deberia de ir el codigo que ovuluciona

	}

	@Override
	public void lanzar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String mostrarDatos() {

		String text = "";
		text += "Nombre: " + getName() + "\n";
		text += super.mostrarDatos();
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

}
