package estrategias;

import modelos.Grafo;
import modelos.Vertice;
import java.util.ArrayList;
import java.util.List;

public class BusquedaLocal {

    //busqueda vecindad
    private List<Vertice> busquedaVecindadMejorYCorto(Grafo g, List<Vertice> circuito) {
        List<Vertice> lista = circuito;
        boolean mejoro = false;
        int i = 0;

        while (!mejoro && i < lista.size()) {
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

            float costoCircuito = getCostoCircuito(g, lista);
            float costoCircuitoSwap = getCostoCircuito(g, listaSwapeada);

            if (costoCircuitoSwap < costoCircuito) {
                lista = listaSwapeada;
                mejoro = true;
            }

            i++;
        }
        return lista;
    }

    private List<Vertice> busquedaVecindad(Grafo g, List<Vertice> circuito) {
        List<Vertice> lista = circuito;

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

            float costoCircuito = getCostoCircuito(g, lista);
            float costoCircuitoSwap = getCostoCircuito(g, listaSwapeada);

            if (costoCircuitoSwap < costoCircuito) {
                lista = listaSwapeada;
            }

        }
        return lista;
    }

    //busqueda Local
    public List<Vertice> busquedaLocal(int cantVeces, float porcentajeMejora, Grafo g, List<Vertice> circuito) {
        int vecesLocal = 0;
        float porcentajeLocal = 100;

        while (cantVeces > vecesLocal && porcentajeLocal > porcentajeMejora) {
            List<Vertice> vertices = busquedaVecindad(g, circuito);

            float costoCircuito = getCostoCircuito(g, circuito);
            float costoMejorado = getCostoCircuito(g, vertices);

            if (costoMejorado < costoCircuito) {
                circuito = vertices;
                porcentajeLocal = (costoCircuito - costoMejorado) / costoCircuito * 100;
            }
            vecesLocal++;
        }
        return circuito;
    }

    public float getCostoCircuito(Grafo g, List<Vertice> circuito) {
        int costo = 0;

        for (int i = 1; i < circuito.size(); i++) {
            costo = costo + g.getPosicionMatriz(circuito.get(i-1).getId(), circuito.get(i).getId());
        }

        return costo;
    }
}
