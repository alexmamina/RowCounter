import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Counter extends JFrame {

    //TODO gridlayout, each column is box,text,num, +,-

    public Counter() {
        setTitle("Row Counter");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10,5));
        addMenu();
        for (int i = 0; i < 5; i++) {
            this.add((new Chart("General counter").chart)[i]);
        }
    }

    private void addMenu() {
        JMenuBar menu = new JMenuBar();
        JButton newchart = new JButton("Add");
        JButton remove = new JButton("Delete chart");

        newchart.addActionListener(e -> {
            Chart x = new Chart("");
            x.checkremove.addActionListener(l -> {
                if (x.checkremove.isSelected()) {
                    for (int i = 0; i < 5; i++) {
                        remove(x.chart[i]);
                    }
                    revalidate();
                    repaint();
                }
            });

            for (int i = 0; i < 5; i++) {
                add(x.chart[i]);
            }
            revalidate();
            repaint();
        });

        remove.addActionListener(e -> {
            if (remove.getText().equals("Delete chart")) {

                for (JComponent[] c : Chart.charts.subList(1, Chart.charts.size())) {
                    (c[0]).setVisible(true);
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
