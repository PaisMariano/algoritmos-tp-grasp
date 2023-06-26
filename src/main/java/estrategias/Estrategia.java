package estrategias;

import modelos.Grafo;
import modelos.Vertice;

import java.util.List;

public abstract class Estrategia {
    public abstract List<Vertice> calcular(Grafo g);
}
