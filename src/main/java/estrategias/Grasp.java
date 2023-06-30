package estrategias;

import modelos.Grafo;
import modelos.Vertice;
import visual.Printer;

import java.util.ArrayList;
import java.util.List;

public class Grasp {
    private final Grafo g;
    private final BusquedaLocal busqLoc;
    private final Printer printer;
    private Estrategia greedy;

    public Grasp(Grafo g, Estrategia greedy, BusquedaLocal bl) {
        this.g = g;
        this.greedy = greedy;
        this.busqLoc = bl;
        this.printer = new Printer();

        //printer.imprimir(g);
    }
    /*Se definen multiples parametros para pruebas:
        Tag de la curva del grafico. (tag)
        Cantidad de veces a ejecutar Grasp: (vecesGrasp)
        % de items Random tomados en GreedyRandom. (porcentajeItRandom)
        Cantidad de veces a ejecutar la busqueda local. (vecesBL)
        Porcentaje a superar por cada iteracion para seguir ejecutando. (porcMejora)
     */

    public void ejecutarGrasp(String tag, int vecesGrasp, int porcentajeItRandom, int vecesBL, float porcMejora){
        float mejorCostoCircuito = Integer.MAX_VALUE;
        List<Vertice> mejorCircuito = new ArrayList();

        for (int i = 0; i < vecesGrasp; i++) {
            List circuito = greedy.calcular(this.g);

            List circuitoMejorado = busqLoc.busquedaLocal(vecesBL, porcMejora, this.g, circuito);
            float costoCircuitoActual = busqLoc.getCostoCircuito(this.g, circuitoMejorado);

            if (costoCircuitoActual < mejorCostoCircuito) {
                mejorCircuito = circuitoMejorado;
                mejorCostoCircuito = costoCircuitoActual;
            }
            printer.setDatoGrafico(mejorCostoCircuito, tag, String.valueOf(i));
            //printer.imprimir(mejorCircuito);
        }
        System.out.print(tag + " costo: " + mejorCostoCircuito);
        printer.imprimir(mejorCircuito);
    }

    public void setGreedy(Estrategia greedy) {
        this.greedy = greedy;
    }

    public void imprimirGrasp(){
        printer.graficar();
    }
}
