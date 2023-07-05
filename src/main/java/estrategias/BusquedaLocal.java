package estrategias;

import modelos.Grafo;
import modelos.Vertice;
import visual.Printer;

import java.util.ArrayList;
import java.util.List;

public class BusquedaLocal {
    Printer printer = new Printer();

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
    //o(n^2)
    private List<Vertice> busquedaVecindad(Grafo g, List<Vertice> circuito) {
        List<Vertice> lista = circuito;

        for (int i = 0; i < lista.size(); i++) { //o(n)
            List<Vertice> listaSwapeada = new ArrayList<>();
            listaSwapeada.addAll(lista.subList(0, i+1)); //o(1)
            if (i+3 == lista.size()) { //o(1)
                listaSwapeada.addAll(lista.subList(i, i+3)); //o(1)
                i = i+3;
            } else {
                listaSwapeada.add(lista.get(i + 2));
                listaSwapeada.add(lista.get(i + 1));
                listaSwapeada.addAll(lista.subList(i + 3, lista.size())); //o(1)
            }

            float costoCircuito = getCostoCircuito(g, lista); // o(n)
            float costoCircuitoSwap = getCostoCircuito(g, listaSwapeada);

            if (costoCircuitoSwap < costoCircuito) {
                lista = listaSwapeada;
            }

        }
        return lista;
    }

    //busqueda Local o(cantVeces * n^2)
    public List<Vertice> busquedaLocal(int cantVeces, float porcentajeMejora, Grafo g, List<Vertice> circuito, String tag) {
        int vecesLocal = 0;
        float porcentajeLocal = 100;
        printer.createSerie(tag);

        while (cantVeces > vecesLocal && porcentajeLocal > porcentajeMejora) { //o(cantVeces)
            List<Vertice> vertices = busquedaVecindad(g, circuito); //o(n^2)

            float costoCircuito = getCostoCircuito(g, circuito); //o(n)
            float costoMejorado = getCostoCircuito(g, vertices); //o(n)

            if (costoMejorado < costoCircuito) {
                circuito = vertices;
                porcentajeLocal = (costoCircuito - costoMejorado) / costoCircuito * 100;

            }
            printer.setDatoGrafico(vecesLocal, costoMejorado, tag);
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

    public void imprimirBL() {
        printer.graficar();
    }
}
