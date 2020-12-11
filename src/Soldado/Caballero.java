package Soldado;

import java.util.Random;

public class Caballero extends Soldado {
	public static int cantidad = 0;
	private static int ataque = 13;
	private static int defensa = 7;
	private static int vidaMin = 10;
	private static int vidaMax = 12;

	private String[] armas = { "espada", "lanza" };
	private boolean montado = true; // se crea montado de su caballo
	private String armaActual = armas[1]; // como esta montado, tiene lanza

	public Caballero(String nameReino) {
		super("caballero", nameReino, cantidad);
		Random rd = new Random();
		this.nivelVida = rd.nextInt(vidaMax - vidaMin) + vidaMin;
		nivelAtaque = ataque;
		nivelDefensa = defensa;
		cantidad++;

	}

	public Caballero(String nameReino, int cantidad) {
		super("Super Caballero", nameReino, cantidad);
		Random rd = new Random();
		this.nivelVida = rd.nextInt(vidaMax - vidaMin) + vidaMin;
		nivelAtaque = ataque;
		nivelDefensa = defensa;
		cantidad++;

	}

	public void montar() {
		if (!montado) {
			montado = true;
			cambiarArma();
			envestir();
			System.out.println("Caballero montado");
		} else {
			System.out.println("No puede montar, ya esta montado");
		}
	}

	public void envestir() {
		System.out.println("Envistiendo");
		atacar();

		if (montado) {
			atacar();
			atacar();
			atacar();
		} else {
			atacar();
			atacar();
		}

	}

	public void atacar() {
		System.out.println("Atacando");
		setAtacar(true);
		envestir();
	}

	public void defender() {
		setActitud('d');
		setVelocidad(0);
		desmontar();
		setAtacar(false);

	}

	public void desmontar() {
		if (montado) {
			montado = false;
			System.out.println("Defendiendo");
			cambiarArma();
			System.out.println("Caballero desmontado");
		} else {
			System.out.println("No puede desmontar, ya esta desmontado");
		}
	}

	public void cambiarArma() {
		if (armaActual == armas[0]) {
			armaActual = armas[1];
		} else {
			armaActual = armas[0];
		}
	}

	@Override
	public String toString() {
		return super.toString() + "\tArma Actual: " + armaActual;
	}

}
