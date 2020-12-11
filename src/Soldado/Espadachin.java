package Soldado;

import java.util.Random;

public class Espadachin extends Soldado {
	public static int cantidad = 0;

	private static int ataque = 10;
	private static int defensa = 8;
	private static int vidaMin = 8;
	private static int vidaMax = 10;

	private int longitudEspada;
	private boolean muro = false;

	public Espadachin(String nameReino) {
		super("Espadachin", nameReino, cantidad);
		Random rd = new Random();
		this.longitudEspada = rd.nextInt(5) + 1;
		this.nivelVida = rd.nextInt(vidaMax - vidaMin) + vidaMin;
		nivelAtaque = ataque;
		nivelDefensa = defensa;
		cantidad++;

	}

	public Espadachin(String nameReino, int cantidad) {
		super("Super Espadachin", nameReino, cantidad);
		Random rd = new Random();
		this.longitudEspada = rd.nextInt(5) + 1;
		this.nivelVida = rd.nextInt(vidaMax - vidaMin) + vidaMin;
		nivelAtaque = ataque;
		nivelDefensa = defensa;
		cantidad++;

	}

	public void crearMuroEscudo() {
		muro = true;
		System.out.println("Muro de escudo creado");
		defender();

	}

	@Override
	public String toString() {
		return super.toString() + "\tLongitud Espada: " + longitudEspada;
	}

	@Override
	public void atacar() {
		// Aun no esta definido como atacar
		setActitud('o');
		avanzar(1);
		setAtacar(true);

	}

	@Override
	public void defender() {
		crearMuroEscudo();

	}

}
