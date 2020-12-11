package test;

import java.util.ArrayList;
import java.util.Random;

import Consola.Batallar;

public class Ejercito implements Batallar{
	private ArrayList<Soldado> misSoldados = new ArrayList<Soldado>();
	private String reino;
	private int cantidadSoldados = 0;
//	private char simbolo;

	public Ejercito() {

	}

	public Ejercito(String nameReino) {
		this.reino = nameReino;
		llenarAutomatico();

	}

	public void llenarAutomatico() {
		Random rd = new Random();
		int cantidad = rd.nextInt(11); // [0,10]
		for (int i = 0; i < cantidad; i++) {
			cantidadSoldados = cantidad;
			int[] valores = rangoSegunReino(); // devuelve entre que soldados se puede crear
			int min = valores[0]; // minimo
			int max = valores[1]; // maximo

			int tipo = rd.nextInt(max - min + 1) + min;

			// aqui con un Switch case determianos entre que soldados elegir

			switch (tipo) {
			case 0: { // Super caballero
				misSoldados.add(new SuperCaballero(reino));
				break;
			}
			case 1: {// caballero
				misSoldados.add(new Caballero(reino));
				break;
			}
			case 2: { // arquero
				misSoldados.add(new Arquero(reino));
				break;
			}
			case 3: {// lancero
				misSoldados.add(new Lancero(reino));
				break;
			}
			case 4: { // espadachin
				misSoldados.add(new Espadachin(reino));
				break;
			}
			case 5: { // Super espadachin
				misSoldados.add(new SuperEspadachin(reino));
				break;
			}
			}
		}
	}

	private int[] rangoSegunReino() {
		int[] valores = new int[2];
		// OJO (aragon-castilla) o (aragon o castilla)
		// Super Espadachines
		if (reino.equalsIgnoreCase("Inglaterra") || reino.equalsIgnoreCase("Sacro Imperio Romano Germanico")
				|| reino.equalsIgnoreCase("Aragon-Castilla")) {
			valores[0] = 1;
			valores[1] = 5;

			// Super caballero
		} else if (reino.equalsIgnoreCase("Francia") || reino.equalsIgnoreCase("Moros")) {
			valores[0] = 0;
			valores[1] = 4;

			// Normal
		} else {
			valores[0] = 1;
			valores[1] = 4;

		}
		return valores;
	}

	public void mostrarDatos() {
		System.out.println("\nEjercito: " + this.getReino());
		System.out.println("Cantidad total de soldados: " + this.numSoldadosActual());
		int[] numTipos = this.cantidadTiposSoldados();
		System.out.println("Caballero: " + numTipos[1]);
		System.out.println("Arquero: " + numTipos[2]);
		System.out.println("Lancero: " + numTipos[3]);
		System.out.println("Espadachin: " + numTipos[4]);
		this.imprimirSoldadoEspecial(numTipos);

	}

	private void imprimirSoldadoEspecial(int[] numTipos) {

		if (reino.equalsIgnoreCase("Inglaterra")) {
			System.out.println("Espadachin Real: " + numTipos[5]);
		} else if (reino.equalsIgnoreCase("Sacro Imperio Romano Germanico")) {
			System.out.println("Espadachin Teutonico: " + numTipos[5]);
		} else if (reino.equalsIgnoreCase("Aragon-Castilla")) {
			System.out.println("Espadachin Conquistador: " + numTipos[5]);
		} else if (reino.equalsIgnoreCase("Francia")) {
			System.out.println("Caballero Franco: " + numTipos[0]);
		} else if (reino.equalsIgnoreCase("Moros")) {
			System.out.println("Caballero Moro: " + numTipos[0]);
		}

	}

	private int[] cantidadTiposSoldados() {
		int[] tipos = new int[6];
		for (Soldado soldado : this.getMisSoldados()) {
			if (soldado instanceof SuperCaballero) {
				tipos[0]++;
			} else if (soldado instanceof Caballero && !(soldado instanceof SuperCaballero)) {
				tipos[1]++;
			} else if (soldado instanceof Arquero) {
				tipos[2]++;
			} else if (soldado instanceof Lancero) {
				tipos[3]++;
			} else if (soldado instanceof Espadachin && !(soldado instanceof SuperEspadachin)) {
				tipos[4]++;
			} else if (soldado instanceof SuperEspadachin) {
				tipos[5]++;
			}
		}
		return tipos;
	}

	public int sumaVida() {
		int suma = 0;
		for (Soldado soldado : misSoldados) {
			suma += soldado.getNivelVida();
		}
		return suma;
	}

	public void beneficiado() {
		for (Soldado sol : misSoldados) {
			sol.aumentarVida1();
		}
	}

	public int numSoldadosActual() {

		return this.misSoldados.size();
	}

	public void limipar() {
		misSoldados.clear();
	}

	public ArrayList<Soldado> getMisSoldados() {
		return misSoldados;
	}

	public void setMisSoldados(ArrayList<Soldado> misSoldados) {
		this.misSoldados = misSoldados;
	}

	public String getReino() {
		return reino;
	}

	public void setReino(String reino) {
		this.reino = reino;
	}

}
