import estrategias.BusquedaLocal;
import estrategias.Estrategia;
import estrategias.Greedy;
import estrategias.GreedyRandom;
import modelos.Grafo;
import visual.Printer;

import java.util.List;

public class Main {

    public static void main(String[] args){
        Grafo g = new Grafo(20000);
        Estrategia estrategia = new Greedy();
        Estrategia estrategia2 = new GreedyRandom();
        BusquedaLocal busquedaLocal = new BusquedaLocal();

        List circuito = estrategia2.calcular(g);
        List circuitoMejorado = busquedaLocal.mejorarCircuito(g, circuito);

        //Printer.imprimir(g);
        Printer.imprimir(circuito);
        System.out.println("Costo circuito 1: "+ busquedaLocal.getCostoCircuito(g, circuito));
        Printer.imprimir(circuitoMejorado);
        System.out.println("Costo circuito 2: "+ busquedaLocal.getCostoCircuito(g, circuitoMejorado));
    }
}
