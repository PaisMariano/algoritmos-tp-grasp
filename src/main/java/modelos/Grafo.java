package modelos;

import java.util.HashSet;
import java.util.Random;

public class Grafo {
    int[][] matriz;
    HashSet<Vertice> v;

    public Grafo(int dimension) {
        v = new HashSet<>();
        generarMatrizHardcodeDistanciaRandom(dimension);
    }

    private void generarMatrizHardcode(int dimension) {
        int[][] matrizTemp = new int[dimension][dimension];

        for (int i = 1; i < dimension; i++) {
            v.add(new Vertice(i));
            for (int j = 1; j < dimension; j++) {
                matrizTemp[i][j] = 5;
                if (i == j) {
                    matrizTemp[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        matriz = matrizTemp;
    }

    private void generarMatrizHardcodeDistanciaRandom(int dimension){
        int[][] matrizTemp = new int[dimension][dimension];
        Random rand = new Random();
        int min = 1;
        int max = 9;

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

    public HashSet getVertices() {
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
