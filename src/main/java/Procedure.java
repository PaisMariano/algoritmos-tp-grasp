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
        Grafo g1 = new Grafo(50);
        Grafo g2 = new Grafo(50);
        Grafo g3 = new Grafo(300);

        //Estrategias y BL
        Estrategia greedyRandom = new GreedyRandom();
        Estrategia greedy = new Greedy();
        BusquedaLocal busquedaLocal = new BusquedaLocal();

        //Grasp prueba1 con diversos parametros
        Grasp grasp = new Grasp(g1,greedy,busquedaLocal);
        //grasp.ejecutarGrasp("gr10-gree-vc10-bl5",500, 5, 5, 0.0f);
        //grasp.ejecutarGrasp("gr10-gree-vc10-bl10",500, 5, 10, 0.0f);
        //grasp.ejecutarGrasp("gr10-gree-vc10-bl20",500, 5, 20, 0.0f);

        grasp.setGreedy(greedyRandom);
        grasp.ejecutarGrasp("gr10-greeR-vc10-bl5",500, 5, 100, 0.0f);
        grasp.ejecutarGrasp("gr10-greeR-vc10-bl10",500, 5, 200, 0.0f);
        grasp.ejecutarGrasp("gr10-greeR-vc10-bl20",500, 5, 300, 0.0f);

        grasp.ejecutarGrasp("gr10-greeR-vc10-bl100-porc0.1",500, 5, 100, 0.1f);
        grasp.ejecutarGrasp("gr10-greeR-vc10-bl100-porc0.05",500, 5, 100, 0.05f);
        grasp.ejecutarGrasp("gr10-greeR-vc10-bl100-porc0.02",500, 5, 100, 0.02f);

        grasp.imprimirGrasp();

    }
}