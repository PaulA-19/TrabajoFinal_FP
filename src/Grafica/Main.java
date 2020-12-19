package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Consola.Game;

public class Main extends JFrame {

	private JButton nuevo, cargar, salir, tutorial;
	private static Main ventanaInicio;
	private JPanel principal, opciones;

	public Main() {
		super("Juego Trabajo Final");
		principal = new JPanel();
		ventanaInicio = this;
		principal.setLayout(new BorderLayout());
		getContentPane().add(principal);
		setSize(350, 300);
		setLocationRelativeTo(null);
		addComponentes();
		setResizable(false);
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
		opciones.setLayout(new GridLayout(4, 1, 5, 5));

		Navegar accion = new Navegar();

		addBoton("Nuevo", "Nuevo Juego");
		addBoton("Cargar", "Carga Juego Guardado");
		addBoton("Tutorial", "Un breve tutorial");
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

			if (boton.getText().equalsIgnoreCase("Nuevo")) {
				System.out.println("Nuevo");
				ventanaInicio.setVisible(false);
				Game.setGame(new Game());
				new NuevoJuego(ventanaInicio);

			}

			if (boton.getText().equalsIgnoreCase("Cargar")) {
				System.out.println("Nuevo");
				String nameGame = JOptionPane.showInputDialog(ventanaInicio,
						"Ingrese el nombre con el que guadó el juego: ");

				// Lectura de el objeto game guardado

				ObjectInputStream fileIn;
				try {
					fileIn = new ObjectInputStream(new FileInputStream("./games/" + nameGame));
					Game gameGuardado = (Game) fileIn.readObject();
					Game.setGame(gameGuardado);
					fileIn.close();
					ventanaInicio.setVisible(false);
					Game.setGame(new Game());
					new CargarJuego(ventanaInicio);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(ventanaInicio, "No se encontró el juego guardado");
//					e2.getMessage();
				}
				// ------------
				System.out.println(nameGame);

			}

			if (boton.getText().equalsIgnoreCase("Tutorial")) {
				JOptionPane.showMessageDialog(principal, "Enlace a la página");
			}

			// Salir
			if (boton.getText().equalsIgnoreCase("Salir")) {
				int value = JOptionPane.showConfirmDialog(principal, "¿Está seguro que desea salir?", "Salir",
						JOptionPane.YES_NO_OPTION);
				if (value == 0) {
					System.exit(0);
				}
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

}
