package modelos;

public class Arista implements Comparable<Arista> {
    Vertice origen;
    Vertice destino;
    int costo;

    public Arista(Vertice origen, Vertice fin, int costo) {
        this.origen = origen;
        this.destino = fin;
        this.costo = costo;
    }

    public Vertice getOrigen() {
        return origen;
    }

    public void setOrigen(Vertice origen) {
        this.origen = origen;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public int compareTo(Arista arista) {
        int cmp;
        if (costo < arista.getCosto()) {
            cmp = -1;
        } else {
            if (costo == arista.getCosto()) {
                cmp = 0;
            } else {
                cmp = 1;
            }
        }
        return cmp;
    }
}
