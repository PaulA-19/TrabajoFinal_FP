package Soldado;

import java.awt.Color;

public class SuperEspadachin extends Espadachin implements SuperSoldado {

	private static int cantidad = 0;

	// datos personales
	private static int ataque = 15;
	private static int defensa = 10;
	private static int vidaMax = 15;
	private static int vidaMin = 10;

	public SuperEspadachin(String nameReino, String nameEjercito, Color c) {
		super(nameReino, nameEjercito, c, "SuperEspadachin", cantidad, vidaMax, vidaMin, defensa, ataque);
		cantidad++;
	}

	public SuperEspadachin(String nameReino, String nameEjercito, Color c, String name) {
		super(nameReino, nameEjercito, c, "SuperEspadachin", cantidad, vidaMax, vidaMin, defensa, ataque);
		setName(name);
		cantidad++;
	}

	@Override
	public void evolucionar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void lanzar() {
		// TODO Auto-generated method stub

	}

}
