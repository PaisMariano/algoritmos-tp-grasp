package estrategias;

import modelos.Grafo;
import modelos.Vertice;
import java.util.ArrayList;
import java.util.List;

public class BusquedaLocal {

    public List<Vertice> mejorarCircuito(Grafo g, List<Vertice> circuito) {
        List<Vertice> lista = circuito;

        //swap y guardar
        for (int i = 0; i < lista.size(); i++) {
            List<Vertice> listaSwapeada = new ArrayList<>();
            listaSwapeada.addAll(lista.subList(0, i+1));
            if (i+3 == lista.size()) {
                listaSwapeada.addAll(lista.subList(i, i+3));
                i = i+3;
            } else {
                listaSwapeada.add(lista.get(i + 2));
                listaSwapeada.add(lista.get(i + 1));
                listaSwapeada.addAll(lista.subList(i + 3, lista.size()));
            }

            int costoCircuito = getCostoCircuito(g, lista);
            int costoCircuitoSwap = getCostoCircuito(g, listaSwapeada);

            if (costoCircuitoSwap < costoCircuito) {
                lista = listaSwapeada;
            }
        }

        return lista;
    }

    public int getCostoCircuito(Grafo g, List<Vertice> circuito) {
        int costo = 0;

        for (int i = 1; i < circuito.size(); i++) {
            costo = costo + g.getPosicionMatriz(circuito.get(i-1).getId(), i);
        }
        //agregar el costo del primer elemento nuevamente
        //costo = costo + g.getPosicionMatriz(circuito.get(circuito.size()-1).getId(), 1);

        return costo;
    }
}
