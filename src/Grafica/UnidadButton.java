package Grafica;

import javax.swing.JButton;

import Consola.UnidadesDeMapa;

public class UnidadButton extends JButton {

	private UnidadesDeMapa unidad;
	private int fila, columna;

	public UnidadButton(UnidadesDeMapa unidad, int fila, int columna) {
		super();
		this.unidad = unidad;
		this.fila = fila;
		this.columna = columna;
	}

	public UnidadesDeMapa getUnidad() {
		return unidad;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setUnidad(UnidadesDeMapa unidad) {
		this.unidad = unidad;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

}