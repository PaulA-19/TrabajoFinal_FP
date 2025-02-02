package Grafica;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;

import Consola.*;

public abstract class MuestraTablero extends JFrame implements Serializable {

	private Mapeable u1, u2;

	private JFrame ventana, anterior;
	protected TextArea textActua = new TextArea(20, 30), textMover = new TextArea(20, 30);
	protected TextArea turnoText;
	protected TextArea[] turnosText;
	private JPanel presentacionTurno, opciones, juego, informe;
	protected JLabel nombreTurno = new JLabel();;
	private JButton mover, inicio, atras;
	protected UnidadButton boton, oponente;
	protected JRadioButton actualTexArea, moverTextArea;
	protected Tablero tableroJuego;

	public MuestraTablero(JFrame anterior) {
		ventana = this;
		this.anterior = anterior;
		setSize(1400, 500);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		addComponentes();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public MuestraTablero() {

	}

	private void addComponentes() {

		juego = new JPanel();
		juego.setBackground(Color.blue);

		// Norte
		presentacionTurno = new JPanel();
		addPresentacion();
		this.add(presentacionTurno, BorderLayout.NORTH);

		// Este
		informe = new JPanel();
		informe.setLayout(new GridLayout(2, 1, 5, 5));
		addInforme();
		this.add(informe, BorderLayout.EAST);

		// Centro
		add(juego, BorderLayout.CENTER);

		// Sur
		mover = new JButton("Mover");
		inicio = new JButton("Inicio");
		inicio.addActionListener(new IrInicio());
		atras = new JButton("Atras");
		JPanel op = new JPanel();
		op.add(atras);
		op.add(mover);
		op.add(inicio);
		this.add(op, BorderLayout.SOUTH);

	}

	public void actualizarJuegoPanel(Tablero p) {

		juego.setVisible(false);
		ventana.add(p.getPanel(), BorderLayout.CENTER);
		setJuego(p.getPanel());

	}

	private void addPresentacion() {
		presentacionTurno.add(nombreTurno);

	}

	public void actualizarLabelTurno(String turno, Color turnoColor) {
		nombreTurno.setText("Turno de Reino: " + turno);
		nombreTurno.setForeground(turnoColor);

	}

	private void addInforme() {

		textActua.setEditable(false);
		textMover.setEditable(false);

		turnosText = new TextArea[2];
		turnosText[0] = textActua;
		turnosText[1] = textMover;

		actualTexArea = new JRadioButton("Selecionar unidad");
		moverTextArea = new JRadioButton("Selecionar Donde Mover");

		ButtonGroup actualMoverGrupo = new ButtonGroup();
		actualMoverGrupo.add(actualTexArea);
		actualMoverGrupo.add(moverTextArea);

		actualTexArea.setSelected(true);

		JPanel pa = new JPanel();
		JPanel arriba = new JPanel();
		arriba.setLayout(new GridLayout(2, 1));
		arriba.add(actualTexArea);
		arriba.add(new JLabel("Actual"));
		pa.setLayout(new BorderLayout());
		// Nombre del ejercito 1
		pa.add(arriba, BorderLayout.NORTH);
		pa.add(textActua, BorderLayout.CENTER);
		informe.add(pa);

		pa = new JPanel();
		pa.setLayout(new BorderLayout());
		JPanel arriba2 = new JPanel();
		arriba2.setLayout(new GridLayout(2, 1));
		arriba2.add(moverTextArea);
		arriba2.add(new JLabel("Mover"));
		pa.add(arriba2, BorderLayout.NORTH);
		pa.add(textMover, BorderLayout.CENTER);
		informe.add(pa);

		// Turno incial
		turnoText = textActua; // turno inicial

	}

//	private void cambiarAreaText() {
//		if (turnoText == turnosText[0]) {
//			turnoText = turnosText[1];
//		} else {
//			turnoText = turnosText[0];
//		}
//	}

	private class IrInicio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Main.volverAMostrar(ventana);
			ventana.setVisible(false);
		}

	}

	// Get and set

	public Mapeable getU1() {
		return u1;
	}

	public Mapeable getU2() {
		return u2;
	}

	public JFrame getVentana() {
		return ventana;
	}

	public TextArea getTextActua() {
		return textActua;
	}

	public TextArea getTextMover() {
		return textMover;
	}

	public TextArea getTurnoText() {
		return turnoText;
	}

	public TextArea[] getTurnosText() {
		return turnosText;
	}

	public JPanel getPresentacionTurno() {
		return presentacionTurno;
	}

	public JPanel getOpciones() {
		return opciones;
	}

	public JPanel getJuego() {
		return juego;
	}

	public JPanel getInforme() {
		return informe;
	}

	public JLabel getNombreTurno() {
		return nombreTurno;
	}

	public JButton getMover() {
		return mover;
	}

	public void setU1(Mapeable u1) {
		this.u1 = u1;
	}

	public void setU2(Mapeable u2) {
		this.u2 = u2;
	}

	public void setVentana(JFrame ventana) {
		this.ventana = ventana;
	}

	public void setTextActua(TextArea textActua) {
		this.textActua = textActua;
	}

	public void setTextMover(TextArea textMover) {
		this.textMover = textMover;
	}

	public void setTurnoText(TextArea turnoText) {
		this.turnoText = turnoText;
	}

	public void setTurnosText(TextArea[] turnosText) {
		this.turnosText = turnosText;
	}

	public void setPresentacionTurno(JPanel presentacionTurno) {
		this.presentacionTurno = presentacionTurno;
	}

	public void setOpciones(JPanel opciones) {
		this.opciones = opciones;
	}

	public void setJuego(JPanel juego) {
		this.juego = juego;
	}

	public void setInforme(JPanel informe) {
		this.informe = informe;
	}

	public void setNombreTurno(JLabel nombreTurno) {
		this.nombreTurno = nombreTurno;
	}

	public void setMover(JButton mover) {
		this.mover = mover;
	}

	public JButton getInicio() {
		return inicio;
	}

	public JButton getAtras() {
		return atras;
	}

	public void setInicio(JButton inicio) {
		this.inicio = inicio;
	}

	public void setAtras(JButton atras) {
		this.atras = atras;
	}

}
