package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Consola.Game;
import Consola.Reino;

public class PreviaJuego extends JFrame implements Serializable {

	private static final int width = 700;
	private static final int high = 400;

	private JPanel principal, centro;
	private JFrame anterior;
	private PreviaJuego ventana;
	private JButton configurar1, configurar2, jugar, listo1, listo2, cancelar;

	private JTextField numDato;

	public PreviaJuego(JFrame anterior, String title) {
		super(title);
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

		cancelar = new JButton("ATRAS");
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
		p1.setLayout(new GridLayout(6, 2, 20, 20));
		llenarPanel(p1, Game.getGame().getReino1(), 1);
		p1G.add(new JLabel(Game.getGame().getReino1().getName()), BorderLayout.NORTH);
		p1G.add(p1, BorderLayout.CENTER);
		centro.add(p1G);
	}

	private void llenarPanel(JPanel p, Reino r, int numReino) {

		p.add(new JLabel("Num. Ejercitos: "));
		numDato = new JTextField(Integer.toString(r.getNumEjercitosVivos()));
		numDato.setEditable(false);
		p.add(numDato);

		p.add(new JLabel("Num. Soldados: "));
		numDato = new JTextField(Integer.toString(r.getNumSoldadosVivos()));
		numDato.setEditable(false);
		p.add(numDato);

		p.add(new JLabel("Promedio Vida: "));
		numDato = new JTextField(Double.toString(r.promedioNivelVidaVivos()));
		numDato.setEditable(false);
		p.add(numDato);

		p.add(new JLabel("Promedio Ataque: "));
		numDato = new JTextField(Double.toString(r.promedioNivelAtaque()));
		numDato.setEditable(false);
		p.add(numDato);

		p.add(new JLabel("Promedio Defensa: "));
		numDato = new JTextField(Double.toString(r.promedioNivelDefensa()));
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
		p2.setLayout(new GridLayout(6, 2, 20, 20));
		llenarPanel(p2, Game.getGame().getReino2(), 2);
		p2G.add(new JLabel(Game.getGame().getReino2().getName()), BorderLayout.NORTH);
		p2G.add(p2, BorderLayout.CENTER);
		centro.add(p2G);
	}

	private class Accion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton botonPresionado = (JButton) e.getSource();

			// Atras
			if (botonPresionado.getText().contentEquals("ATRAS")) {
				Main.volverAMostrar(ventana);
				ventana.setVisible(false);
			}

			// Listo
			if ((e.getActionCommand().equalsIgnoreCase("Jugar")) && e.getSource() != jugar) {
				botonPresionado.setEnabled(false);
				if (botonPresionado == listo1) {
					configurar1.setEnabled(false);
				} else {
					configurar2.setEnabled(false);
				}
				botonPresionado.setText("LISTO");

			}

			// Jugar
			if (e.getSource() == jugar) {

				if (listo1.isEnabled() || listo2.isEnabled()) {
					JOptionPane.showMessageDialog(ventana, "Todos deben de estar listos");
				} else {
					// Aqui juego
					ventana.setVisible(false);
					new MostrarTableroEjercitos(ventana);

				}
			}

			// configurar 1
			if (e.getSource() == configurar1) {
				ventana.setVisible(false);
				new Configurar(Game.getGame().getReino1(), ventana);
			}

			// configurar 2
			if (e.getSource() == configurar2) {
				System.out.println("Confi 2");
				ventana.setVisible(false);
				new Configurar(Game.getGame().getReino2(), ventana);
			}

			// cancelar
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
