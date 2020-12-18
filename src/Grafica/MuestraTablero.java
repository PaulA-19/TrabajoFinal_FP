package Grafica;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Consola.*;

public class MuestraTablero extends JFrame {

	private Game game;
	private Mapeable u1, u2;

	private JFrame ventana;
	private TextArea textActua = new TextArea(20, 22), textMover = new TextArea(20, 22);
	private TextArea turnoText;
	private TextArea[] turnosText;
	private JPanel presentacionTurno, opciones, juego, informe;
	private JLabel nombreTurno = new JLabel();;
	private JButton mover;

	public MuestraTablero(Game game) {
		this.game = game;
		ventana = this;
		setSize(1200, 500);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
//		p = new Tablero(u1, u2);
//		p.getMapa().mostrarTabla(); // Consola
		addComponentes();
//		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void addComponentes() {

		juego = new JPanel();
		juego.setBackground(Color.blue);

		// Norte
		presentacionTurno = new JPanel();
//		presentacion.setBackground(Color.red);
		presentacionTurno.setLayout(new GridLayout(1, 2));
		addPresentacion();
		this.add(presentacionTurno, BorderLayout.NORTH);

		// Este
		informe = new JPanel();
//		informe.setBackground(Color.green);
		informe.setLayout(new GridLayout(2, 1, 5, 5));
		addInforme();
		this.add(informe, BorderLayout.EAST);

		// Centro
		add(juego, BorderLayout.CENTER);

		// Sur
		mover = new JButton("Mover");
		this.add(mover, BorderLayout.SOUTH);

	}
	
	public void actualizarJuegoPanel(JPanel p) {
	
		setJuego(p);
		
		add(juego, BorderLayout.CENTER);

		
		
	}

	private void addPresentacion() {
		// Aqui añadimos componentes a la presentacion, parte norte
		presentacionTurno.add(new JLabel("Turno de: "));
		presentacionTurno.add(nombreTurno);

	}

	private void addInforme() {

		textActua.setEditable(false);
		textMover.setEditable(false);

		turnosText = new TextArea[2];
		turnosText[0] = textActua;
		turnosText[1] = textMover;

		JPanel pa = new JPanel();
		pa.setLayout(new BorderLayout());
		// Nombre del ejercito 1
		pa.add(new JLabel("Actual"), BorderLayout.NORTH);
		pa.add(textActua, BorderLayout.CENTER);
		informe.add(pa);

		pa = new JPanel();
		pa.setLayout(new BorderLayout());
		// Nombre del ejercito 2
		pa.add(new JLabel("Mover a "), BorderLayout.NORTH);
		pa.add(textMover, BorderLayout.CENTER);
		informe.add(pa);

		// Turno incial
		turnoText = textActua; // turno inicial

	}

	private void cambiarAreaText() {
		if (turnoText == turnosText[0]) {
			turnoText = turnosText[1];
		} else {
			turnoText = turnosText[0];
		}
	}

	// Get and set
	public Game getGame() {
		return game;
	}

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

	public void setGame(Game game) {
		this.game = game;
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

}
