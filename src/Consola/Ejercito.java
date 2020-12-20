package Consola;

import Soldado.*;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Ejercito extends UnidadesDeMapa implements Mapeable, Batalla, Serializable {

	// Datos de clase
	private static int cantidad = 0;

	// Para el random
	private static Random rd = new Random();

	// Datos Personales
	private ArrayList<UnidadesDeMapa> soldados = new ArrayList<UnidadesDeMapa>();
	private String name;
	private int id;
	private String nameReino;
	private char simbolo;
	private char actitud = UnidadesDeMapa.NEUTRO;
	private Color color;

	// Constructores
	public Ejercito(String nameReino, Color c) {
		this(nameReino, c, "Ejercito_" + cantidad);
	}

	public Ejercito(String nameReino, Color c, String name) {
		this.nameReino = nameReino;
		this.name = name;
		color = c;
		simbolo = name.toUpperCase().charAt(0);
		llenarAutomatico();
		cantidad++;
	}

	// Metodos propios de la Clase

	public void actualizar(String nameReino, Color c, char actitud) {
		setNameReino(nameReino);
		setColor(c);
		setActitud(actitud);
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;

			sol.actualizar(nameReino, c, actitud, name);
		}
	}

	public void morir() {
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;
			sol.morir();
		}
		setVive(false);
	}

	public void llenarAutomatico() {
		int cantidad = rd.nextInt(11); // [0,10]
		for (int i = 0; i < cantidad; i++) {
			int tipo = rd.nextInt(8); // [0-7]

			// aqui con un Switch case determianos entre que soldados elegir

			switch (tipo) {
			case 0: { // Arquero
				soldados.add(new Arquero(nameReino, name, getColor()));
				break;
			}
			case 1: {// Lancero
				soldados.add(new Lancero(nameReino, name, getColor()));
				break;
			}
			case 2: { // Caballero
				soldados.add(new Caballero(nameReino, name, getColor()));
				break;
			}
			case 3: {// Espadachin
				soldados.add(new Espadachin(nameReino, name, getColor()));
				break;
			}
			case 4: { // SuperArquero
				soldados.add(new SuperArquero(nameReino, name, getColor()));
				break;
			}
			case 5: { // SuperLancero
				soldados.add(new SuperLancero(nameReino, name, getColor()));
				break;
			}
			case 6: { // SuperCaballero
				soldados.add(new SuperCaballero(nameReino, name, getColor()));
				break;
			}
			case 7: { // SuperEspadachin
				soldados.add(new SuperEspadachin(nameReino, name, getColor()));
				break;
			}
			}
		}
	}

	public int nivelVida() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;
			total += sol.getNivelVida();
		}
		return total;
	}

	public int nivelVidaActual() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;
			total += sol.getNivelVidaActual();
		}
		return total;
	}

	public int nivelAtaque() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;
			total += sol.getNivelAtaque();
		}
		return total;
	}

	public int nivelDefensa() {
		int total = 0;
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;
			total += sol.getNivelDefensa();
		}
		return total;
	}

	public double promedioAtaque() {
		int total = nivelAtaque();
		return ((double) total / getUnidades().size());
	}

	public double promedioDefensa() {
		int total = nivelDefensa();
		return ((double) total / getUnidades().size());
	}

	public double promedioNivelVida() {
		int total = nivelVida();
		return ((double) total / getUnidades().size());
	}

	public double promedioNivelVidaActual() {
		int total = nivelVidaActual();
		return ((double) total / getUnidades().size());
	}

	@Override
	public String toString() {
		String text = "nombre Ejercito: " + name + "\n";
		text += "Reino: " + getNameReino();
		// for (UnidadesDeMapa object : soldados) {
//			Soldado sol = (Soldado) object; // casting a soldado
//			text += sol.toString();
//		}
		return text;
	}

	public void beneficiado() {
		for (UnidadesDeMapa soldado : soldados) {
			Soldado sol = (Soldado) soldado;
			sol.beneficiado();
		}
	}

	public int numSoldados() {
		return soldados.size();
	}

	public void limpiarEjercito() {
		soldados.clear();
	}

	public void derrrotado() {
		setVive(false);
	}

	public Color getColor() {
		return color;

	}

	@Override
	public String datosPuntuales() {
//		String text = Integer.toString(getUnidades().size()) + "-" + promedioNivelVida() + "-" + promedioAtaque();
		String text = getNameReino();
		System.out.println(text);
		return text;
	}

	// Mapeable
	@Override
	public ArrayList<UnidadesDeMapa> getUnidades() {

		return soldados;
	}

	@Override
	public void addUnidad(UnidadesDeMapa unidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUnidad(UnidadesDeMapa unidad) {
		// TODO Auto-generated method stub

	}

	// UnidadesDeMapa
	@Override
	public int sumaVida() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumaVidaActual() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumaAtaque() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumaDefensa() {
		// TODO Auto-generated method stub
		return 0;
	}

	// Get and Set
	public String getName() {
		return name;
	}

	public String getNameReino() {
		return nameReino;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSoldados(ArrayList<UnidadesDeMapa> soldados) {
		this.soldados = soldados;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNameReino(String nameReino) {
		this.nameReino = nameReino;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	// Batalla

	@Override
	public void actitudAtacar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actitudDefender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actitudNormal() {
		// TODO Auto-generated method stub

	}

	public static UnidadesDeMapa[] batalla(UnidadesDeMapa unidad1, UnidadesDeMapa unidad2) {
		Ejercito ganador, perdedor;
		Ejercito e1 = (Ejercito) unidad1;
		Ejercito e2 = (Ejercito) unidad1;

		int vida1 = e1.sumaVidaActual();
		int vida2 = e2.sumaVidaActual();

		double total = vida1 + vida2;
		double vida1Decimal = vida1 / total;
		double vida2Decimal = vida2 / total;

		double elegido = Math.random();

		if (elegido < Math.min(vida1Decimal, vida2Decimal)) {
			if (Math.min(e1.sumaVidaActual(), e2.sumaVidaActual()) == e1.sumaVidaActual()) {
				mostrarGanador(e1);
				ganador = e1;
				perdedor = e2;
			} else {
				mostrarGanador(e2);
				ganador = e2;
				perdedor = e1;
			}

		} else {
			if (Math.max(e1.sumaVidaActual(), e2.sumaVidaActual()) == e1.sumaVidaActual()) {
				mostrarGanador(e1);
				ganador = e1;
				perdedor = e2;
			} else {
				mostrarGanador(e2);
				ganador = e2;
				perdedor = e1;
			}
		}

		ganador.beneficiado();
		Ejercito[] resultado = { ganador, perdedor };
		return resultado;
	}

	private static void mostrarGanador(Ejercito ejer) {
		System.out.println("Ganador ejercito del reino: " + ejer.getNameReino());

	}

	@Override
	public String mostrarDatos() {
		String text = "";
		text += "Ejercito: " + this.getNameReino() + "\n";
		text += "Cantidad total de soldados: " + numSoldados() + "\n";
		int[] numTipos = this.cantidadTiposSoldados();
		text += "Caballero: " + numTipos[1] + "\n";
		text += "Arquero: " + numTipos[2] + "\n";
		text += "Lancero: " + numTipos[3] + "\n";
		text += "Espadachin: " + numTipos[4] + "\n";
		text += this.imprimirSoldadoEspecial(numTipos);
		return text;
	}

	private int[] cantidadTiposSoldados() {
		int[] tipos = new int[6];
		for (UnidadesDeMapa soldado : getUnidades()) {
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

	private String imprimirSoldadoEspecial(int[] numTipos) {
		String text = "";
		if (getNameReino().equalsIgnoreCase("Inglaterra")) {
			text += "Espadachin Real: " + numTipos[5] + "\n";
		} else if (getNameReino().equalsIgnoreCase("Sacro Imperio Romano Germanico")) {
			text += "Espadachin Teutonico: " + numTipos[5] + "\n";
		} else if (getNameReino().equalsIgnoreCase("Aragon-Castilla")) {
			text += "Espadachin Conquistador: " + numTipos[5] + "\n";
		} else if (getNameReino().equalsIgnoreCase("Francia")) {
			text += "Caballero Franco: " + numTipos[0] + "\n";
		} else if (getNameReino().equalsIgnoreCase("Moros")) {
			text += "Caballero Moro: " + numTipos[0] + "\n";
		}
		return text;

	}

	public char getActitud() {
		return actitud;
	}

	public void setActitud(char actitud) {
		for (UnidadesDeMapa unidadesDeMapa : soldados) {
			Soldado sol = (Soldado) unidadesDeMapa;
			sol.setActitud(actitud);
		}
		this.actitud = actitud;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
