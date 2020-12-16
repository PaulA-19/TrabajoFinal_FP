package Consola;

import Soldado.Soldado;

public class Principal {
	public static void main(String[] args) {

		Game game1 = new Game();
		Ejercito ejer = (Ejercito) game1.getReinos().get(0).getUnidades().get(0);
		System.out.println(ejer.numSoldados());
	}
}