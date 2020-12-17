package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Consola.Ejercito;
import Consola.Reino;

public class Test extends JFrame {

	Ejercito ejercito1;
	Ejercito ejercito2;

	public Test(Reino ejercito1, Reino ejercito2) {
		super("Test");
		setSize(500, 500);
		setLayout(new BorderLayout());
		Tablero p = new Tablero(ejercito1, ejercito2);
		add(p.getPrincipal(), BorderLayout.CENTER);
		p.getMapa().mostrarTabla();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Test() {
		super("Test");
		setSize(500, 500);
		setLayout(new BorderLayout());
		Tablero p = new Tablero(ejercito1, ejercito2);
		add(p.getPrincipal(), BorderLayout.CENTER);
		p.getMapa().mostrarTabla();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
