package visual;

import modelos.Grafo;
import modelos.Vertice;

import java.util.List;

public class Printer {
    public static void imprimir(List<Vertice> circuito) {
        System.out.println("Circuito: ");
        for (Vertice v : circuito) {
            System.out.print(" [" + v.getId() + "] ");
        }
        System.out.println("");
    }
    public static void imprimir(Grafo g){
        System.out.println("Matriz de adyacencias:");
        int[][] mat = g.getMatrizAdyacencias();
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
