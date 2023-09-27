package AdvancedDatabases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.*;
 
public class BarChart extends JPanel{
 
  private double[] values;
  private String[] labels;
  private Color[] colors;
  private String title;
 
  public BarChart(double[] values, String[] labels, Color[] colors, String title) {
    this.labels = labels;
    this.values = values;
    this.colors = colors;
    this.title = title;
  }
 
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (values == null || values.length == 0) {
      return;
    }
 
    double minValue = 0;
    double maxValue = 0;
    for (int i = 0; i < values.length; i++) {
      if (minValue > values[i]) {
        minValue = values[i];
      }
      if (maxValue < values[i]) {
        maxValue = values[i];
      }
    }
 
    Dimension dim = getSize();
    int panelWidth = dim.width;
    int panelHeight = dim.height;
    int barWidth = panelWidth / values.length;
 
    Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
 
    Font labelFont = new Font("Book Antiqua", Font.PLAIN, 14);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
 
    int titleWidth = titleFontMetrics.stringWidth(title);
    int stringHeight = titleFontMetrics.getAscent();
    int stringWidth = (panelWidth - titleWidth) / 2;
    g.setFont(titleFont);
    g.drawString(title, stringWidth, stringHeight);
 
    int top = titleFontMetrics.getHeight();
    int bottom = labelFontMetrics.getHeight();
    if (maxValue == minValue) {
      return;
    }
    double scale = (panelHeight - top - bottom) / (maxValue - minValue);
    stringHeight = panelHeight - labelFontMetrics.getDescent();
    g.setFont(labelFont);
    for (int j = 0; j < values.length; j++) {
      int valueP = j * barWidth + 1;
      int valueQ = top;
      int height = (int) (values[j] * scale);
      if (values[j] >= 0) {
        valueQ += (int) ((maxValue - values[j]) * scale);
      } else {
        valueQ += (int) (maxValue * scale);
        height = -height;
      }
 
      g.setColor(colors[j]);
      g.fillRect(valueP, valueQ, barWidth - 2, height);
      g.setColor(Color.black);
      g.drawRect(valueP, valueQ, barWidth - 2, height);
 
      int labelWidth = labelFontMetrics.stringWidth(labels[j]);
      stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
      g.drawString(labels[j], stringWidth, stringHeight);
    }
  }
 
  public void main() {
 
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Bar Chart Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 450);
 
    String title = " Data Mart: Total Sales Bar Chart";
    double[] values1 = new double[]{values[0],values[1],values[2]};
    String[] labels = new String[]{"Dublin: €"+values[0] ,"Belfast: €"+ values[1] , "Cork: €" + values[2]};
    Color[] colors = new Color[]{
        Color.red,
        Color.orange,
        Color.blue,
        
    };
    BarChart bc = new BarChart(values1, labels, colors, title);
    frame.add(bc);
    frame.setVisible(true);
  }
}