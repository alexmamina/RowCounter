import javax.swing.*;
import java.awt.*;

public class Counter extends JFrame {

    //TODO limits/loopedcharts like sock



    private int counter = 1;

    private Counter() {
        setTitle("Row Counter");
        setSize(1000, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10,6));
        addMenu();
        getContentPane().setBackground(new Color(174,237,255));
        Chart gen = new Chart("General counter");
        for (int i = 0; i < 6; i++) {
            this.add(gen.chart[i]);
        }
        addInvisibleCharts(10-counter);
        setCheckBoxes();
    }

    private void addInvisibleCharts(int n) {
        for (int i = 0; i < n; i++) {
            Chart x = new Chart("invisible "+i);
            for (int j = 0; j < 6; j++) {
                add(x.chart[j]);
                x.chart[j].setVisible(false);
            }
        }
    }
    private void setCheckBoxes() {
        for (JComponent[] c : Chart.charts.subList(1, Chart.charts.size())) {
            ((JCheckBox) c[0]).addActionListener(l -> {
                if (((JCheckBox) c[0]).isSelected()) {
                    for (int i = 0; i < 6; i++) {
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
            String name = JOptionPane.showInputDialog(
                    "Enter chart name:");
            if (name != null && !name.equals("")) {
                for (int i = 1; i < 6; i++) {
                    if (i == 1) ((JLabel) Chart.charts.get(counter)[i]).setText(name);
                    Chart.charts.get(counter)[i].setVisible(true);
                }
                counter++;
                revalidate();
                repaint();
            }
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
