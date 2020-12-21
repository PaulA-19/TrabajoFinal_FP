package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Consola.Game;

public class Main extends JFrame implements Serializable {

	private JButton nuevo, cargar, salir, tutorial, guardar;
	private static Main ventanaInicio;
	private static JFrame anterior;
	private JPanel principal, opciones;

	public Main() {
		super("Juego Trabajo Final");
		principal = new JPanel();
		ventanaInicio = this;
		principal.setLayout(new BorderLayout());
		getContentPane().add(principal);
		setSize(350, 350);
		setLocationRelativeTo(null);
		addComponentes();
//		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void addComponentes() {
		JPanel pre = new JPanel();
		pre.add(new JLabel("Proyecto Final - LAB FP 2"));
		principal.add(pre, BorderLayout.NORTH);
		addBotones();
		principal.add(new JLabel("Alumno: Paul Alexander Luque Ccosi"), BorderLayout.SOUTH);
	}

	private void addBotones() {
		opciones = new JPanel();
		opciones.setLayout(new GridLayout(6, 1, 5, 5));

		Navegar accion = new Navegar();

		addBoton("Nuevo", "Nuevo Juego");
		addBoton("Continuar", "Continuar Juego");
		addBoton("Cargar", "Carga Juego Guardado");
		addBoton("Tutorial", "Un breve tutorial");
		addBoton("Guardar", "Guarda la partida");
		addBoton("Salir", "Salir del Juego");

		principal.add(opciones, BorderLayout.CENTER);
	}

	private void addBoton(String text, String descripcion) {
		JPanel p = new JPanel();
		JButton boton = new JButton(text);
		boton.addActionListener(new Navegar());
		p.setLayout(new GridLayout(1, 2, 10, 10));
		p.add(boton);
		p.add(new JLabel(descripcion));
		opciones.add(p);

	}

	public static void volverAMostrar(JFrame anterior) {
		setAnterior(anterior);
		ventanaInicio.setVisible(true);
	}

	// get and set
	public JPanel getPrincipal() {
		return principal;
	}

	public void setPrincipal(JPanel principal) {
		this.principal = principal;
	}

	private class Navegar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton boton = (JButton) e.getSource();

			// Nuevo
			if (boton.getText().equalsIgnoreCase("Nuevo")) {
				ventanaInicio.setVisible(false);
				Game.setGame(new Game());
				new PreviaJuego(ventanaInicio, "Nuevo Juego");

			}

			// Continuar
			if (boton.getText().equalsIgnoreCase("Continuar")) {
				if (getAnterior() == null) {
					JOptionPane.showMessageDialog(ventanaInicio, "Primero debe de crear o cargar un juego");
				} else {

					ventanaInicio.setVisible(false);
					getAnterior().setVisible(true);
				}
			}

			// Cargar
			if (boton.getText().equalsIgnoreCase("Cargar")) {
				System.out.println("Nuevo");
				String nameGame = JOptionPane.showInputDialog(ventanaInicio,
						"Ingrese el nombre con el que guadó el juego: ", Game.getUltimoGuardado());

				System.out.println("name: " + nameGame);
				// Lectura de el objeto game guardado

				if (nameGame != null) {

					ObjectInputStream fileIn;
					try {
						fileIn = new ObjectInputStream(new FileInputStream("./games/" + nameGame));
						Game gameGuardado = (Game) fileIn.readObject();
						Game.setGame(gameGuardado);
						fileIn.close();
						ventanaInicio.setVisible(false);
						new PreviaJuego(ventanaInicio, "Juego Cargado");

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(ventanaInicio, "No se encontró el juego guardado");
//					e2.getMessage();
					}
					// ------------
					System.out.println(nameGame);
				}
			}

			// Tutorial
			if (boton.getText().equalsIgnoreCase("Tutorial")) {
				JOptionPane.showMessageDialog(principal, "Enlace a la página");
			}

			// Salir
			if (boton.getText().equalsIgnoreCase("Salir")) {
				int value = JOptionPane.showConfirmDialog(principal, "¿Está seguro que desea salir?", "Salir",
						JOptionPane.YES_NO_OPTION);
				if (value == 0) {
					if (!(Game.isGuardado())) {
						int value1 = JOptionPane.showConfirmDialog(principal, "No guardo su partida\n¿Desea Salir?",
								"No Guardó Partida", JOptionPane.YES_NO_OPTION);
						if (value1 == 0) {
							System.exit(0);
						}
					} else {
						System.exit(0);

					}

				}
			}

			// Guardar
			if (boton.getText().equalsIgnoreCase("Guardar")) {
				int opcion = JOptionPane.showConfirmDialog(ventanaInicio, "Se guardara, el último juego que creó");
				if (opcion == 0) {
					// No existe juego
					if (Game.getGame() == null) {
						JOptionPane.showMessageDialog(ventanaInicio, "Debe crear o cargar una Partida");
					} else {
						String namegame = JOptionPane.showInputDialog(ventanaInicio,
								"Ingrese el nombre con el que se guardará");
						Game.guardarJuego(namegame);
					}
				}
				System.out.println("Guardar");
				System.out.println(opcion);
				System.out.println(Game.getGame());
			}
		}

		private Object newFileInputStream(String string) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static Main getVentanaInicio() {
		return ventanaInicio;
	}

	public static void setVentanaInicio(Main ventanaInicio) {
		Main.ventanaInicio = ventanaInicio;
	}

	public static JFrame getAnterior() {
		return anterior;
	}

	public static void setAnterior(JFrame anterior) {
		Main.anterior = anterior;
	}

}
