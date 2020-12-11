package Soldado;

import java.util.Random;

public class Lancero extends Soldado {
	public static int cantidad = 0;
	private static int ataque = 5;
	private static int defensa = 10;
	private static int vidaMin = 5;
	private static int vidaMax = 8;

	private int longitudLanza;

	public Lancero(String nameReino) {
		super("Lancero", nameReino, cantidad);
		Random rd = new Random();
		this.longitudLanza = rd.nextInt(5) + 1;
		this.nivelVida = rd.nextInt(vidaMax - vidaMin) + vidaMin;
		nivelAtaque = ataque;
		nivelDefensa = defensa;

		cantidad++;

	}

	public void schiltrom() {
		System.out.println("schiltrom activado");
		setNivelDefensa(getNivelDefensa() + 1);
		setActitud('D');

	}

	public String toString() {
		return super.toString() + "\tLongitud Lanza: " + longitudLanza;
	}

	@Override
	public void atacar() {
		// Aun no esta definido como atacaria lancero
		setActitud('o');
		avanzar(1);
		setAtacar(true);

	}

	@Override
	public void defender() {
		schiltrom();
		setActitud('d');
		setVelocidad(0);
		setAtacar(false);

	}

}