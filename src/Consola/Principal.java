package Consola;

import java.awt.Color;

import Grafica.Pelea2Soldados;
import Grafica.Pelea2Unidades;
import Grafica.Test;
import Soldado.Soldado;

public class Principal {
	public static void main(String[] args) {

		Ejercito ejer1 = new Ejercito("TEst1", Color.red);
		Ejercito ejer2 = new Ejercito("TEst2", Color.YELLOW);

//		Mapa tabla = new Mapa("Mapa", ejer1, ejer2);
//		System.out.println("dasf");
//
//		Pelea2Soldados pelea = new Pelea2Soldados(ejer1.getUnidades().get(0), ejer2.getUnidades().get(0));
//		pelea.setVisible(true);

		new Test();
	}
}