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

import Consola.Game;
import Consola.Reino;
import Consola.UnidadesDeMapa;

public class Configurar extends JFrame implements Serializable{

	private static final int width = 200;
	private static final int high = 550;

	private JFrame ventana, anterior;

	private JTextField name;
	private Reino reino;
	private JPanel principal, centro;
	private Color c;
	private String[] colores = Game.getColores();
	private JComboBox<String> opcionesColores;

	private JButton enviarCancelar, masOpciones;
	private JRadioButton ataque, defensa, neutro;
	private ButtonGroup grupoActitud;

	public Configurar(Reino r, JFrame ventana2) {
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
		opcionesColores = new JComboBox<String>(colores);
		opcionesColores.addItem("mas opciones");
		colors.add(opcionesColores);
		centro.add(colors);

		JPanel actitudes = new JPanel();
		llenarActitudes(actitudes);
		centro.add(actitudes);

		JPanel cambiarNombre = new JPanel();
		llenarCambiarNombre(cambiarNombre);
		centro.add(cambiarNombre);

		JPanel p = new JPanel();
		masOpciones = new JButton("Mas opciones");
		masOpciones.addActionListener(new Accion());
		p.add(masOpciones);
		centro.add(p);

	}

	private void llenarCambiarNombre(JPanel p) {
		p.add(new JLabel("Ingrese nuevo nombre: "));
		name = new JTextField(reino.getName(), 15);
		p.add(name);
	}

	private void llenarActitudes(JPanel p) {
		p.setLayout(new BorderLayout());
		JPanel pas = new JPanel();
		pas.add(new JLabel("Actitud del reino en general"));
		p.add(pas, BorderLayout.NORTH);
		grupoActitud = new ButtonGroup();
		ataque = new JRadioButton("Ofensivo - Atacar");
		defensa = new JRadioButton("Defensivo - Defender");
		neutro = new JRadioButton("Neutro - Normal");
		grupoActitud.add(ataque);
		grupoActitud.add(defensa);
		grupoActitud.add(neutro);

		neutro.setSelected(true);
		JPanel panelActitudes = new JPanel();
		panelActitudes.add(ataque);
		panelActitudes.add(defensa);
		panelActitudes.add(neutro);

		p.add(panelActitudes, BorderLayout.CENTER);

	}

	private Color optenerColor(String opcion) {

		// deberia de haber una forma mejor
//		"rojo", "verde", "azul", "celeste" 
		if (opcion.equalsIgnoreCase("rojo")) {
			return Color.red;
		} else if (opcion.equalsIgnoreCase("verde")) {
			return Color.green;
		} else if (opcion.equalsIgnoreCase("azul")) {
			return Color.blue;
		} else if (opcion.equalsIgnoreCase("amarillo")) {
			return Color.yellow;
		} else {
			JOptionPane.showMessageDialog(ventana, "Estamos trabajando con mas colores");
		}

		return null;
	}

	private class Accion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equalsIgnoreCase("Cancelar")) {
				ventana.setVisible(false);
				anterior.setVisible(true);

			}

			if (e.getActionCommand().equalsIgnoreCase("Guardar")) {

				// nombre
				reino.setName(name.getText());

				// Color
				Color c = optenerColor((String) opcionesColores.getSelectedItem());
				if (c != null) {
					reino.setC(c);
				}

				// Actitud
				if (ataque.isSelected()) {
					reino.setActitud(UnidadesDeMapa.ATAQUE);
				} else if (defensa.isSelected()) {
					reino.setActitud(UnidadesDeMapa.DEFENZA);
				} else {
					reino.setActitud(UnidadesDeMapa.NEUTRO);
				}

				ventana.setVisible(false);
				PreviaJuego antes = (PreviaJuego)anterior;
				anterior = new PreviaJuego(antes.getAnterior(), ventana.getTitle());

			}

			if (e.getSource() == masOpciones) {
				JOptionPane.showMessageDialog(ventana,
						"Lo sentimos :(\nAun no está desarrollado mas opciones\nEstamos trabajando en ello :)");
			}

		}

	}
}
