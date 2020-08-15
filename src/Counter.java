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
        setLayout(new GridLayout(10,1));
        addMenu();
        this.add((new Chart("General counter").chart));
    }

    private void addMenu() {
        JMenuBar menu = new JMenuBar();
        JButton newchart = new JButton("Add");
        JButton remove = new JButton("Delete chart");

        newchart.addActionListener(e -> {
            Chart x = new Chart("");
            x.checkremove.addActionListener(l -> {
                if (x.checkremove.isSelected()) {
                    remove(x.chart);
                    revalidate();
                    repaint();
                }
            });

            add(x.chart);
            revalidate();
            repaint();
        });

        remove.addActionListener(e -> {
            if (remove.getText().equals("Delete chart")) {

                for (JPanel c : Chart.charts.subList(1, Chart.charts.size())) {
                    (c.getComponents()[0]).setVisible(true);
                    remove.setText("Done");
                }
            } else {
                for (JPanel c : Chart.charts.subList(1, Chart.charts.size())) {
                    (c.getComponents()[0]).setVisible(false);
                    remove.setText("Delete chart");
                }
            }
        });
        menu.add(newchart);
        menu.add(remove);
        add(menu);
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        c.setVisible(true);
        //c.add((new Chart("").chart));
    }
}
