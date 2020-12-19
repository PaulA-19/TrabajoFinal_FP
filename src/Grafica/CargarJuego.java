package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Consola.Game;
import Consola.Reino;

public class CargarJuego extends JFrame {

	private static final int width = 700;
	private static final int high = 300;

	private JPanel principal, centro;
	private JFrame anterior;
	private CargarJuego ventana;
	private JButton configurar1, configurar2, jugar, listo1, listo2, cancelar;

	private JTextField numDato;

	public CargarJuego(JFrame anterior) {
		super("Nuevo Juego");
		this.anterior = anterior;
		ventana = this;
		setSize(width, high);
		principal = new JPanel();
		principal.setLayout(new BorderLayout());
		centro = new JPanel();
		centro.setLayout(new GridLayout(1, 2, 10, 10));
		getContentPane().add(principal);
		setLocationRelativeTo(null);
		addComponentes();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void addComponentes() {
		addPanel1();
		addPanel2();
		principal.add(centro, BorderLayout.CENTER);

		jugar = new JButton("JUGAR");
		jugar.addActionListener(new Accion());

		cancelar = new JButton("CANCELAR");
		cancelar.addActionListener(new Accion());

		JPanel p = new JPanel();
		p.add(jugar);
		p.add(cancelar);
		principal.add(p, BorderLayout.SOUTH);

	}

	public void actualizar() {
		principal.removeAll();
		addComponentes();
	}

	private void addPanel1() {

		JPanel p1G = new JPanel();
		p1G.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		p1.setBackground(Game.getGame().getReino1().getColor());
		p1.setLayout(new GridLayout(4, 2, 20, 20));
		llenarPanel(p1, Game.getGame().getReino1(), 1);
		p1G.add(new JLabel(Game.getGame().getReino1().getName()), BorderLayout.NORTH);
		p1G.add(p1, BorderLayout.CENTER);
		centro.add(p1G);
	}

	private void llenarPanel(JPanel p, Reino r, int numReino) {

		p.add(new JLabel(r.getName()));
		numDato = new JTextField(Integer.toString(r.getNumEjercitos()));
		numDato.setEditable(false);
		p.add(numDato);

		p.add(new JLabel("Num. Soldados"));
		numDato = new JTextField(Integer.toString(r.getNumSoldados()));
		numDato.setEditable(false);
		p.add(numDato);

		p.add(new JLabel("Promedio Vida"));
		numDato = new JTextField(Double.toString(r.promedioNivelVida()));
		numDato.setEditable(false);
		p.add(numDato);

		if (numReino == 1) {
			configurar1 = new JButton("Configurar");
			configurar1.addActionListener(new Accion());
			p.add(configurar1);
			listo1 = new JButton("Jugar");
			listo1.addActionListener(new Accion());
			p.add(listo1);
		} else {
			configurar2 = new JButton("Configurar");
			configurar2.addActionListener(new Accion());
			p.add(configurar2);
			listo2 = new JButton("Jugar");
			listo2.addActionListener(new Accion());
			p.add(listo2);

		}
	}

	private void addPanel2() {
		JPanel p2G = new JPanel();
		p2G.setLayout(new BorderLayout());
		JPanel p2 = new JPanel();
		p2.setBackground(Game.getGame().getReino2().getColor());
		p2.setLayout(new GridLayout(4, 2, 20, 20));
		llenarPanel(p2, Game.getGame().getReino2(), 2);
		p2G.add(new JLabel(Game.getGame().getReino2().getName()), BorderLayout.NORTH);
		p2G.add(p2, BorderLayout.CENTER);
		centro.add(p2G);
	}

	private class Accion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton botonPresionado = (JButton) e.getSource();

			if (botonPresionado.getText().contentEquals("Cancelar")) {
				Main.getVentanaInicio().setVisible(true);
				ventana.setVisible(false);
			}
			if ((e.getActionCommand().equalsIgnoreCase("Jugar")) && e.getSource() != jugar) {
				botonPresionado.setEnabled(false);
				if (botonPresionado == listo1) {
					configurar1.setEnabled(false);
				} else {
					configurar2.setEnabled(false);
				}
				botonPresionado.setText("LISTO");

			}

			if (e.getSource() == jugar) {
				System.out.println(listo1.isEnabled());
				System.out.println(listo2.isEnabled());

				if (listo1.isEnabled() || listo2.isEnabled()) {
					JOptionPane.showMessageDialog(ventana, "Todos deben de estar listos");
				} else {
					// Aqui Juagr
					System.out.println("Jugando");
				}
			}

			if (e.getSource() == configurar1) {
				ventana.setVisible(false);
				new Configurar(Game.getGame().getReino1(), ventana);
			}

			if (e.getSource() == configurar2) {
				System.out.println("Confi 2");
				ventana.setVisible(false);
				new Configurar(Game.getGame().getReino2(), ventana);
			}

			if (e.getSource() == cancelar) {
				ventana.setVisible(false);
				anterior.setVisible(true);
			}
		}

	}

	public JFrame getAnterior() {
		return anterior;
	}

	public void setAnterior(JFrame anterior) {
		this.anterior = anterior;
	}

}
