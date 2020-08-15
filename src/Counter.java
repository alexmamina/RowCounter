import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Counter extends JFrame {

    //TODO colours
    //TODO link counters

    public int counter = 1;

    public Counter() {
        setTitle("Row Counter");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10,5));
        addMenu();
        Chart gen = new Chart("General counter");
        for (int i = 0; i < 5; i++) {
            this.add(gen.chart[i]);
        }
        addInvisibleCharts(10-counter);
        setCheckBoxes();
    }

    private void addInvisibleCharts(int n) {
        for (int i = 0; i < n; i++) {
            Chart x = new Chart("invisible "+i);
            for (int j = 0; j < 5; j++) {
                add(x.chart[j]);
                x.chart[j].setVisible(false);
            }
        }
    }
    private void setCheckBoxes() {
        for (JComponent[] c : Chart.charts.subList(1, Chart.charts.size())) {
            ((JCheckBox) c[0]).addActionListener(l -> {
                if (((JCheckBox) c[0]).isSelected()) {
                    for (int i = 0; i < 5; i++) {
                        remove(c[i]);
                    }
                    Chart.charts.remove(c);
                    counter--;
                    revalidate();
                    repaint();
                    addInvisibleCharts(1);
                }

            });
        }
    }

    private void addMenu() {
        JMenuBar menu = new JMenuBar();
        JButton newchart = new JButton("Add");
        JButton remove = new JButton("Delete chart");

        newchart.addActionListener(e -> {
            for (int i = 1; i < 5; i++) {
                if (i == 1) ((JLabel) Chart.charts.get(counter)[i]).setText(JOptionPane.showInputDialog(
                        "Enter chart name:"));
                Chart.charts.get(counter)[i].setVisible(true);
            }
            counter++;
            revalidate();
            repaint();
        });

        remove.addActionListener(e -> {
            if (remove.getText().equals("Delete chart")) {
                for (JComponent[] c : Chart.charts.subList(1, Chart.charts.size())) {
                   if (c[1].isVisible()) (c[0]).setVisible(true);
                   remove.setText("Done");
                }
            } else {
                for (JComponent[] c : Chart.charts.subList(1, Chart.charts.size())) {
                    (c[0]).setVisible(false);
                    remove.setText("Delete chart");
                }
            }
        });
        menu.add(newchart);
        menu.add(remove);
        setJMenuBar(menu);
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        c.setVisible(true);
    }
}
