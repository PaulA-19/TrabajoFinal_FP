package Soldado;

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JOptionPane;

import Consola.Batalla;
import Consola.Ejercito;
import Consola.UnidadesDeMapa;
//falta implementar los ataques, de soldados

public abstract class Soldado extends UnidadesDeMapa implements Batalla, Serializable {

	// Actitudes
	public final static char ATAQUE = 'a';
	public final static char NEUTRO = 'n';
	public final static char DEFENZA = 'd';

	// Datos de clase
	private static int cantidad = 0;

	// Para el random
	private static Random rd = new Random();

	// Datos Personales
	private String name;
	private int nivelAtaque;
	private int nivelDefensa;
	private int nivelVida;
	private int nivelVidaActual;
	private char actitud = Soldado.NEUTRO;
	private int batallaGanada = 0;
	private Color color;

	// Datos generales
	private int id;
	private String nameReino;
	private String nameEjercito;
	private String tipo; // tipo de soldado
	private char simbolo; // del reino y ejercito

	private boolean atacar = false;
	private boolean defender = false;

	// Constructores---------------------
	public Soldado(String nameReino, String nameEjercito, Color c) {
		this(nameReino, nameEjercito, c, UnidadesDeMapa.NEUTRO);
	}

	public Soldado(String nameReino, String nameEjercito, Color c, char actitud) {
		this(nameReino, nameEjercito, c, actitud, "", 0);
	}

	public Soldado(String nameReino, String nameEjercito, Color c, char actitud, String tipo, int cantidadTipo) {
		this.name = tipo + "_" + Integer.toString(cantidadTipo);
		this.tipo = tipo;
		this.nameReino = nameReino;
		this.nameEjercito = nameEjercito;
		this.actitud = actitud;
		this.simbolo = nameEjercito.toUpperCase().charAt(0);
		setColor(c);
		cantidad++;
	}

	// Batalla----------------

	@Override
	public void atacarOponente(UnidadesDeMapa oponente) {
		int ataque = this.getNivelAtaque();

		Soldado oponenteSol = (Soldado) oponente;
		oponenteSol.quitarVidaDefensa(numAleatorio(ataque - FALLO_ATAQUE_SOL, ataque + FALLO_ATAQUE_SOL));

	}

	@Override
	public void quitarVida(UnidadesDeMapa oponenteAtaca) {
		Soldado solOpo = (Soldado) oponenteAtaca;

		this.quitarVidaDefensa(solOpo.getNivelAtaque() / PORCION_ATAQUE);

	}

	public static UnidadesDeMapa[] batalla(UnidadesDeMapa unidad1, UnidadesDeMapa unidad2) {
		Soldado ganador, perdedor;
		Soldado e1 = (Soldado) unidad1;
		Soldado e2 = (Soldado) unidad2;

		while (e2.isVive() && e1.isVive()) {
			e1.atacarOponente(e2);
			if (e2.isVive()) {
				e2.atacarOponente(e1);
			} else {
				continue;
			}

		}

		if (e1.isVive()) {
			ganador = e1;
			perdedor = e2;
		} else {
			ganador = e2;
			perdedor = e1;

		}
		ganador.setBatallaGanada(ganador.getBatallaGanada() + 1);
		Soldado[] resultado = { ganador, perdedor };
		ganador.intentaEvolucionar();
		return resultado;

	}

//	private static void pelear(Soldado s1, Soldado s2) {
//
//		int ataque1 = s1.getNivelAtaque();
//		int ataque2 = s1.getNivelAtaque();
//
//		// ataca s2
//		s1.quitarVidaDefensa(numAleatorio(ataque2 - 2, ataque2 + 2));
//		if (s1.isVive()) {
//			// ataca s1
//			s2.quitarVidaDefensa(numAleatorio(ataque1 - 2, ataque1 + 2));
//		} else {
//			return;
//		}
//
//	}

	public static int numAleatorio(int m, int n) {
		int num = (rd.nextInt(n - m) + m);
		return num;
	}

//	private static double elegirNumeroSegunPorcentaje(double num1, double num2) {
//		double ale = rd.nextDouble();
//
//		double minimo = Math.min(num1, num2);
//
//		if (ale < minimo) {
//			return minimo;
//		} else {
//			return Math.max(num2, num1);
//		}
//
//	}

	// Completar
	private static int[] beneficiadoBatalla(Soldado s1, Soldado s2) {
		int[] vidas = { s1.getNivelVidaActual(), s2.getNivelVidaActual() };

		if ((s1 instanceof Caballero && s2 instanceof Arquero) || (s1 instanceof Arquero && s2 instanceof Caballero)) {
			if (s1 instanceof Caballero) {
				vidas[0]++;
				if (s1 instanceof SuperCaballero) {
					vidas[0]++;
				}
			} else {
				vidas[1]++;
				if (s2 instanceof SuperCaballero) {
					vidas[1]++;
				}
			}

		} else if ((s1 instanceof Lancero && s2 instanceof Caballero)
				|| (s2 instanceof Lancero && s1 instanceof Caballero)) {
			if ((s1 instanceof Lancero) && !(s2 instanceof SuperCaballero)) {
				vidas[0]++;

			} else if (!(s1 instanceof SuperCaballero)) {
				vidas[1]++;
			}
		} else if ((s1 instanceof Arquero && s2 instanceof Lancero)
				|| (s2 instanceof Arquero && s1 instanceof Lancero)) {
			if (s1 instanceof Arquero) {
				vidas[0]++;

			} else {
				vidas[1]++;
			}

		} else if ((s1 instanceof Caballero && s2 instanceof Espadachin)
				|| (s2 instanceof Caballero && s1 instanceof Espadachin)) {
			// no se da restricciones para el caballero especial
			if (s1 instanceof Caballero) {
				vidas[0]++;

			} else {
				vidas[1]++;
			}

		} else if ((s1 instanceof Espadachin && s2 instanceof Lancero)
				|| (s1 instanceof Espadachin && s2 instanceof Lancero)) {
			if (s1 instanceof Espadachin) {
				vidas[0]++;
				if (s1 instanceof SuperEspadachin) {
					vidas[0]++;
				}
			} else {
				vidas[1]++;
				if (s2 instanceof SuperEspadachin) {
					vidas[1]++;
				}
			}

		} else if ((s1 instanceof SuperEspadachin && s2 instanceof Espadachin && !(s2 instanceof SuperEspadachin))
				|| (s2 instanceof SuperEspadachin && s1 instanceof Espadachin && !(s1 instanceof SuperEspadachin))) {
			if (s1 instanceof SuperEspadachin) {
				vidas[0]++;

			} else {
				vidas[1]++;
			}

		}

		else if ((s1 instanceof SuperCaballero && s2 instanceof Caballero && !(s2 instanceof SuperCaballero))
				|| (s2 instanceof SuperCaballero && s1 instanceof Caballero && !(s1 instanceof SuperCaballero))) {
			if (s1 instanceof SuperCaballero) {
				vidas[0]++;

			} else {
				vidas[1]++;
			}
		}

		return vidas;

	}

	// Metodos propios de Clase-----------------------

	public void actualizar(String nameReino, Color c, char actitud, String nameEjercito) {
		setNameReino(nameReino);
		setNameEjercito(nameEjercito);
		setActitud(actitud);
		setColor(c);

	}

	public void beneficiado() {
		setNivelVidaActual(getNivelVidaActual() + SUMA_VIDA_BENEFICIO);
	}

	public void quitarVidaDefensa(int cant) {
		int nuevo = getNivelDefensa() - cant;
		setNivelDefensa(nuevo);

	}

	public void quitarVida(int cant) {
		int nuevo = getNivelVidaActual() - cant;
		setNivelVidaActual(nuevo);
	}

	public void morir() {
		setVive(false);
		setNivelVidaActual(0);
		setNivelDefensa(0);
	}

	@Override
	public String toString() {
		return "Nombre: " + name + "\nReino: " + getNameReino() + "\nNivel de vida actual:\t" + getNivelVidaActual()
				+ "\nNive de ataque:\t" + getNivelAtaque() + "\nNive de defensa:\t" + getNivelDefensa();
	}

	// Batalla

	@Override
	public void actitudAtacar() {
		setNivelAtaque(getNivelAtaque() + INTERCAMBIO_VIDA_EN_ATAQUE);
		setNivelDefensa(getNivelDefensa() - INTERCAMBIO_VIDA_EN_ATAQUE);
		setAtacar(true);
	}

	@Override
	public void actitudDefender() {
		setNivelAtaque(getNivelAtaque() - INTERCAMBIO_ATAQUE_EN_VIDA);
		setNivelDefensa(getNivelDefensa() + INTERCAMBIO_ATAQUE_EN_VIDA);
		setDefender(true);
	}

	@Override
	public void actitudNormal() {

		if (atacar) {
			setNivelAtaque(getNivelAtaque() - INTERCAMBIO_VIDA_EN_ATAQUE);
			setNivelDefensa(getNivelDefensa() + INTERCAMBIO_VIDA_EN_ATAQUE);
			setAtacar(false);
		}

		if (defender) {
			setNivelAtaque(getNivelAtaque() + INTERCAMBIO_ATAQUE_EN_VIDA);
			setNivelDefensa(getNivelDefensa() - INTERCAMBIO_ATAQUE_EN_VIDA);
			setAtacar(false);
		}

	}

	// UnidadesDeMapa --------------------

	@Override
	public int sumaVida() {
		return getNivelVida();
	}

	@Override
	public int sumaAtaque() {
		return getNivelAtaque();
	}

	@Override
	public int sumaDefensa() {
		return getNivelDefensa();
	}

	@Override
	public int sumaVidaActual() {
		return getNivelVidaActual();
	}

	@Override
	public int sumaVidaVivos() {
		if (isVive()) {
			return getNivelVida();
		}
		return 0;
	}

	@Override
	public int sumaVidaActualVivos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumaAtaqueVivos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumaDefensaVivos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String datosPuntuales() {
		String text = "";

		text += "Vida; " + getNivelVidaActual();

		return text;
	}

	@Override
	public String mostrarDatos() {

		String text = "";

		text += "Reino: " + getNameReino() + "\n";
		text += "Ejercito: " + getNameEjercito() + "\n";
		text += "Tipo: " + getTipo() + "\n";
		text += "Nivel Ataque: " + getNivelAtaque() + "\n";
		text += "Nivel Defensa: " + getNivelDefensa() + "\n";
		text += "Nivel Vida: " + getNivelVida() + "\n";
		text += "Nivel Vida Actual: " + getNivelVidaActual() + "\n";

		return text;
	}

	public void intentaEvolucionar() {
	}

	// ---------------- get and set ---------------------------------------
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public int getNivelAtaque() {
		return nivelAtaque;
	}

	public int getNivelDefensa() {
		return nivelDefensa;
	}

	public int getNivelVida() {
		return nivelVida;
	}

	public int getNivelVidaActual() {
		return nivelVidaActual;
	}

	public char getActitud() {
		return actitud;
	}

	public int getId() {
		return id;
	}

	public String getNameReino() {
		return nameReino;
	}

	public String getNameEjercito() {
		return nameEjercito;
	}

	public String getTipo() {
		return tipo;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNivelAtaque(int nivelAtaque) {
		if (nivelAtaque > 0) {
			this.nivelAtaque = nivelAtaque;
		}

	}

	public void setNivelDefensa(int nivelDefensa) {
		if (nivelDefensa > 0) {
			this.nivelDefensa = nivelDefensa;
		} else {
			quitarVida(Math.abs(nivelDefensa));
			this.nivelDefensa = 0;
		}

	}

	public void setNivelVida(int nivelVida) {
		this.nivelVida = nivelVida;
	}

	public void setNivelVidaActual(int nivelVidaActual) {
		if (nivelVidaActual <= 0) {
			setVive(false);
			this.nivelVidaActual = 0;

		} else {
			this.nivelVidaActual = nivelVidaActual;
		}
	}

	public void setActitud(char actitud) {
		this.actitud = actitud;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNameReino(String nameReino) {
		this.nameReino = nameReino;
	}

	public void setNameEjercito(String nameEjercito) {
		this.nameEjercito = nameEjercito;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	public boolean isAtacar() {
		return atacar;
	}

	public boolean isDefender() {
		return defender;
	}

	public void setAtacar(boolean atacar) {
		this.atacar = atacar;
	}

	public void setDefender(boolean defender) {
		this.defender = defender;
	}

	public int getBatallaGanada() {
		return batallaGanada;
	}

	public void setBatallaGanada(int batallaGanada) {
		this.batallaGanada = batallaGanada;
	}

}