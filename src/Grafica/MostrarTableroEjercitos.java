package Grafica;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Consola.*;

public class MostrarTableroEjercitos extends MuestraTablero {

	private Mapa tabla;
	private String turno;
	private String turnoMostrar;
	private Tablero panelJuego;
	private Reino reino1, reino2;
	
	private Informe eventoInformar = new Informe();

	private boolean primerEjercito = true; // true, selecciona al primer ejercito, false selecciona al segundo ejercito
											// (puede ser vacio)

	public MostrarTableroEjercitos(Game game) {
		super(game);
		setTitle("Mapa Completo");
		reino1 = game.getReino1();
		reino2 = game.getReino2();
		System.out.println("---------------");
		System.out.println(reino1);
		System.out.println(reino2);

		addComponentes();
		setVisible(true);
	}

	private void addComponentes() {
		Informe eve = new Informe();
	
		getMover().addActionListener(eve);
		
		panelJuego = new Tablero(reino1, reino2, eve);
		actualizarJuegoPanel(panelJuego.getPanel());
	}

	private void cambiarTurno() {
		if (turno.equalsIgnoreCase(reino1.getName())) {
			turno = reino2.getName();
		} else {
			turno = reino1.getName();
		}
	}

	private void cambiarTurnoMostrar() {
		if (turnoMostrar.equalsIgnoreCase(reino1.getName())) {
			turnoMostrar = reino2.getName();
		} else {
			turnoMostrar = reino1.getName();
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
	
	private class AccionBoton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Entro aqui");
			UnidadButton boton = (UnidadButton) e.getSource();

			JOptionPane.showMessageDialog(null, "Aqui");
//			System.out.println(primerEjercito);
//			if (primerEjercito) { // primer ejercito , no puede ser vacio
//				try {
//					if (turno.equalsIgnoreCase(boton.getEjercito().getReino())) {
//						turnoText.setText(boton.getEjercito().mostrarDatos());
//						cambiarTurno();
//						cambiarAreaText();
//						nombreTurno.setText(turnoMostrar);
//
//						// cambia estado primerEjercito
//						if (primerEjercito) {
//							primerEjercito = false;
//						} else {
//							primerEjercito = true;
//						}
//
//						// ejerciot seleccionado 1
//						ejercitoSelecionado1 = boton.getEjercito();
//						JOptionPane.showMessageDialog(null, ejercitoSelecionado1.mostrarDatos());
//
//					} else {
//						JOptionPane.showMessageDialog(null, "No puede elegir ejercito de otro reino");
//
//					}
//
//				} catch (Exception e2) {
//					System.out.println(e2.getMessage());
//					JOptionPane.showMessageDialog(juego, "No existe el ejercito");
//				}
//			} else {
//				try {
//					ejercitoOponente = (EjerButton) e.getSource();
//					System.out.println("Fila: " + ejercitoOponente.getFila());
//					System.out.println("Columna: " + ejercitoOponente.getColumna());
//
//					if (boton.getEjercito() == null) {
//						turnoText.setText("Espacio Libre");
//						cambiarTurno();
//						cambiarAreaText();
//						nombreTurno.setText(turnoMostrar);
//
//						// cambia estado primerEjercito
//						if (primerEjercito) {
//							primerEjercito = false;
//						} else {
//							primerEjercito = true;
//						}
//
//						// ejerciot seleccionado 1
//						ejercitoSelecionado2 = boton.getEjercito();
//
//						JOptionPane.showMessageDialog(null, "Sitio vacio");
//
//					} else if (turno.equalsIgnoreCase(boton.getEjercito().getReino())) {
//						turnoText.setText(boton.getEjercito().mostrarDatos());
//						cambiarTurno();
//						cambiarAreaText();
//						nombreTurno.setText(turnoMostrar);
//
//						// cambia estado primerEjercito
//						if (primerEjercito) {
//							primerEjercito = false;
//						} else {
//							primerEjercito = true;
//						}
//
//						// ejerciot seleccionado 1
//						ejercitoSelecionado2 = boton.getEjercito();
//						JOptionPane.showMessageDialog(null, ejercitoSelecionado1.mostrarDatos());
//
//					} else {
//						JOptionPane.showMessageDialog(null, "Ya existe un ejercito tuyo");
//
//					}
//
//				} catch (Exception e2) {
//					System.out.println(e2.getMessage());
//					JOptionPane.showMessageDialog(juego, "No existe el ejercito");
//				}
//
//			}
		}

	}

	private class Mover implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton mover = (JButton) e.getSource();
			if (ejercitoSelecionado1 == null && ejercitoSelecionado2 == null) {
				JOptionPane.showMessageDialog(null, "Seleccione algun ejercito");
			} else if (ejercitoSelecionado2 == null) {
				JOptionPane.showMessageDialog(null, "Moviendo");
				ejercitoSelecionado1.setFila(ejercitoOponente.getFila());
				ejercitoSelecionado1.setColumna(ejercitoOponente.getColumna());
				juego.setVisible(false);
				juego.removeAll();
				addEjercitosJuego();
				juego.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "PELEANDO");
				Ejercito[] resultado = Ejercito.batallar(ejercitoSelecionado1, ejercitoSelecionado2);
				JOptionPane.showMessageDialog(null, "El ganador en el ejercito " + resultado[0].getPosicionCadena()
						+ ", del reino " + resultado[0].getReino());

				resultado[0].setFila(ejercitoOponente.getFila());
				resultado[0].setColumna(ejercitoOponente.getColumna());

				System.out.println(game);
				game.eliminarEjercito(resultado[1]);
				System.out.println(game);

				juego.setVisible(false);
				juego.removeAll();
				addEjercitosJuego();
				juego.setVisible(true);

			}
			textEjercito1.setText("");
			textEjercito2.setText("");

			cambiarTurno();
			cambiarTurnoMostrar();
			nombreTurno.setText(turnoMostrar);

			System.out.println(game.getReino1());
			System.out.println(game.getReino2());

			if (game.ejercitoVacio()) {
				JOptionPane.showMessageDialog(null, game.mostrarGanorBatalla());
				System.exit(0);
			}
		}
	}

	public Informe getEventoInformar() {
		return eventoInformar;
	}

	public void setEventoInformar(Informe eventoInformar) {
		this.eventoInformar = eventoInformar;
	};

	
}


	

