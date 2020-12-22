package Consola;

public interface Batalla {

	int PORCION_ATAQUE = 3;
	int FALLO_ATAQUE_SOL = 3;
	int SUMA_VIDA_BENEFICIO = 2;
	int INTERCAMBIO_VIDA_EN_ATAQUE = 2;
	int INTERCAMBIO_ATAQUE_EN_VIDA = 2;

	void actitudAtacar();

	void actitudDefender();

	void actitudNormal();

	void atacarOponente(UnidadesDeMapa oponente);

	void quitarVida(UnidadesDeMapa oponenteAtaca);
}
