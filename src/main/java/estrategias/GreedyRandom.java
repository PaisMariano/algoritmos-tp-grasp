package estrategias;

import modelos.Grafo;
import modelos.Vertice;
import java.util.*;
import java.util.stream.Collectors;

public class GreedyRandom extends Estrategia{
    @Override
    public List<Vertice> calcular(Grafo g) {
        Integer largoMat = g.getMatrizAdyacencias().length;
        boolean[] visitados = new boolean[largoMat];
        List<Vertice> circuito = new ArrayList<>();

        Vertice verticeActual = (Vertice) g.getVertices()
                .stream()
                .findAny()
                .get();

        visitados[verticeActual.getId()] = true;
        circuito.add(verticeActual);

        for (int i = 1; i < largoMat; i++) {
            Vertice verticeMasCercano = null;
            Integer distanciaMinima = Integer.MAX_VALUE;
            List<Vertice> verticesRandom = getVerticesRandom(visitados, g);

            for(Vertice v : verticesRandom) {
                if (!visitados[v.getId()] && v.getId()!=i && g.getPosicionMatriz(verticeActual.getId(),v.getId()) < distanciaMinima) {
                    verticeMasCercano = g.getVertice(v.getId());
                    distanciaMinima = g.getPosicionMatriz(verticeActual.getId(),v.getId());
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

    private List<Vertice> getVerticesRandom(boolean[] visitados, Grafo g) {
        //obtengo 5 elementos al azar de la lista de vertices, validando que los mismos no esten visitados.
        Set<Vertice> vertices = g.getVertices();
        ArrayList<Vertice> verticesRet = new ArrayList<>();

        List<Vertice> verticesNoVisitados = vertices.stream()
                .filter(v->!visitados[v.getId()])
                .collect(Collectors.toList());
        Collections.shuffle(verticesNoVisitados);

        return verticesNoVisitados.stream()
                .limit(vertices.size() * 35 / 100)
                .collect(Collectors.toList());
    }

}