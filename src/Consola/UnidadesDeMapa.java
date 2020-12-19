package Consola;

import java.awt.Color;

public abstract class UnidadesDeMapa {

	private int fila = -1;
	private int columna = -1;
	private boolean vive = true;
	private Color color;

	public final static char ATAQUE = 'a';
	public final static char NEUTRO = 'n';
	public final static char DEFENZA = 'd';

	public abstract int sumaVida();

	public abstract int sumaVidaActual();

	public abstract int sumaAtaque();

	public abstract int sumaDefensa();

	public abstract String getNameReino();

	public abstract String datosPuntuales();

	public abstract String mostrarDatos();

	// Get and Set

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public boolean isVive() {
		return vive;
	}

	public Color getColor() {
		return color;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public void setVive(boolean vive) {
		this.vive = vive;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
