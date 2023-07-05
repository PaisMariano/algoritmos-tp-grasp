import estrategias.*;
import modelos.Grafo;

public class ProcedureBL {

    public static void main(String[] args) {
        //Grafos:
        Grafo g1 = new Grafo(100);
        Grafo g2 = new Grafo(200);
        Grafo g3 = new Grafo(250);

        //Estrategias y BL
        Estrategia greedyRandom = new GreedyRandom();
        Estrategia greedy = new Greedy();
        BusquedaLocal busquedaLocal = new BusquedaLocal();

        busquedaLocal.busquedaLocal(5,0.0f, g1, greedyRandom.calcular(g1), "5it");
        busquedaLocal.busquedaLocal(10,0.0f, g1, greedyRandom.calcular(g1), "10it");
        busquedaLocal.busquedaLocal(15,0.0f, g1, greedyRandom.calcular(g1), "15it");
        busquedaLocal.busquedaLocal(20,0.0f, g1, greedyRandom.calcular(g1), "20it");
        busquedaLocal.busquedaLocal(25,0.0f, g1, greedyRandom.calcular(g1), "25it");
        busquedaLocal.busquedaLocal(30,0.0f, g1, greedyRandom.calcular(g1), "30it");

        busquedaLocal.imprimirBL();

    }
}
