package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Consola.UnidadesDeMapa;
import Soldado.Soldado;

public class Pelea2Unidades extends JFrame {

	private static final int WIDTH = 500;
	private static final int HIGH = 500;

	private UnidadesDeMapa u1, u2;

	protected JPanel principal, lucharBoton, panelU1, panelU2, opcionesU1, opcionesU2;
	protected TextArea textUnidad1, textUnidad2;
	protected JButton luchar = new JButton("LUCHAR");

	public Pelea2Unidades(UnidadesDeMapa unidadesDeMapa, UnidadesDeMapa unidadesDeMapa2) {
		super("Pelea Unidades");
		setSize(WIDTH, HIGH);
		u1 = unidadesDeMapa;
		u2 = unidadesDeMapa2;
		setLayout(new BorderLayout());
		principal = new JPanel();
		principal.setLayout(new GridLayout(1, 2));
		add(principal, BorderLayout.CENTER);

		lucharBoton = new JPanel();
		lucharBoton.add(luchar);
		add(lucharBoton, BorderLayout.SOUTH);
		addComponentes();
//		setVisible(true);
	}

	private void addComponentes() {
		textUnidad1 = new TextArea();
		textUnidad2 = new TextArea();

		opcionesU1 = new JPanel();
		opcionesU2 = new JPanel();

		panelU1 = new JPanel();
		panelU1.setLayout(new GridLayout(2, 1, 5, 5));
		panelU1.setBackground(u1.getColor());
		panelU1.add(textUnidad1);
		panelU1.add(opcionesU1);

		panelU2 = new JPanel();
		panelU2.setLayout(new GridLayout(2, 1, 5, 5));
		panelU2.setBackground(u2.getColor());
		panelU2.add(textUnidad2);
		panelU2.add(opcionesU2);

		principal.add(panelU1);
		principal.add(panelU2);

	}

}
