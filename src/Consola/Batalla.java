package Consola;

public interface Batalla {

	int PORCION_ATAQUE = 3;

	void actitudAtacar();

	void actitudDefender();

	void actitudNormal();

	void atacarOponente(UnidadesDeMapa oponente);

	void quitarVida(UnidadesDeMapa oponenteAtaca);
}
