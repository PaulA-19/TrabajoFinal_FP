package Soldado;

import java.awt.Color;

import javax.swing.JOptionPane;

import Consola.UnidadesDeMapa;

public class SuperEspadachin extends Espadachin implements SuperSoldado {

	private static int cantidad = 0;

	// datos personales
	private static int ataque = 15;
	private static int defensa = 10;
	private static int vidaMax = 15;
	private static int vidaMin = 10;
	private int numObjetosLanzar = OBJETO_LANZAR;

	public SuperEspadachin(String nameReino, String nameEjercito, Color c) {
		super(nameReino, nameEjercito, c, "SuperEspadachin", cantidad, vidaMax, vidaMin, defensa, ataque);
		cantidad++;
	}

	public SuperEspadachin(String nameReino, String nameEjercito, Color c, String name) {
		super(nameReino, nameEjercito, c, "SuperEspadachin", cantidad, vidaMax, vidaMin, defensa, ataque);
		setName(name);
		cantidad++;
	}

	@Override
	public void intentaEvolucionar() {
		if (getBatallaGanada() > 5) {
			setNivelVida(getNivelVida() + AUMENTAR_VIDA_EVOLUCIONADA);
			setNivelVidaActual(getNivelVida());
			setNivelAtaque(getNivelAtaque() + AUMENTAR_ATAQUE_EVOLUCIONADA);
			setNivelDefensa(getNivelDefensa() + AUMENTAR_DEFENSA_EVOLUCIONADA);
			JOptionPane.showMessageDialog(null,
					"El soldado " + getName() + "Supero 5 batallas ganadas\nPor lo tanto evoluciona");
		}
	}

	@Override
	public void lanzar() {
		numObjetosLanzar--;
	}

	@Override
	public String mostrarDatos() {

		String text = "";
		text += super.mostrarDatos();
		text += "Numero de Objeto Lanzar: " + getNumObjetoLanzar() + "\n";

		return text;
	}

	@Override
	public void atacarOponente(UnidadesDeMapa oponente) {
		super.atacarOponente(oponente);
		if (numObjetosLanzar > 0) {
			Soldado oponenteSol = (Soldado) oponente;
			oponenteSol.quitarVidaDefensa(numAleatorio(ataque - FALLO_ATAQUE_SOL, ataque));
			lanzar();
		}
	}

	@Override
	public int getNumObjetoLanzar() {
		// TODO Auto-generated method stub
		return numObjetosLanzar;
	}
}
