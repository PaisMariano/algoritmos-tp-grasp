package visual;

import modelos.Grafo;
import modelos.Vertice;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Printer {
    XYSeriesCollection datos = new XYSeriesCollection();

    public void createSerie(String tag){
        XYSeries series1 = new XYSeries(tag);

        datos.addSeries(series1);
    }
    public void setDatoGrafico(float x, float y, String tag) {

        XYSeries series1 = datos.getSeries(tag);
        series1.add(x,y);
    }

    public void graficar(){
        JFreeChart grafico = ChartFactory.createXYLineChart(
                "",
                "# Iteraciones",
                "Puntaje",
                datos,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);

        XYPlot plot = grafico.getXYPlot();
        configurePlot(plot);
        ChartPanel cPanel = new ChartPanel(grafico);

        JFrame frame = new JFrame("Gráfico");
        frame.getContentPane().add(cPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void imprimir(List<Vertice> circuito) {
        System.out.print("Circuito: [");
        for (Vertice v : circuito) {
            System.out.print(v.getId() + ",");
        }
        System.out.println("]");
    }
    public void imprimir(Grafo g){
        System.out.println("Matriz de adyacencias:");
        int[][] mat = g.getMatrizAdyacencias();
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
    }
    private void configurePlot(XYPlot plot) {
        plot.setBackgroundPaint(Color.BLACK);
        plot.setOutlinePaint(null);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        NumberAxis xAxis = new NumberAxis();

        xAxis.setTickUnit(new NumberTickUnit(1));

        plot.setDomainAxis(xAxis);
        configureRenderer(plot.getRenderer());
    }
    protected void configureRenderer(
            XYItemRenderer renderer) {
        renderer.setBaseStroke(new BasicStroke(3));

        for (int i = 0; i < datos.getSeriesCount(); i++){
            renderer.setSeriesStroke(i, new BasicStroke(5.0f));
        }
    }

}
