import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Chart {
    public JComponent[] chart;
    public String name;
    public JCheckBox checkremove;
    public static ArrayList<JComponent[]> charts = new ArrayList<>();
    public static ArrayList<JComponent[]> links = new ArrayList<>();

    public Chart(String name) {
        chart = new JComponent[6];
        JLabel num = new JLabel("1");
        JButton inc = new JButton("+");
        JButton dec = new JButton("-");
        JToggleButton link = new JToggleButton("Link to general");

        if (name.equals("General counter")) {
            inc.addActionListener(megaincrease);
            dec.addActionListener(megadecrease);
            link.setVisible(false);
            link.setEnabled(false);
            links.add(chart);
        } else {
            inc.addActionListener(e -> num.setText(String.valueOf(Integer.parseInt(num.getText()) + 1)));
            dec.addActionListener(e -> num.setText(String.valueOf(Integer.parseInt(num.getText()) - 1)));
        }
        JLabel text;
        text = new JLabel(name);

        link.addActionListener(e -> {
            if (link.isSelected()) links.add(chart);
            else links.remove(chart);
        });
        checkremove = new JCheckBox();
        checkremove.setSelected(false);
        checkremove.setVisible(false);
        chart[0] = checkremove;
        chart[1] = text;
        chart[2] = num;
        chart[3] = inc;
        chart[4] = dec;
        chart[5] = link;


        charts.add(chart);
    }

    private ActionListener megaincrease = e -> {
        for (JComponent[] c : links) {
            ((JLabel) c[2]).setText(String.valueOf(Integer.parseInt(((JLabel) c[2]).getText()) + 1));
        }
    };

    private ActionListener megadecrease = e -> {
        for (JComponent[] c : links) {
            ((JLabel) c[2]).setText(String.valueOf(Integer.parseInt(((JLabel) c[2]).getText()) - 1));
        }
    };

}
