package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Consola.Ejercito;
import Consola.Mapeable;
import Consola.Reino;
import Consola.UnidadesDeMapa;

public class Test extends JFrame implements Serializable {

	Ejercito ejercito1;
	Ejercito ejercito2;

	public Test(Mapeable ejercito1, Mapeable ejercito2) {
		super("Test");
		setSize(500, 500);
		setLayout(new BorderLayout());
		Tablero p = new Tablero(ejercito1, ejercito2);
		add(p.getPanel(), BorderLayout.CENTER);
		p.getMapa().mostrarTabla();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Test() {
		super("Test");
		setSize(500, 500);
		setLayout(new BorderLayout());
		Tablero p = new Tablero(ejercito1, ejercito2);
		add(p.getPanel(), BorderLayout.CENTER);
		p.getMapa().mostrarTabla();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
