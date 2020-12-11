package Soldado;

public class SuperEspadachin extends Espadachin implements SuperSoldado {

	private static int cantidad = 0;

	public SuperEspadachin(String nameReino) {
		super(nameReino, cantidad);
		if (nameReino.equalsIgnoreCase("Inglaterra")) {
			setNivelVida(12);
			setSimbolo("ER");
		} else if (nameReino.equalsIgnoreCase("Sacro Imperio Romano Germanico")) {
			setNivelVida(13);
			setSimbolo("ET");
		} else if (nameReino.equalsIgnoreCase("Aragon-castilla")) { // OJO (aragon-castilla) o (aragon o castilla)
			setNivelVida(14);
			setSimbolo("EC");
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
		// TODO Auto-generated method stub

	}

	@Override
	public void lanzar() {
		// TODO Auto-generated method stub

	}

}
