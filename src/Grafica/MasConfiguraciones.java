package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Consola.Ejercito;
import Consola.Game;
import Consola.Reino;
import Consola.UnidadesDeMapa;

public class MasConfiguraciones extends JFrame implements Serializable {

	private static final int width = 200;
	private static final int high = 400;

	private JFrame ventana, anterior;

	private JTextField name;
	private Reino reino;
	private JPanel principal, centro;
	private Color c;
	private String[] colores = Game.getColores();
	private JComboBox<String> nombresEjercitos;
	private JButton añadir, eliminar;
	private JButton enviarCancelar, masOpciones;
	private JRadioButton ataque, defensa, neutro;
	private ButtonGroup grupoActitud;

	public MasConfiguraciones(Reino r, JFrame ventana2) {
		super("Configurar");
		this.anterior = ventana2;
		reino = r;
		ventana = this;
		setSize(width, high);
		principal = new JPanel();
		principal.setLayout(new BorderLayout());
		centro = new JPanel();
		centro.setLayout(new GridLayout(4, 1, 10, 10));
		getContentPane().add(principal);
		setLocationRelativeTo(null);
		addComponentes();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void addComponentes() {
		JPanel p = new JPanel();
		p.add(new JLabel(reino.getName()));
		principal.add(p, BorderLayout.NORTH);

		addCentro();

		principal.add(centro, BorderLayout.CENTER);

		JPanel p1 = new JPanel();
		enviarCancelar = new JButton("Guardar");
		enviarCancelar.addActionListener(new Accion());
		p1.add(enviarCancelar);
		enviarCancelar = new JButton("Cancelar");
		enviarCancelar.addActionListener(new Accion());

		p1.add(enviarCancelar);

		principal.add(p1, BorderLayout.SOUTH);

	}

	private void addCentro() {
		JPanel colors = new JPanel();
		colors.add(new JLabel("Color: "));
		nombresEjercitos = new JComboBox<String>(reino.nombresEjercitos());
		nombresEjercitos.setSelectedIndex(0);
		colors.add(nombresEjercitos);
		centro.add(colors);

		JPanel cambiarNombre = new JPanel();
		llenarCambiarNombre(cambiarNombre);
		centro.add(cambiarNombre);

		JPanel nuevo = new JPanel();
		añadir = new JButton("Añadir Nuevo");
		añadir.addActionListener(new Accion());
		nuevo.add(añadir);
		centro.add(nuevo);

		JPanel p = new JPanel();
		eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new Accion());
		p.add(eliminar);
		centro.add(p);

	}

	private void llenarCambiarNombre(JPanel p) {
		p.add(new JLabel("Ingrese nuevo nombre: "));
		name = new JTextField(15);
		p.add(name);
	}

	private class Accion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// cancelar
			if (e.getActionCommand().equalsIgnoreCase("Cancelar")) {
				ventana.setVisible(false);
				anterior.setVisible(true);
			}

			// guardar
			if (e.getActionCommand().equalsIgnoreCase("Guardar")) {

				// nombre
				String newName = name.getText();

				Ejercito selectEjercito = reino.obtenerEjer((String) nombresEjercitos.getSelectedItem());

				selectEjercito.setName(newName);

				ventana.setVisible(false);
				anterior.setVisible(true);

			}

			if (e.getSource() == añadir) {

				reino.addNewEjercito();
				JOptionPane.showMessageDialog(ventana, "ejercito nuevo añadido");

				ventana.setVisible(false);
				anterior.setVisible(true);

			}

			if (e.getSource() == eliminar) {

				Ejercito ejerDelette = reino.obtenerEjer((String) nombresEjercitos.getSelectedItem());

				reino.deleteUnidad(ejerDelette);
				JOptionPane.showMessageDialog(ventana, "ejercito eliminado");
				ventana.setVisible(false);
				anterior.setVisible(true);

			}

		}
	}
}
