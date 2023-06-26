package estrategias;

import modelos.Grafo;
import modelos.Vertice;
import java.util.ArrayList;
import java.util.List;

public class Greedy extends Estrategia{
    @Override
    public List<Vertice> calcular(Grafo g) {
        Integer largoMat = g.getMatrizAdyacencias().length;
        boolean[] visitados = new boolean[largoMat];
        List<Vertice> circuito = new ArrayList<>();

        Vertice verticeOriginal = (Vertice) g.getVertices().stream().findAny().get();
        Vertice verticeActual = verticeOriginal;

        visitados[verticeActual.getId()] = true;
        circuito.add(verticeActual);

        for (int i = 1; i < largoMat; i++) {
            Vertice verticeMasCercano = null;
            Integer distanciaMinima = Integer.MAX_VALUE;

            for (int j = 1; j < largoMat; j++)  {
                if (!visitados[j] && j!=i && g.getPosicionMatriz(verticeActual.getId(),j) < distanciaMinima) {
                    verticeMasCercano = g.getVertice(j);
                    distanciaMinima = g.getPosicionMatriz(verticeActual.getId(),j);
                }
            }

            if (verticeMasCercano != null) { //caso final.
                verticeActual = verticeMasCercano;
                visitados[verticeActual.getId()] = true;
                circuito.add(verticeActual);
            } else {
                circuito.add(verticeOriginal);
            }
        }

        return circuito;
    }

}
