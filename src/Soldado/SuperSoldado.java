package Soldado;

public interface SuperSoldado {
	int OBJETO_LANZAR = 50; // por ahora final, ya que no se salza
	int AUMENTAR_VIDA_EVOLUCIONADA = 3;
	int AUMENTAR_ATAQUE_EVOLUCIONADA = 3;
	int AUMENTAR_DEFENSA_EVOLUCIONADA = 3;

	// añadir mas cosas
	void intentaEvolucionar();

	void lanzar();

	int getNumObjetoLanzar();

}
