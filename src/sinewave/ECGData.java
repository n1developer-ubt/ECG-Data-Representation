package sinewave;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.experimental.categories.Categories;

public class ECGData extends JPanel implements Scroll{
    private NumberAxis xAxis ;
    private NumberAxis yAxis;
    //private int Start = 0;
    //private int End = 1000;
    private ChartPanel chartPanel;
    private int xIRange = 0;
    private int xERange = 1000;
    private int yIRange = 0;
    private int yERange = 10;
    private JFreeChart chart;
    
    public ECGData()
    {
        this.setBackground(Color.WHITE);
    }
    public void AddData(List<DataSet> data)
    {
        XYDataset dataset = getDataSet(data, true);

        // Create chart
        chart = ChartFactory.createXYLineChart(
            "ECG Data",
            "X-Axis",
            "Y-Axis",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false);
        XYPlot xyPlot = (XYPlot) chart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLACK);
        xAxis = (NumberAxis) xyPlot.getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(100));
        xAxis.setRange(xIRange, xERange);
        xAxis.setVerticalTickLabels(true);
        xAxis.setLabel("Time");
        
        yAxis = (NumberAxis) xyPlot.getRangeAxis();
        yAxis.setTickUnit(new NumberTickUnit(1));
        yAxis.setRange(0, 10);
        yAxis.setVerticalTickLabels(true);
        yAxis.setLabel("Voltage");
//UpdateXRange();
        // Create Panel
        chartPanel = new ChartPanel(chart);
        
        XYPlot p = chart.getXYPlot();
        p.setDomainGridlinePaint(new Color(253, 147, 130));
        p.setRangeGridlinePaint(new Color(253, 147, 130));
        //p.setDomainGridlineStroke();
        this.add(chartPanel);
        Color myWhite = new Color(252, 227, 222);
        chart.getPlot().setBackgroundPaint( myWhite);
        //setContentPane(panel);
    }
    private void UpdateChart()
    {
        
    }
    private XYDataset getDataSet(List<DataSet> data, boolean j)
    {
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        XYSeries series = new XYSeries("25mm/s");
        int totalTime = 0;
        int Margin = 0;
        double total = 0;
        int y = 0;
        
//        int t = 0;
//        int from = 350;
//        int to = 405;
//        int min = 0;
//        int max = to-from;
//        for(double i = min; i < max; i += 0.2){
//            //++t;
//            
//            double devider = (max-min)*2;
//            
//            double sinx;
//            if(Math.cos((2*Math.PI/devider)*i) == 0)
//                sinx = Math.cos((2*Math.PI/devider)*i);
//            else
//                sinx = Math.sin((2*Math.PI/devider)*i);
//            
//            System.out.println(Math.abs(sinx));
//            
//            total=sinx*2.5;
//            
//            series.add(i+from, Math.abs(total));
//        }
        //System.out.println(t);
        int lastEnd =0;
        for (DataSet dataSet : data) {
            
//            if(lastEnd-dataSet.getStartTime() != 0)
//            {
//                series.add(lastEnd, 0+Margin);
//                series.add(dataSet.getStartTime(),0+Margin);
//            }

            if(dataSet.getVoltage() == 0)
            {
                //series.add(dataSet.getStartTime(), 0+Margin);
                //series.add(dataSet.getEndTime(),0+Margin);
                continue;
            }
            if(dataSet.getType() == 1)
            {
                series.add(dataSet.getStartTime(),Margin);
                series.add((dataSet.getEndTime()-dataSet.getStartTime())/2+dataSet.getStartTime(),dataSet.getVoltage()+Margin);
                series.add(dataSet.getEndTime(),Margin);
                totalTime+=dataSet.getEndTime();
            }
            else
            {
                
                int from = dataSet.getStartTime();
                int to = dataSet.getEndTime();
                int min = 0;
                int max = to-from;
                
                for(double i = min; i < max; i += 0.2){
                    //++t;

                    double devider = (max-min)*2;

                    double sinx;
                    if(Math.cos((2*Math.PI/devider)*i) == 0)
                        sinx = Math.cos((2*Math.PI/devider)*i);
                    else
                        sinx = Math.sin((2*Math.PI/devider)*i);

                    //System.out.println(Math.abs(sinx));

                    total=sinx*dataSet.getVoltage();

                    series.add(i+from, total+Margin);
                }
                
//                for(double i = dataSet.getStartTime(); i < dataSet.getEndTime(); i += 0.2){
//                    double sinx = Math.sin((2*Math.PI/((dataSet.getEndTime()-dataSet.getStartTime())))*i);
//                    series.add(i, sinx*dataSet.getVoltage()+Margin);
//                }
            }
            lastEnd = dataSet.getEndTime();
        }
        
        dataset.addSeries(series);

        return dataset;
    }
    
    private void UpdateXRange()
    {
        xAxis.setRange(xIRange, xERange);
    }
    
    private void UpdateYRange()
    {
        yAxis.setRange(yIRange,yERange);
    }
    
    @Override
    public void Increase() {
        int temp = xERange;
        xERange += xERange-xIRange;
        xIRange = temp;
        //System.out.println(xERange+"\n"+xIRange);
        UpdateXRange();
        chartPanel.getChart().fireChartChanged();
    }

    @Override
    public void Decrease()  {
        if(xIRange - (xERange-xIRange) < 0)
            return;
        
        int temp = xIRange;
        xIRange = xIRange - (xERange-xIRange);
        xERange = temp;
        UpdateXRange();
        //System.out.println(xERange+"\n"+xIRange);
        UpdateXRange();
        chartPanel.getChart().fireChartChanged();
    }

    @Override
    public void ChangeXRange(int x, int y) {
        if(x > y)
            return;
        
        xIRange = x;
        xERange = y;
        
        UpdateXRange();
    }

    @Override
    public void ChangeYRange(int x, int y) {
        if(x > y)
            return;
        
        yIRange = x;
        yERange = y;
        
        UpdateYRange();
    }

    @Override
    public void Up() {
        int temp = yERange;
        yERange += yERange-yIRange;
        yIRange = temp;
        UpdateYRange();
        chartPanel.getChart().fireChartChanged();
    }

    @Override
    public void Down() {
        int temp = yIRange;
        yIRange = yIRange - (yERange-yIRange);
        yERange = temp;
        
        UpdateYRange();
        chartPanel.getChart().fireChartChanged();
    }
 }
