package visual;

import modelos.Grafo;
import modelos.Vertice;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Printer {
    DefaultCategoryDataset datos = new DefaultCategoryDataset();

    public void setDatoGrafico(float valy, String tag, String posx) {
        datos.addValue(valy, tag, posx);
    }
    public void graficar(){
        JFreeChart grafico = ChartFactory.createLineChart(
                "",
                "# Iteraciones",
                "Puntaje",
                datos,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);


        final CategoryPlot plot = grafico.getCategoryPlot();
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

    private void configurePlot(CategoryPlot plot) {
        plot.setBackgroundPaint(Color.BLACK);
        plot.setOutlinePaint(null);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        configureRenderer((LineAndShapeRenderer) plot.getRenderer());

    }

    protected void configureRenderer(LineAndShapeRenderer renderer) {
        renderer.setBaseStroke(new BasicStroke(3));

        for (int i = 0; i < datos.getRowCount(); i++){
            renderer.setSeriesStroke(i, new BasicStroke(5.0f));
        }
    }

}
