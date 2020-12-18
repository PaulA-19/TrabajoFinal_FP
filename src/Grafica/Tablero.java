package Grafica;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Consola.*;

public class Tablero extends JPanel {

	private JPanel panel;
	private Mapa mapa;
	private ArrayList<UnidadesDeMapa> unidades1;
	private ArrayList<UnidadesDeMapa> unidades2;
	private UnidadButton unidadBoton;
	private String textUnidadBoton;

	private ActionListener e;

	public Tablero(Mapeable u1, Mapeable u2, ActionListener e) {

		this.mapa = new Mapa("TipoMapa", u1, u2);
		unidades1 = u1.getUnidades();
		unidades2 = u2.getUnidades();
		addComponentes();
		this.e = e;
		System.out.println("Evento: "+e);
		mapa.mostrarTabla(); // consola

	}

	private void addComponentes() {
		panel = new JPanel();
		panel.setBackground(Color.yellow);
		panel.setLayout(new GridLayout(10, 10, 5, 5));
		addUnidadesTablero();

	}

	private void addUnidadesTablero() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				UnidadesDeMapa u = mapa.getUnidad(i, j); // Si existe un ejercito en con esa coordenada
				unidadBoton = new UnidadButton(u, i, j);

//				unidadBoton.addActionListener(new Informe());
				unidadBoton.addActionListener(e);
//				System.out.println(unidadBoton);

				if (u == null) {
					unidadBoton.setText("");
				} else {
					unidadBoton.setText(u.datosPuntuales());

					unidadBoton.setBackground(u.getColor());
				}
				panel.add(unidadBoton); // Añadimos al panel

			}

		}
	}

	// get and set
	public JPanel getPanel() {
		return panel;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public String getTextUnidadBoton() {
		return textUnidadBoton;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void setTextUnidadBoton(String textUnidadBoton) {
		this.textUnidadBoton = textUnidadBoton;
	}

	public ArrayList<UnidadesDeMapa> getUnidades1() {
		return unidades1;
	}

	public ArrayList<UnidadesDeMapa> getUnidades2() {
		return unidades2;
	}

	public UnidadButton getUnidadBoton() {
		return unidadBoton;
	}

	public void setPrincipal(JPanel principal) {
		this.panel = principal;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public void setUnidades1(ArrayList<UnidadesDeMapa> unidades1) {
		this.unidades1 = unidades1;
	}

	public void setUnidades2(ArrayList<UnidadesDeMapa> unidades2) {
		this.unidades2 = unidades2;
	}

	public void setUnidadBoton(UnidadButton unidadBoton) {
		this.unidadBoton = unidadBoton;
	}

	private class Informe implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UnidadButton boton = (UnidadButton) e.getSource();
			String text = "No existe soldado";

			try {
				text = boton.getUnidad().toString();
				textUnidadBoton = boton.getUnidad().mostrarDatos();

			} catch (Exception e2) {
				text = "NO existe nada";
				textUnidadBoton = "Vacio";
			}

			JOptionPane.showMessageDialog(panel, text);
		}

	}

}