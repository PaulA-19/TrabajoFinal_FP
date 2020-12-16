package Consola;

import java.util.ArrayList;

public interface Mapeable {

	ArrayList<UnidadesDeMapa> getUnidades();

	void addUnidad(UnidadesDeMapa unidad);

	void deleteUnidad(UnidadesDeMapa unidad);

}
