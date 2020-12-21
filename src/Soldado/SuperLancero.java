package Soldado;

import java.awt.Color;

public class SuperLancero extends Espadachin implements SuperSoldado {

	private static int cantidad = 0;

	// datos personales
	private static int ataque = 15;
	private static int defensa = 10;
	private static int vidaMax = 10;
	private static int vidaMin = 8;

	public SuperLancero(String nameReino, String nameEjercito, Color c) {
		super(nameReino, nameEjercito, c, "SuperLancero", cantidad, vidaMax, vidaMin, defensa, ataque);
		cantidad++;
	}

	public SuperLancero(String nameReino, String nameEjercito, Color c, String name) {
		super(nameReino, nameEjercito, c, "SuperLancero", cantidad, vidaMax, vidaMin, defensa, ataque);
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

	@Override
	public String mostrarDatos() {

		String text = "";
		text += "Nombre: " + getName() + "\n";
		text += super.mostrarDatos();
		return text;
	}

}
