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
	private JFrame anterior, ventana;
	private Informe evento;
	private ActionListener eve;
	private boolean listoActual = false, listoMover = false;
	private Ejercito ejer1, ejer2;

	public MostrarTableroEjercitos(JFrame anterior) {
		super(anterior);
		setTitle("Mapa Completo");
		addComponentes();
		this.anterior = anterior;
		setVisible(true);
		this.ventana = this;
		verificarFinalJuego();
	}

	public MostrarTableroEjercitos() {
		super();
	}

	private void addComponentes() {
		Informe eve = new Informe();
		this.eve = eve;
		evento = eve;
		getMover().addActionListener(eve);
		getInicio().addActionListener(eve);
		getAtras().addActionListener(eve);
		turno = Game.getGame().getReino1().getName();
		panelJuego = new Tablero(Game.getGame().getReino1(), Game.getGame().getReino2(), anterior, eve);
		actualizarJuegoPanel(panelJuego.getPanel());
	}

	public void verificarFinalJuego() {
		if (Game.getGame().isFinal()) {
			JOptionPane.showMessageDialog(ventana,
					"El ganador es el Reino de " + (Game.getGame().ganadorReino().getName()));

			ventana.setVisible(false);
			ventana = null;
			Main.getVentanaInicio().setVisible(true);
		}
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

			// Atras
			if (e.getSource() == getAtras()) {
				System.out.println("Atras");
				ventana.setVisible(false);
				anterior.setVisible(true);
			}

			// Inicio
			if (e.getSource() == getInicio()) {
				System.out.println("Inicio");
				ventana.setVisible(false);
				Main.getVentanaInicio().setVisible(true);
			}

			// Mover

			if (e.getSource() == getMover()) {
				System.out.println("Mover");
				if (listoActual && listoMover) {
					JOptionPane.showMessageDialog(ventana, "Pelea");

					// datos reiniciados
					ejer1 = null;
					ejer2 = null;
					textActua.setText("");
					textMover.setText("");
					actualTexArea.setSelected(true);
					cambiarTurno();
					actualizarLabelTurno(turno);

				} else if (listoActual) {
					JOptionPane.showMessageDialog(ventana, "Debe selecionar donde desea mover su ejercito");
					moverTextArea.setSelected(true);
				} else {
					JOptionPane.showMessageDialog(ventana, "Debe de selecionar un ejercito");
					actualTexArea.setSelected(true);

				}

				verificarFinalJuego();
			}

			// Seleccion de ejercitos
			if (e.getSource() instanceof UnidadButton) {

				UnidadButton botonSeleccionado = (UnidadButton) e.getSource();
				// actual
				if (actualTexArea.isSelected()) {

					if (botonSeleccionado.getUnidad() == null) {
						JOptionPane.showMessageDialog(ventana, "No existe algun ejercito en esa posición");

					} else if (turno.equalsIgnoreCase(botonSeleccionado.getUnidad().getNameReino())) {

						// correcto
						ejer1 = (Ejercito) botonSeleccionado.getUnidad();
						textActua.setText(ejer1.mostrarDatos());
						moverTextArea.setSelected(true);
						listoActual = true;

						JOptionPane.showMessageDialog(ventana, "Seleccione a donde desea mover");
					} else {
						JOptionPane.showMessageDialog(ventana, "No pude seleccionar ejercitos de otros Reinos");
					}

				} else {
					if (botonSeleccionado.getUnidad() == null) {
						JOptionPane.showMessageDialog(ventana, "Vacio");
						ejer2 = (Ejercito) botonSeleccionado.getUnidad();
						textMover.setText("Vacio");
						actualTexArea.setSelected(true);
						listoMover = true;
						JOptionPane.showMessageDialog(ventana, "Ya puede mover");

					} else if (turno.equalsIgnoreCase(botonSeleccionado.getUnidad().getNameReino())) {

						// Misma unidad

						int opcion = JOptionPane.showConfirmDialog(ventana,
								"Existe un ejercito del mismo reino\nDesea Continuar", "Continuar",
								JOptionPane.YES_NO_OPTION);

						System.out.println(opcion);
						if (opcion == 0) {
							ejer2 = (Ejercito) botonSeleccionado.getUnidad();
							textMover.setText(ejer2.mostrarDatos());
							actualTexArea.setSelected(true);
							listoMover = true;
							JOptionPane.showMessageDialog(ventana, "Seleccione a donde desea mover");
						} else {
							return;
						}
					} else {
						ejer2 = (Ejercito) botonSeleccionado.getUnidad();
						textMover.setText(ejer2.mostrarDatos());
						actualTexArea.setSelected(true);
						listoMover = true;
						JOptionPane.showMessageDialog(ventana, "Ya puede mover");

					}

				}

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
