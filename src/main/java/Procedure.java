import estrategias.BusquedaLocal;
import estrategias.Estrategia;
import estrategias.GreedyRandom;
import modelos.Grafo;
import modelos.Vertice;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import visual.Printer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Procedure {

    public static void main(String[] args) {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();

        //Parametros:
        Grafo g = new Grafo(10);
        Estrategia greedy = new GreedyRandom();
        BusquedaLocal busquedaLocal = new BusquedaLocal();
        int vecesMax = 7;
        int recorridoActual = 0;
        float mejorCostoCircuito = 0;
        List<Vertice> mejorCircuito = new ArrayList();

        //Procedure
        List circuito = greedy.calcular(g);
        List circuitoMejorado = circuito;

        while (recorridoActual < vecesMax){
            circuitoMejorado = busquedaLocal.mejorarCircuitoNVeces(1, 0.0f, g, circuitoMejorado);
            float costoCircuitoActual = busquedaLocal.getCostoCircuito(g, circuitoMejorado);

            System.out.println(costoCircuitoActual);
            datos.addValue(costoCircuitoActual, "Grafica 1", String.valueOf(recorridoActual));

            if (costoCircuitoActual < mejorCostoCircuito) {
                mejorCircuito = circuitoMejorado;
                mejorCostoCircuito = costoCircuitoActual;
            }

            recorridoActual++;

        }
        JFreeChart grafico = ChartFactory.createLineChart3D(
                "Gráfico Viajero",
                "Eje X",
                "Eje Y",
                datos,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        ChartPanel cPanel = new ChartPanel(grafico);
        JFrame frame = new JFrame("Gráfico");
        frame.getContentPane().add(cPanel);
        frame.pack();
        frame.setVisible(true);
        Printer.imprimir(mejorCircuito);
        System.out.println("Costo circuito mejorado luego de "+vecesMax+" recorridos: "+mejorCostoCircuito);
    }
}
