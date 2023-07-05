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

public class ProcedureGRASP {

    public static void main(String[] args) {

        //Grafos:
        Grafo g1 = new Grafo(100);
        Grafo g2 = new Grafo(200);
        Grafo g3 = new Grafo(250);

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
        grasp.ejecutarGrasp("500it",500, 5, 100, 0.0f);
        grasp.ejecutarGrasp("1000it",1000, 5, 100, 0.0f);
        grasp.ejecutarGrasp("2000it",2000, 5, 100, 0.0f);
        grasp.ejecutarGrasp("3000it",3000, 5, 100, 0.0f);
        grasp.ejecutarGrasp("4000it",4000, 5, 100, 0.0f);
        grasp.ejecutarGrasp("5000it",5000, 5, 100, 0.0f);

        grasp.imprimirGrasp();

    }
}