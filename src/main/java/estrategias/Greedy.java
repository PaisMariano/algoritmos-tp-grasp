package estrategias;

import modelos.Grafo;
import modelos.Vertice;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Greedy extends Estrategia{
    @Override
    public List<Vertice> calcular(Grafo g) {
        Integer largoMat = g.getMatrizAdyacencias().length;
        boolean[] visitados = new boolean[largoMat];
        List<Vertice> circuito = new ArrayList<>();
        Random rand = new Random();

        Vertice verticeOriginal = (Vertice) g.getVertices().get(rand.nextInt((g.getVertices().size()-1 - 0) + 1));
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
            }
        }

        return circuito;
    }

}
