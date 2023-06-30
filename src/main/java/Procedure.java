import estrategias.*;
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

        //Grafos:
        Grafo g1 = new Grafo(10);
        Grafo g2 = new Grafo(50);
        Grafo g3 = new Grafo(300);

        //Estrategias y BL
        Estrategia greedyRandom = new GreedyRandom();
        Estrategia greedy = new Greedy();
        BusquedaLocal busquedaLocal = new BusquedaLocal();

        //Grasp prueba1 con diversos parametros
        Grasp grasp = new Grasp(g1,greedy,busquedaLocal);
        grasp.ejecutarGrasp("grasp0",10, 5, 5, 0.0f);
        grasp.ejecutarGrasp("grasp1",10, 5, 10, 0.0f);
        grasp.ejecutarGrasp("grasp2",10, 5, 20, 0.0f);

        grasp.setGreedy(greedyRandom);
        grasp.ejecutarGrasp("grasp3",10, 5, 5, 0.0f);
        grasp.ejecutarGrasp("grasp4",10, 5, 10, 0.0f);
        grasp.ejecutarGrasp("grasp5",10, 5, 20, 0.0f);

        grasp.ejecutarGrasp("grasp6",10, 5, 100, 0.1f);
        grasp.ejecutarGrasp("grasp7",10, 5, 100, 0.05f);
        grasp.ejecutarGrasp("grasp8",10, 5, 100, 0.02f);

        grasp.imprimirGrasp();

    }
}