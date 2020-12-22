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

public class Pelea2Ejercitos extends Pelea2Unidades implements Serializable {

	private Ejercito ejer1, ejer2;

	private JRadioButton actitudA1, actitudD1, actitudN1, actitudA2, actitudD2, actitudN2;
	private JButton listo1, listo2;
	boolean listoP1 = false, listoP2 = false;
	private Ejercito[] resultado;
	private JFrame ventana, anterior;
	private ActionListener evento;
	private UnidadButton oponente;

	public Pelea2Ejercitos(UnidadesDeMapa unidadesDeMapa, UnidadesDeMapa unidadesDeMapa2, JFrame antertior,
			ActionListener eve, UnidadButton oponente) {
		super(unidadesDeMapa, unidadesDeMapa2);
		this.anterior = antertior;
		ventana = this;
		this.oponente = oponente;
		setTitle("Batalla ejercitos");
		ejer1 = (Ejercito) unidadesDeMapa;
		ejer2 = (Ejercito) unidadesDeMapa2;
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

		textUnidad1.setText(ejer1.toString());
		textUnidad1.setEditable(false);
		textUnidad2.setText(ejer2.toString());
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
					ejer1.actitudAtacar();
					JOptionPane.showMessageDialog(ventana, "Todos los soldados de " + ejer1.getName()
							+ "\n- Aumentaron en 1 su nivel de ataque\n- Disminuyeron en 1 su nivel de defensa");
				} else if (actitudD1.isSelected()) {
					ejer1.actitudDefender();
					JOptionPane.showMessageDialog(ventana, "Todos los soldados de " + ejer1.getName()
							+ "\n- Aumentaron en 1 su nivel de defensa\n- Disminuyeron en 1 su nivel de Ataque");

				}

			}

			if (e.getSource() == listo2) {
				listoP2 = true;
				actitudA2.setEnabled(false);
				actitudD2.setEnabled(false);
				actitudN2.setEnabled(false);
				if (actitudA2.isSelected()) {
					ejer2.actitudAtacar();
					JOptionPane.showMessageDialog(ventana, "Todos los soldados de " + ejer2.getName()
							+ "\n- Aumentaron en 1 su nivel de ataque\n- Disminuyeron en 1 su nivel de defensa");

				} else if (actitudD2.isSelected()) {
					ejer2.actitudDefender();
					JOptionPane.showMessageDialog(ventana, "Todos los soldados de " + ejer2.getName()
							+ "\n- Aumentaron en 1 su nivel de defensa\n- Disminuyeron en 1 su nivel de Ataque");

				}

			}

		}

	}

	private class Luchar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (listoP1 && listoP2) {
				JOptionPane.showMessageDialog(principal, "Luchando");

				resultado = (Ejercito[]) Ejercito.batalla(ejer1, ejer2);

				// actualizamos posicion
				resultado[0].setFila(oponente.getFila());
				resultado[0].setColumna(oponente.getColumna());

				// Beneficiado
				resultado[0].beneficiado();
				JOptionPane.showMessageDialog(ventana,
						"El ejercito " + resultado[0].getName() + "\nFue beneficiado por ganar la batalla");

				// Actualizar TextArea
				textUnidad1.setText(ejer1.toString());
				textUnidad2.setText(ejer2.toString());

				MostrarTableroEjercitos ante = (MostrarTableroEjercitos) anterior;
				ante.actualizarJuegoPanel(
						new Tablero(Game.getGame().getReino1(), Game.getGame().getReino2(), anterior, evento));

				ventana.setVisible(false);
				ante.verificarFinalJuego();

			} else {
				JOptionPane.showMessageDialog(principal, "Ambos participantes deben de estar LISTOS");

			}

		}

	}

}
