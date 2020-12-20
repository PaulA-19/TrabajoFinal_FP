package Grafica;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;

import com.sun.source.doctree.TextTree;

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

	public MostrarTableroEjercitos() {
		super();
	}

	private void addComponentes() {
		Informe2 eve = new Informe2();

		getMover().addActionListener(eve);

<<<<<<< HEAD
		panelJuego = new Tablero(Game.getGame().getReino1(), Game.getGame().getReino2(), anterior, eve);
=======
		panelJuego = new Tablero(Game.getGame().getReino1(), Game.getGame().getReino2(), textActua, textMover, turnoText, turnosText);
>>>>>>> 6524991be4416004afda3a470b8cc69af7efe874
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

			if (e.getActionCommand().equals("Mover")) {
				JOptionPane.showMessageDialog(ventana, "Moviendo 1");
			} else if (e.getSource() instanceof UnidadButton) {
				JOptionPane.showMessageDialog(ventana, "Otro boton evento 1");
			}
		}

	}

	public class Informe2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("Mover")) {
				JOptionPane.showMessageDialog(ventana, "Moviendo 2");
			} else if (e.getSource() instanceof UnidadButton) {
				JOptionPane.showMessageDialog(ventana, "Otro boton evento 2");
			}
		}

	}

}
