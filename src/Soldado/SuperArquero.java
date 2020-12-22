package Soldado;

import java.awt.Color;

import javax.swing.JOptionPane;

import Consola.UnidadesDeMapa;

public class SuperArquero extends Arquero implements SuperSoldado {

	private static int cantidad = 0;

	// datos personales
	private static int ataque = 13;
	private static int defensa = 5;
	private static int vidaMax = 10;
	private static int vidaMin = 5;
	private int numObjetosLanzar = OBJETO_LANZAR;

	public SuperArquero(String nameReino, String nameEjercito, Color c) {
		super(nameReino, nameEjercito, c, "SuperArquero", cantidad, vidaMax, vidaMin, defensa, ataque);
		cantidad++;
	}

	public SuperArquero(String nameReino, String nameEjercito, Color c, String name) {
		super(nameReino, nameEjercito, c, "SuperArquero", cantidad, vidaMax, vidaMin, defensa, ataque);
		setName(name);
		cantidad++;
	}

	@Override
	public String mostrarDatos() {

		String text = "";
		text += super.mostrarDatos();
		text += "Numero de Objeto Lanzar: " + getNumObjetoLanzar() + "\n";

		return text;
	}

	@Override
	public void intentaEvolucionar() {
		if (getBatallaGanada() > 5) {
			setNivelVida(getNivelVida() + AUMENTAR_VIDA_EVOLUCIONADA);
			setNivelVidaActual(getNivelVida());
			setNivelAtaque(getNivelAtaque() + AUMENTAR_ATAQUE_EVOLUCIONADA);
			setNivelDefensa(getNivelDefensa() + AUMENTAR_DEFENSA_EVOLUCIONADA);
			setNumFlechas(getNumFlechas() + (getNumFlechas() / 2));
			JOptionPane.showMessageDialog(null,
					"El soldado " + getName() + "Supero 5 batallas ganadas\nPor lo tanto evoluciona");
		}
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
	public void lanzar() {
		numObjetosLanzar--;
	}

	@Override
	public void actitudAtacar() {
		super.actitudAtacar();

	}

	@Override
	public int getNumObjetoLanzar() {
		return numObjetosLanzar;

	}

}
