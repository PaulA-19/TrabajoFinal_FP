package Consola;

import java.awt.Color;
import java.util.ArrayList;

public class ConjuntoUnidadesDeMapa implements Mapeable, Batalla {
	private ArrayList<UnidadesDeMapa> conjunto = new ArrayList<UnidadesDeMapa>();

	public void setConjunto(ArrayList<UnidadesDeMapa> conjunto) {
		this.conjunto = conjunto;
	}

	public int getTamaño() {
		return conjunto.size();
	}

	@Override
	public ArrayList<UnidadesDeMapa> getUnidades() {
		return conjunto;
	}

	@Override
	public void addUnidad(UnidadesDeMapa unidad) {
		conjunto.add(unidad);

	}

	@Override
	public void deleteUnidad(UnidadesDeMapa unidad) {
		conjunto.remove(unidad);

	}

	public static UnidadesDeMapa[] batalla(UnidadesDeMapa unidad1, UnidadesDeMapa unidad2) {
		// TODO Auto-generated method stub
		return null;
	}
	// Batalla

	@Override
	public void actitudAtacar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actitudDefender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actitudNormal() {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getColor() {
		return getColor();

	}

}
