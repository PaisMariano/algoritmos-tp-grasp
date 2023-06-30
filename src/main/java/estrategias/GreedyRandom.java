package estrategias;

import modelos.Arista;
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
        Random rand = new Random();

        Vertice verticeActual = (Vertice) g.getVertices()
                .get(rand.nextInt((g.getVertices().size()-1 - 0) + 1));

        visitados[verticeActual.getId()] = true;
        circuito.add(verticeActual);

        for (int i = 1; i < largoMat; i++) {

            List<Vertice> verticesRandom = getVerticesRandom(visitados, g, verticeActual);
            if(verticesRandom.stream().findAny().isPresent()) {
                int x = verticeActual.getId();
                verticeActual = verticesRandom.get(rand.nextInt((verticesRandom.size()-1 - 0) + 1) + 0);

                visitados[verticeActual.getId()] = true;
                circuito.add(verticeActual);
            }
        }

        return circuito;
    }

    private List<Vertice> getVerticesRandom(boolean[] visitados, Grafo g) {
        List<Vertice> vertices = g.getVertices();

        List<Vertice> verticesNoVisitados = vertices.stream()
                .filter(v->!visitados[v.getId()])
                .collect(Collectors.toList());

        Collections.shuffle(verticesNoVisitados);

        return verticesNoVisitados.stream()
                .limit(vertices.size() * 35 / 100)
                .collect(Collectors.toList());
    }

    private List<Vertice> getVerticesRandom(boolean[] visitados, Grafo g, Vertice verticeOrigen) {
        List<Vertice> vertices = g.getVertices();
        List<Arista> aristas = new ArrayList<>();

        List<Vertice> verticesNoVisitados = vertices.stream()
                .filter(v->!visitados[v.getId()])
                .collect(Collectors.toList());

        for (Vertice v : verticesNoVisitados) {
            int costoVertice = g.getPosicionMatriz(verticeOrigen.getId(), v.getId());
            Arista aristaTemp = new Arista(verticeOrigen, v, costoVertice);
            aristas.add(aristaTemp);
        }

        Collections.sort(aristas);

        //pasar el 5 % como parametros o 3
        return aristas.stream()
                .limit(Math.max(3,vertices.size() * 5 / 100))
                .map(arista -> arista.getDestino())
                .collect(Collectors.toList());
    }
}