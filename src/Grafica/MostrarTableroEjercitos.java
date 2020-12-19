package Grafica;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;

import Consola.*;

public class MostrarTableroEjercitos extends MuestraTablero implements Serializable {

	private Mapa tabla;
	private String turno;
	private String turnoMostrar;
	private Tablero panelJuego;
	private JFrame anterior;
	private MostrarTableroEjercitos ventana;

	private Informe eventoInformar = new Informe();

	private boolean primerEjercito = true; // true, selecciona al primer ejercito, false selecciona al segundo ejercito
											// (puede ser vacio)

	public MostrarTableroEjercitos(JFrame anterior) {
		super(anterior);
		setTitle("Mapa Completo");
		addComponentes();
		setVisible(true);
	}

	private void addComponentes() {
		Informe eve = new Informe();

		getMover().addActionListener(eve);

		panelJuego = new Tablero(Game.getGame().getReino1(), Game.getGame().getReino2(), eve);
		actualizarJuegoPanel(panelJuego.getPanel());
	}

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

	public class Informe implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("DEntro");

			UnidadButton boton = (UnidadButton) e.getSource();
			System.out.println("DEntro");
			String text = "No existe soldado";

			try {
				text = boton.getUnidad().toString();
//				textUnidadBoton = boton.getUnidad().mostrarDatos();

			} catch (Exception e2) {
				text = "NO existe nada";
//				textUnidadBoton = "Vacio";
			}

			JOptionPane.showMessageDialog(null, text);
		}

	}

}
