package Soldado;

public class SuperCaballero extends Caballero implements SuperSoldado {

	private static int cantidad = 0;

	public SuperCaballero(String nameReino) {
		super(nameReino, cantidad);
		if (nameReino.equalsIgnoreCase("moro")) {
			setNivelVida(13);
			setSimbolo("CM");
		} else if (nameReino.equalsIgnoreCase("francia")) {
			setNivelVida(15);
			setSimbolo("CF");
		}
		cantidad++;

	}

	@Override
	public void atacar() {
		super.atacar();
		lanzar();
	}

	@Override
	public void evolucionar() {
		// Aqui deberia de ir el codigo que ovuluciona

	}

	@Override
	public void lanzar() {
		// TODO Auto-generated method stub

	}

}
