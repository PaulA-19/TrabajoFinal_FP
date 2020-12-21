package Grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import Consola.Ejercito;
import Consola.Game;
import Consola.UnidadesDeMapa;
import Soldado.Soldado;

public class Pelea2Soldados extends Pelea2Unidades implements Serializable {

	private Soldado s1, s2;

	private JRadioButton actitudA1, actitudD1, actitudN1, actitudA2, actitudD2, actitudN2;
	private JButton listo1, listo2;
	boolean listoP1 = false, listoP2 = false;
	private Soldado[] resultado;
	private JFrame ventana, anterior;
	private ActionListener evento;
	private UnidadButton oponente;

	public Pelea2Soldados(UnidadesDeMapa unidadesDeMapa, UnidadesDeMapa unidadesDeMapa2, JFrame antertior,
			ActionListener eve, UnidadButton oponente) {
		super(unidadesDeMapa, unidadesDeMapa2);
		setTitle("Pelea Soldados");
		s1 = (Soldado) unidadesDeMapa;
		s2 = (Soldado) unidadesDeMapa2;
		this.anterior = antertior;
		ventana = this;
		this.oponente = oponente;
		addComponentes();
		addEventos();
		setLocationRelativeTo(antertior);
		setVisible(true);
		evento = eve;

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
					JOptionPane.showMessageDialog(ventana, "El soldado " + s1.getName()
							+ "\n- Aumentaron en 1 su nivel de ataque\n- Disminuyeron en 1 su nivel de defensa");

				} else if (actitudD1.isSelected()) {
					s1.actitudDefender();
					JOptionPane.showMessageDialog(ventana, "El soldado " + s1.getName()
							+ "\n- Aumentaron en 1 su nivel de defensa\n- Disminuyeron en 1 su nivel de ataque");

				}

			}

			if (e.getSource() == listo2) {
				listoP2 = true;
				actitudA2.setEnabled(false);
				actitudD2.setEnabled(false);
				actitudN2.setEnabled(false);
				if (actitudA2.isSelected()) {
					s2.actitudAtacar();
					JOptionPane.showMessageDialog(ventana, "El soldado " + s2.getName()
							+ "\n- Aumentaron en 1 su nivel de ataque\n- Disminuyeron en 1 su nivel de defensa");

				} else if (actitudD2.isSelected()) {
					s2.actitudDefender();
					JOptionPane.showMessageDialog(ventana, "El soldado " + s2.getName()
							+ "\n- Aumentaron en 1 su nivel de defensa\n- Disminuyeron en 1 su nivel de ataque");

				}

			}

		}

	}

	private class Luchar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (listoP1 && listoP2) {
				JOptionPane.showMessageDialog(principal, "Luchando");
// -----------

				System.out.println("Antes");
//				System.out.println("Ejercito 1 : " + ejer1.isVive() + "-" + ejer1.getNameReino() + "(" + ejer1.getFila()
//						+ "," + ejer1.getColumna() + ")");
//				System.out.println("Ejercito 2 : " + ejer2.isVive() + "-" + ejer2.getNameReino() + "(" + ejer2.getFila()
//						+ "," + ejer2.getColumna() + ")");

//				System.out.println("Oponente: (" + oponente.getFila() + "," + oponente.getColumna() + ")");
				// Puede haber un bucle infinito
				resultado = (Soldado[]) Soldado.batalla(s1, s2);
				JOptionPane.showMessageDialog(principal, "Ganador :\n" + resultado[0].toString());

//				System.out.println("Ganador: " + resultado[0].getNameReino());
//				System.out.println("Perdedor: " + resultado[1].getNameReino());

				// actualizamos posicion
				resultado[0].setFila(oponente.getFila());
				resultado[0].setColumna(oponente.getColumna());

				// Beneficiado
				resultado[0].beneficiado();
				JOptionPane.showMessageDialog(ventana,
						"El Soldado " + resultado[0].getName() + "\nFue beneficiado por ganar la batalla");
//
//				System.out.println("Despues");
//				System.out.println("Ejercito 1 : " + ejer1.isVive() + "-" + ejer1.getNameReino() + "(" + ejer1.getFila()
//						+ "," + ejer1.getColumna() + ")");
//				System.out.println("Ejercito 2 : " + ejer2.isVive() + "-" + ejer2.getNameReino() + "(" + ejer2.getFila()
//						+ "," + ejer2.getColumna() + ")");

				// Actualizar TextArea
				textUnidad1.setText(s1.toString());
				textUnidad2.setText(s2.toString());

				MostrarTableroSoldados ante = (MostrarTableroSoldados) anterior;
				ante.actualizarJuegoPanel(new Tablero(ante.getEjer1(), ante.getEjer2(), anterior, evento));

				ventana.setVisible(false);
//				anterior.setVisible(true);
				ante.verificarFinalBatalla();
// --------

//				// Puede haber un bucle infinito
//				resultado = (Soldado[]) Soldado.batalla(s1, s2);
//				JOptionPane.showMessageDialog(principal, "Ganador :\n" + resultado[0].toString());
			} else {
				JOptionPane.showMessageDialog(principal, "Ambos participantes deben de estar LISTOS");

			}

			// Actualizar TextArea
			textUnidad1.setText(s1.toString());
			textUnidad2.setText(s2.toString());

		}

	}

}
