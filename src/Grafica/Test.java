package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Consola.Ejercito;

public class Test extends JFrame {

	Ejercito ejercito1 = new Ejercito("BReino1", Color.black);
	Ejercito ejercito2 = new Ejercito("AReino2", Color.yellow);

	public Test(Ejercito ejercito1, Ejercito ejercito2) throws HeadlessException {
		super();
		this.ejercito1 = ejercito1;
		this.ejercito2 = ejercito2;
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
