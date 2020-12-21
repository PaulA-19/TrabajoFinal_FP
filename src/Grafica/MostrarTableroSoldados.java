package Grafica;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;

import com.sun.source.doctree.TextTree;

import Consola.*;
import Soldado.Soldado;

public class MostrarTableroSoldados extends MuestraTablero implements Serializable {

	private Mapa tabla;
	private String turno;
	private String turnoMostrar;
//	private Tablero tableroJuego;
	private JFrame anterior, ventana, fututo;
	private InformeSol evento;
	private ActionListener eve;
	private boolean listoActual = false, listoMover = false;
	private Ejercito ejer1, ejer2;
	private Soldado sol1, sol2;
	private Color turnoColor;

	public MostrarTableroSoldados(Ejercito e1, Ejercito e2, JFrame anterior) {
		super(anterior);
		setTitle("Pelea Ejercitos");
		ejer1 = e1;
		ejer2 = e2;
		addComponentes();
		this.anterior = anterior;
		setVisible(true);
		this.ventana = this;
		verificarFinalBatalla();
	}

	public MostrarTableroSoldados() {
		super();
	}

	private void addComponentes() {
		InformeSol eve = new InformeSol();
		this.eve = eve;
		evento = eve;
		getMover().addActionListener(eve);
		getAtras().addActionListener(eve);
		turno = ejer1.getName();
		turnoColor = ejer1.getColor();
		actualizarLabelTurno(turno, turnoColor);
		tableroJuego = new Tablero(ejer1, ejer2, anterior, eve);
		actualizarJuegoPanel(tableroJuego);
	}

	public void verificarFinalBatalla() {
		if (!ejer1.isVive() || !ejer2.isVive()) {
			Ejercito ganador = Ejercito.getVivo(ejer1, ejer2);
			JOptionPane.showMessageDialog(ventana,
					"El ganador es el Ejercito " + ganador.getName() + "\nDel reino " + ganador.getNameReino());

			ventana.setVisible(false);
//			ventana = null;
			System.out.println("Ejercito 1: " + ejer1.isVive());
			System.out.println("Ejercito 2: " + ejer2.isVive());

			MostrarTableroEjercitos ante = (MostrarTableroEjercitos) anterior;
			ante.actualizarFilaColumnaGanador(ganador);
			ante.actualizarJuegoPanel(
					new Tablero(Game.getGame().getReino1(), Game.getGame().getReino2(), anterior, ante.getEvento()));
			ante.verificarFinalJuego();
//			anterior.setVisible(true);
		}
	}

	private void cambiarTurno() {
		if (turno.equalsIgnoreCase(ejer1.getName())) {
			turno = ejer2.getName();
			turnoColor = ejer2.getColor();
		} else {
			turno = ejer1.getName();
			turnoColor = ejer1.getColor();
		}
	}

	public class InformeSol implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// Atras-------------------------------------
			if (e.getSource() == getAtras()) {
				System.out.println("Atras");
				ventana.setVisible(false);
				anterior.setVisible(true);
			}

			// Mover--------------------------------------------

			if (e.getSource() == getMover()) {
				if (listoActual && listoMover) {

					if (sol2 == null) {
						JOptionPane.showMessageDialog(ventana, "Moviendo ejercito");
						MostrarTableroSoldados ventanaAnteCast = (MostrarTableroSoldados) ventana;

						sol1.setFila(oponente.getFila());
						sol1.setColumna(oponente.getColumna());

						ventanaAnteCast.actualizarJuegoPanel(new Tablero(ejer1, ejer2, anterior, evento));
						// datos reiniciados
						sol1 = null;
						sol2 = null;
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

//						ventana.setVisible(false);
						fututo = new Pelea2Soldados(sol1, sol2, ventana, evento, oponente);

						// datos reiniciados
						sol1 = null;
						sol2 = null;
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

				verificarFinalBatalla();
			}

			// Seleccion de
			// Soldaos------------------------------------------------------------------
			if (e.getSource() instanceof UnidadButton) {

				UnidadButton botonSeleccionado = (UnidadButton) e.getSource();
				Soldado solSelecBoton = (Soldado) botonSeleccionado.getUnidad();
				// actualTextArea
				if (actualTexArea.isSelected()) {

					if (solSelecBoton == null) {
						JOptionPane.showMessageDialog(ventana, "No existe algun Soldado en esa posición");

					} else if (turno.equalsIgnoreCase(solSelecBoton.getNameEjercito())) {

						// correcto
						sol1 = solSelecBoton;
						textActua.setText(sol1.mostrarDatos());
						moverTextArea.setSelected(true);
						listoActual = true;

						JOptionPane.showMessageDialog(ventana, "Seleccione a donde desea mover");
					} else {
						JOptionPane.showMessageDialog(ventana, "No pude seleccionar ejercitos de otros Ejercitos");
					}

				} else { // moverTextArea
					oponente = botonSeleccionado;
					if (botonSeleccionado.getUnidad() == null) {
						JOptionPane.showMessageDialog(ventana, "Vacio");
						sol2 = (Soldado) botonSeleccionado.getUnidad();
						textMover.setText("Vacio");
						actualTexArea.setSelected(true);
						listoMover = true;
						JOptionPane.showMessageDialog(ventana, "Ya puede mover");

					} else if (turno.equalsIgnoreCase(((Soldado) botonSeleccionado.getUnidad()).getNameEjercito())) {

						// Misma unidad

						int opcion = JOptionPane.showConfirmDialog(ventana,
								"Existe un Soldado del mismo Ejercito\n¿Desea Continuar?", "Continuar",
								JOptionPane.YES_NO_OPTION);

						if (opcion == 0) {
							sol2 = (Soldado) botonSeleccionado.getUnidad();
							textMover.setText(sol2.mostrarDatos());
							actualTexArea.setSelected(true);
							listoMover = true;
//							JOptionPane.showMessageDialog(ventana, "Seleccione a donde desea mover");
						} else {
							return;
						}
					} else {
						// otro soldado
						sol2 = (Soldado) botonSeleccionado.getUnidad();
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

	public Ejercito getEjer1() {
		return ejer1;
	}

	public Ejercito getEjer2() {
		return ejer2;
	}

	public void setEjer1(Ejercito ejer1) {
		this.ejer1 = ejer1;
	}

	public void setEjer2(Ejercito ejer2) {
		this.ejer2 = ejer2;
	}

}
