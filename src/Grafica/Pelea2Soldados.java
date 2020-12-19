package Grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import Consola.UnidadesDeMapa;
import Soldado.Soldado;

public class Pelea2Soldados extends Pelea2Unidades implements Serializable{

	private Soldado s1, s2;

	private JRadioButton actitudA1, actitudD1, actitudN1, actitudA2, actitudD2, actitudN2;
	private JButton listo1, listo2;
	boolean listoP1 = false, listoP2 = false;
	private Soldado[] resultado;

	public Pelea2Soldados(UnidadesDeMapa unidadesDeMapa, UnidadesDeMapa unidadesDeMapa2) {
		super(unidadesDeMapa, unidadesDeMapa2);
		setTitle("Pelea Soldados");
		s1 = (Soldado) unidadesDeMapa;
		s2 = (Soldado) unidadesDeMapa2;
		addComponentes();
		addEventos();

	}

	private void addEventos() {
		listo1.addActionListener(new Listo());
		listo2.addActionListener(new Listo());
		luchar.addActionListener(new Luchar());
	}

	private void addComponentes() {

		textUnidad1.setText(s1.toString());
		textUnidad1.setEditable(false);
		textUnidad2.setText(s2.toString());
		textUnidad2.setEditable(false);
		listo1 = new JButton("LISTO");
		listo2 = new JButton("LISTO");

		ButtonGroup grupoActitudes = new ButtonGroup();
		actitudA1 = new JRadioButton("Atacar");
		grupoActitudes.add(actitudA1);
		opcionesU1.add(actitudA1);
		actitudD1 = new JRadioButton("Defender");
		grupoActitudes.add(actitudD1);
		opcionesU1.add(actitudD1);
		actitudN1 = new JRadioButton("Normal");
		actitudN1.setSelected(true);
		grupoActitudes.add(actitudN1);
		opcionesU1.add(actitudN1);
		opcionesU1.add(listo1);

		grupoActitudes = new ButtonGroup();
		actitudA2 = new JRadioButton("Atacar");
		grupoActitudes.add(actitudA2);
		opcionesU2.add(actitudA2);
		actitudD2 = new JRadioButton("Defender");
		grupoActitudes.add(actitudD2);
		opcionesU2.add(actitudD2);
		actitudN2 = new JRadioButton("Normal");
		actitudN2.setSelected(true);
		grupoActitudes.add(actitudN2);
		opcionesU2.add(actitudN2);
		opcionesU2.add(listo2);
	}

	private class Listo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == listo1) {
				listoP1 = true;
				actitudA1.setEnabled(false);
				actitudD1.setEnabled(false);
				actitudN1.setEnabled(false);

				if (actitudA1.isSelected()) {
					s1.actitudAtacar();
				} else if (actitudD1.isSelected()) {
					s1.actitudDefender();
				}

			}

			if (e.getSource() == listo2) {
				listoP2 = true;
				actitudA2.setEnabled(false);
				actitudD2.setEnabled(false);
				actitudN2.setEnabled(false);
				if (actitudA2.isSelected()) {
					s2.actitudAtacar();
				} else if (actitudD2.isSelected()) {
					s2.actitudDefender();
				}

			}

		}

	}

	private class Luchar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (listoP1 && listoP2) {
				JOptionPane.showMessageDialog(principal, "Luchando");

				// Puede haber un bucle infinito
				resultado = (Soldado[]) Soldado.batalla(s1, s2);
				JOptionPane.showMessageDialog(principal, "Ganador :\n" + resultado[0].toString());
			} else {
				JOptionPane.showMessageDialog(principal, "Ambos participantes deben de estar LISTOS");

			}

			// Actualizar TextArea
			textUnidad1.setText(s1.toString());
			textUnidad2.setText(s2.toString());

		}

	}

}
