import javax.swing.*;
import java.awt.*;

public class Counter extends JFrame {

    //TODO decrease repeats



    private int counter = 1;

    private Counter() {
        setTitle("Row Counter");
        setSize(900, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10,7));
        addMenu();
        getContentPane().setBackground(new Color(174,237,255));
        Chart gen = new Chart("General counter");
        for (int i = 0; i < 6; i++) {
            this.add(gen.chart[i]);
        }
        add(gen.chart[8]);
        gen.chart[8].setVisible(false);
        addInvisibleCharts(10-counter);
        setCheckBoxes();
        JOptionPane.showMessageDialog(null,"Welcome! This counter accepts up to 9 different chart" +
                " counters. To reset counters, press \"-\" next to General Counter, then press R");
    }

    private void addInvisibleCharts(int n) {
        for (int i = 0; i < n; i++) {
            Chart x = new Chart("invisible "+i);
            for (int j = 0; j < 6; j++) {
                add(x.chart[j]);
                x.chart[j].setVisible(false);
            }
            add(x.chart[8]);
            x.chart[8].setVisible(false);
        }
    }
    private void setCheckBoxes() {
        for (JComponent[] c : Chart.charts.subList(1, Chart.charts.size())) {
            ((JCheckBox) c[0]).addActionListener(l -> {
                if (((JCheckBox) c[0]).isSelected()) {
                    for (int i = 0; i < 6; i++) {
                        remove(c[i]);
                    }
                    remove(c[8]);
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
        JButton reset = new JButton("Reset counters");
        reset.addActionListener(e-> {
            for (JComponent[] c : Chart.charts.subList(0, Chart.charts.size())) {
                if (c[1].isVisible()) {
                    ((JLabel) c[2]).setText(((JLabel) c[6]).getText());
                    ((JLabel) c[8]).setText("0");
                }
            }
        });
        newchart.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(
                    "Enter chart name:");
            String resetafter = JOptionPane.showInputDialog("Reset loop after row " +
                    "number: (if not reset, enter any number >1000)");

            String startfrom = JOptionPane.showInputDialog("Start from row");
            if (name != null && !name.equals("")) {
                for (int i = 1; i < 6; i++) {
                    if (i == 1) ((JLabel) Chart.charts.get(counter)[i]).setText(name);
                    Chart.charts.get(counter)[i].setVisible(true);
                }
                if (Integer.parseInt(resetafter) <= 1000) Chart.charts.get(counter)[8].setVisible(true);
                else Chart.charts.get(counter)[8].setVisible(false);
                ((JLabel) Chart.charts.get(counter)[6]).setText(startfrom);
                ((JLabel) Chart.charts.get(counter)[2]).setText(startfrom);
                ((JLabel) Chart.charts.get(counter)[7]).setText(resetafter);
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
        menu.add(reset);
        setJMenuBar(menu);
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        c.setVisible(true);
    }
}
