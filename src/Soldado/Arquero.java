package Soldado;

import java.util.Random;

public class Arquero extends Soldado {
	private static int ataque = 7;
	private static int defensa = 3;
	private static int vidaMin = 3;
	private static int vidaMax = 5;

	public static int cantidad = 0;

	private int numFlechas = 100;

	public Arquero(String nameReino) {
		super("Arquero", nameReino, cantidad);
		Random rd = new Random();
		this.nivelVida = rd.nextInt(vidaMax - vidaMin) + vidaMin;
		nivelAtaque = ataque;
		nivelDefensa = defensa;

		cantidad++;
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
		return super.toString() + "\tNum. Flechas: " + numFlechas;
	}

	@Override
	public void atacar() {
		dispararFlecha();
		setActitud('o');
		avanzar(1);
		setAtacar(true);

	}

	@Override
	public void defender() {
		setActitud('d');
		setVelocidad(0);

		setAtacar(false);

	}

}
