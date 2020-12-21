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
//	private Tablero tableroJuego;
	private JFrame anterior, ventana, fututo;
	private Informe evento;
	private ActionListener eve;
	private boolean listoActual = false, listoMover = false;
	private Ejercito ejer1, ejer2;
	private Color turnoColor;
	private boolean finalGame = false;

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
		getAtras().addActionListener(eve);
		turno = Game.getGame().getReino1().getName();
		turnoColor = Game.getGame().getReino1().getColor();
		actualizarLabelTurno(turno, turnoColor);
		tableroJuego = new Tablero(Game.getGame().getReino1(), Game.getGame().getReino2(), anterior, eve);
		actualizarJuegoPanel(tableroJuego);
	}

	public void verificarFinalJuego() {
		if (Game.getGame().isFinal()) {
			ventana.setVisible(true);
			JOptionPane.showMessageDialog(ventana,
					"El ganador es el Reino de " + (Game.getGame().ganadorReino().getName()));

			ventana.setVisible(false);
			ventana = null;
			Main.getVentanaInicio().setVisible(true);
			finalGame = true;
		}
	}

	private void cambiarTurno() {
		if (turno.equalsIgnoreCase(Game.getGame().getReino1().getName())) {
			turno = Game.getGame().getReino2().getName();
			turnoColor = Game.getGame().getReino2().getColor();
		} else {
			turno = Game.getGame().getReino1().getName();
			turnoColor = Game.getGame().getReino1().getColor();
		}
	}

	public void actualizarFilaColumnaGanador(Ejercito ejerGanador) {
		ejerGanador.setFila(oponente.getFila());
		ejerGanador.setColumna(oponente.getColumna());
	}

	public class Informe implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// Atras
			if (e.getSource() == getAtras()) {
				System.out.println("Atras");
				ventana.setVisible(false);
				PreviaJuego ant = (PreviaJuego) anterior;
				anterior = new PreviaJuego(ant.getAnterior(), "Informe de juego Actual");
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

					if (ejer2 == null) {
						JOptionPane.showMessageDialog(ventana, "Moviendo ejercito");
						MostrarTableroEjercitos ven = (MostrarTableroEjercitos) ventana;

						ejer1.setFila(oponente.getFila());
						ejer1.setColumna(oponente.getColumna());

						ven.actualizarJuegoPanel(
								new Tablero(Game.getGame().getReino1(), Game.getGame().getReino2(), anterior, evento));
						// datos reiniciados
						ejer1 = null;
						ejer2 = null;
						listoActual = false;
						listoMover = false;
						textActua.setText("");
						textMover.setText("");
						actualTexArea.setSelected(true);
						cambiarTurno();
						actualizarLabelTurno(turno, turnoColor);

					} else {

						// pelea
						JOptionPane.showMessageDialog(ventana, "Preparando todo para la Batalla");

						String[] options = { "Ejercito Entero", "Soldado por Soldado" };
						int numOpcion = JOptionPane.showOptionDialog(ventana, "Seleccione Opcion de Batalla", "Opcion",
								1, 1, null, options, options[0]);
						if (numOpcion == 1) {

// new Mostrarablero Soldado						
							new MostrarTableroSoldados(ejer1, ejer2, ventana);

							// Funciona
							ventana.setVisible(false);
						} else {
							fututo = new Pelea2Ejercitos(ejer1, ejer2, ventana, evento, oponente);

						}
						// -- -------
						// datos reiniciados
						ejer1 = null;
						ejer2 = null;
						listoActual = false;
						listoMover = false;
						textActua.setText("");
						textMover.setText("");
						actualTexArea.setSelected(true);
						cambiarTurno();
						actualizarLabelTurno(turno, turnoColor);
					}

				} else if (listoActual) {
					JOptionPane.showMessageDialog(ventana, "Debe selecionar donde desea mover su ejercito");
					moverTextArea.setSelected(true);
				} else {
					JOptionPane.showMessageDialog(ventana, "Debe de selecionar un ejercito");
					actualTexArea.setSelected(true);

				}

				verificarFinalJuego();
			}

			// Seleccion de ejercitos------------------------
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
					oponente = botonSeleccionado;
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

//	public class Informe2 implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//
//			if (e.getActionCommand().equals("Mover")) {
//				JOptionPane.showMessageDialog(ventana, "Moviendo 2");
//			} else if (e.getSource() instanceof UnidadButton) {
//				JOptionPane.showMessageDialog(ventana, "Otro boton evento 2");
//			}
//		}
//
//	}

	public Informe getEvento() {
		return evento;
	}

	public boolean isFinalGame() {
		return finalGame;
	}

	public void setFinalGame(boolean finalGame) {
		this.finalGame = finalGame;
	}

	public void setEvento(Informe evento) {
		this.evento = evento;
	}

}
