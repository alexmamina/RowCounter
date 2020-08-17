import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Chart {
    public JComponent[] chart;
    public String name;
    public JCheckBox checkremove;
    public JLabel repeats;
    public JLabel resetafterrow;
    public JLabel startfromrow;
    public static ArrayList<JComponent[]> charts = new ArrayList<>();
    public static ArrayList<JComponent[]> links = new ArrayList<>();
    private Border b = BorderFactory.createMatteBorder(1,1,1,1,new Color(174,237,255));
    private Font f = new Font("Arial Rounded MT Bold", Font.PLAIN, 15);

    public Chart(String name) {
        chart = new JComponent[9];
        resetafterrow = new JLabel("1000000");
        startfromrow = new JLabel("1");
        repeats = new JLabel("0");
        repeats.setFont(f);
        JLabel num = new JLabel(startfromrow.getText());
        num.setFont(f);
        num.setForeground(new Color(26,0,255));
        JButton inc = new JButton("+");
        inc.setBorder(b);
        inc.setOpaque(true);
        inc.setBackground(new Color(249,191,255));
        JButton dec = new JButton("-");
        dec.setBorder(b);
        dec.setOpaque(true);
        dec.setBackground(new Color(201,191,255));
        JToggleButton link = new JToggleButton("Link to general");
        link.setBorder(b);
        if (name.equals("General counter")) {
            inc.addActionListener(megaincrease);
            dec.addActionListener(megadecrease);
            dec.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (dec.isFocusOwner() && (e.getKeyCode() == KeyEvent.VK_R)) {
                        for (JComponent[] c : charts.subList(0, charts.size())) {
                            if (c[1].isVisible()) ((JLabel) c[2]).setText(((JLabel) c[6]).getText());
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            link.setVisible(false);
            link.setEnabled(false);
            links.add(chart);
        } else {
            inc.addActionListener(e -> {
                int nextrow = Integer.parseInt(num.getText()) + 1;
            if (nextrow <= Integer.parseInt(resetafterrow.getText()))
                num.setText(String.valueOf(nextrow));
            else {
                num.setText(startfromrow.getText());
                repeats.setText(String.valueOf(Integer.parseInt(repeats.getText()) + 1));
            }
            });
            dec.addActionListener(e -> num.setText(String.valueOf(Integer.parseInt(num.getText()) - 1)));
        }
        JLabel text;
        text = new JLabel(name);
        text.setFont(f);
        link.setOpaque(true);
        link.setBackground(new Color(174,237,255));
        link.addActionListener(e -> {
            if (link.isSelected()) {
                link.setBackground(new Color(90,113,255));
                links.add(chart);
            }
            else {
                link.setBackground(new Color(174,237,255));
                links.remove(chart);
            }
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
        chart[6] = startfromrow;
        chart[7] = resetafterrow;
        chart[8] = repeats;

        charts.add(chart);
    }

    private ActionListener megaincrease = e -> {
        for (JComponent[] c : links) {
            int nextrow = Integer.parseInt(((JLabel) c[2]).getText()) + 1;
            if (nextrow <= Integer.parseInt(((JLabel) c[7]).getText()))
                ((JLabel) c[2]).setText(String.valueOf(nextrow));
            else {
                ((JLabel) c[2]).setText(((JLabel) c[6]).getText());
                ((JLabel) c[8]).setText(String.valueOf(Integer.parseInt(((JLabel) c[8]).getText()) + 1));
            }
        }
    };

    private ActionListener megadecrease = e -> {
        for (JComponent[] c : links) {
            ((JLabel) c[2]).setText(String.valueOf(Integer.parseInt(((JLabel) c[2]).getText()) - 1));
        }
    };

}
