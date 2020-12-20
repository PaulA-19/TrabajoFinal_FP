package Grafica;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;
<<<<<<< HEAD
=======
import java.awt.event.ActionEvent;
>>>>>>> 6524991be4416004afda3a470b8cc69af7efe874
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

<<<<<<< HEAD
import javax.swing.JFrame;
=======
import javax.swing.JLabel;
import javax.swing.JOptionPane;
>>>>>>> 6524991be4416004afda3a470b8cc69af7efe874
import javax.swing.JPanel;

import Consola.*;
import Grafica.MostrarTableroEjercitos.Informe;

public class Tablero extends JPanel implements Serializable {

	private JPanel panel;
	private Mapa mapa;
	private ArrayList<UnidadesDeMapa> unidades1;
	private ArrayList<UnidadesDeMapa> unidades2;
	private UnidadButton unidadBoton;
	private String textUnidadBoton;
<<<<<<< HEAD
	private JFrame antes;

	// Protected
	protected TextArea textActual, textMover;
	protected TextArea turnoText;
	protected TextArea[] turnosText;
	private ActionListener evento;

	public Tablero(Mapeable u1, Mapeable u2, JFrame antes, ActionListener eve) {
=======

	// La otra clase
	private boolean primerEjercito = true; // true, selecciona al primer ejercito, false selecciona al segundo ejercito
	private String turno;
	private String turnoMostrar;
	private JLabel nombreTurno;
	private TextArea textEjercito1, textEjercito2;
	private Ejercito ejercitoSelecionado1;
	private Ejercito ejercitoSelecionado2;

	// Protected
	protected TextArea textActual, textMover;
	protected TextArea turnoText;
	protected TextArea[] turnosText;

	public Tablero(Mapeable u1, Mapeable u2, TextArea textActual, TextArea textMover, TextArea turnoText,
			TextArea[] turnosText) {

		this.textActual = textActual;
		this.textMover = textMover;
		this.turnoText = turnoText;
		this.turnosText = turnosText;

		System.out.println("Color 1" + u1.getColor());
		System.out.println("Color 2" + u2.getColor());
>>>>>>> 6524991be4416004afda3a470b8cc69af7efe874

		this.antes = antes;
		this.evento = eve;
		this.mapa = new Mapa("TipoMapa", u1, u2);
		unidades1 = u1.getUnidades();
		unidades2 = u2.getUnidades();
		addComponentes();
		mapa.mostrarTabla(); // consola

	}

	private void addComponentes() {
		panel = new JPanel();
		panel.setBackground(Color.yellow);
		panel.setLayout(new GridLayout(10, 10, 5, 5));
		addUnidadesTablero();

	}

	private void addUnidadesTablero() {
		MostrarTableroEjercitos eve ;
		if (evento instanceof MostrarTableroEjercitos.Informe2) {
			evento = (MostrarTableroEjercitos.Informe2)evento;
		} else if (evento instanceof MostrarTableroEjercitos.Informe) {
			evento = (MostrarTableroEjercitos.Informe)evento;

		} 

		System.out.println("Creo evento");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				UnidadesDeMapa u = mapa.getUnidad(i, j); // Si existe un ejercito en con esa coordenada

				unidadBoton = new UnidadButton(u, i, j);
<<<<<<< HEAD
				
				unidadBoton.addActionListener(evento);
=======
>>>>>>> 6524991be4416004afda3a470b8cc69af7efe874

				if (u == null) {
					unidadBoton.setText("");
				} else {
					unidadBoton.setText(u.datosPuntuales());

					unidadBoton.setBackground(u.getColor());
				}

<<<<<<< HEAD
=======
//				unidadBoton.addActionListener(new Informe());
>>>>>>> 6524991be4416004afda3a470b8cc69af7efe874
				panel.add(unidadBoton); // Añadimos al panel

			}

		}
	}

	// get and set
	public JPanel getPanel() {
		return panel;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public String getTextUnidadBoton() {
		return textUnidadBoton;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void setTextUnidadBoton(String textUnidadBoton) {
		this.textUnidadBoton = textUnidadBoton;
	}

	public ArrayList<UnidadesDeMapa> getUnidades1() {
		return unidades1;
	}

	public ArrayList<UnidadesDeMapa> getUnidades2() {
		return unidades2;
	}

	public UnidadButton getUnidadBoton() {
		return unidadBoton;
	}

	public void setPrincipal(JPanel principal) {
		this.panel = principal;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public void setUnidades1(ArrayList<UnidadesDeMapa> unidades1) {
		this.unidades1 = unidades1;
	}

	public void setUnidades2(ArrayList<UnidadesDeMapa> unidades2) {
		this.unidades2 = unidades2;
	}

	public void setUnidadBoton(UnidadButton unidadBoton) {
		this.unidadBoton = unidadBoton;
	}

<<<<<<< HEAD
=======
	private void cambiarTurno() {
		if (turno.equalsIgnoreCase(Game.getGame().getReino1().getName())) {
			turno = Game.getGame().getReino2().getName();
		} else {
			turno = Game.getGame().getReino1().getName();
		}
	}

	private void cambiarTurnoMostrar() {
		if (turnoMostrar.equalsIgnoreCase(Game.getGame().getReino1().getName())) {
			turnoMostrar = Game.getGame().getReino2().getName();
		} else {
			turnoMostrar = Game.getGame().getReino1().getName();
		}
	}

	private void cambiarAreaText() {
		if (turnoText == turnosText[0]) {
			turnoText = textMover;
		} else {
			turnoText = textActual;
		}
	}

>>>>>>> 6524991be4416004afda3a470b8cc69af7efe874
}
