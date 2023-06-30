package modelos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Grafo {
    int[][] matriz;
    List<Vertice> v;

    public Grafo(int dimension) {
        v = new ArrayList();
        generarMatrizHardcodeDistanciaRandom(dimension);
    }

    private void generarMatrizHardcodeDistanciaRandom(int dimension){
        int[][] matrizTemp = new int[dimension][dimension];
        Random rand = new Random();
        int min = 1;
        int max = 600;

        for (int i = 1; i < dimension; i++) {
            v.add(new Vertice(i));
            for (int j = 1; j < dimension; j++) {
                int randomNum = rand.nextInt((max - min) + 1) + min;
                matrizTemp[i][j] = randomNum;
                if (i == j) {
                    matrizTemp[i][j] = 0;
                }
            }
        }
        matriz = matrizTemp;
    }

    public int[][] getMatrizAdyacencias() {
        return matriz;
    }

    public int getPosicionMatriz(int posX, int posY) {
        return matriz[posX][posY];
    }

    public List getVertices() {
        return v;
    }

    public Vertice getVertice(Integer index) {
        Vertice verTemp = null;
        for (Vertice vertice : v) {
            if (vertice.getId().equals(index)) {
                verTemp = vertice;
                break;
            }
        }
        return verTemp;
    }
}
