import estrategias.BusquedaLocal;
import estrategias.Estrategia;
import estrategias.Greedy;
import estrategias.GreedyRandom;
import modelos.Grafo;
import visual.Printer;

import java.util.List;

public class Main {

    public static void main(String[] args){
        Grafo g = new Grafo(100);
        Estrategia estrategia = new Greedy();
        Estrategia estrategia2 = new GreedyRandom();
        BusquedaLocal busquedaLocal = new BusquedaLocal();

        List circuito = estrategia2.calcular(g);
        //Printer.imprimir(g);
        Printer.imprimir(circuito);
        System.out.println("Costo circuito greedy: "+ busquedaLocal.getCostoCircuito(g, circuito));

        List circuitoMejorado = busquedaLocal.mejorarCircuitoNVeces(50, 0.05f, g, circuito);
        Printer.imprimir(circuitoMejorado);
        System.out.println("Costo circuito mejorado: "+ busquedaLocal.getCostoCircuito(g, circuitoMejorado));
    }
}
