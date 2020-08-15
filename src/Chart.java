import javax.swing.*;
import java.util.ArrayList;

public class Chart {
    public JPanel chart;
    public String name;
    public JCheckBox checkremove;
    public static ArrayList<JPanel> charts = new ArrayList<>();

    public Chart(String name) {
        chart = new JPanel();
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
        if (name.equals("")) {
            text = new JLabel(JOptionPane.showInputDialog("Enter chart name:"));
        } else
            text = new JLabel(name);

        checkremove = new JCheckBox();
        checkremove.setVisible(false);
        if (!text.getText().equals("General counter")) chart.add(checkremove);
        chart.add(text);
        chart.add(num);
        chart.add(inc);
        chart.add(dec);


        charts.add(chart);
    }

}
