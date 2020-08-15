import javax.swing.*;
import java.util.ArrayList;

public class Chart {
    public JComponent[] chart;
    public String name;
    public JCheckBox checkremove;
    public static ArrayList<JComponent[]> charts = new ArrayList<>();

    public Chart(String name) {
        chart = new JComponent[5];
        JLabel num = new JLabel("1");
        JButton inc = new JButton("+");
        inc.addActionListener(e -> {
            num.setText(String.valueOf(Integer.parseInt(num.getText()) + 1));
        });
        JButton dec = new JButton("-");
        dec.addActionListener(e -> {
            num.setText(String.valueOf(Integer.parseInt(num.getText()) - 1));
        });
        JLabel text;
        text = new JLabel(name);

        checkremove = new JCheckBox();
        checkremove.setSelected(false);
        checkremove.setVisible(false);
        chart[0] = checkremove;
        chart[1] = text;
        chart[2] = num;
        chart[3] = inc;
        chart[4] = dec;


        charts.add(chart);
    }

}
