import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class GirisSayfasi extends JFrame {
   private JPanel panel;
    private  JButton problem1;
    private  JButton problem2;
    private JLabel label;
    public GirisSayfasi(){
        panel = new JPanel();
        panel.setLayout(null);
        add(panel);
        setSize(600,600);
        setTitle("RESTORAN SİSTEMİ");
        label = new JLabel();
        label.setBounds(0,0,800,600);
        panel.add(label);
        problem1 = new JButton("PROBLEM1");
        problem1.setFont(new Font((""), Font.BOLD,20));
        problem1.setBounds(50,200,200,40);
        panel.add(problem1);
        problem2 = new JButton("PROBLEM 2");
        problem2.setFont(new Font((""), Font.BOLD,20));
        problem2.setBounds(350,200,200,40);
        panel.add(problem2);

        problem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             Problem1 problem1= new Problem1();
             problem1.setVisible(true);
            }
        });
        problem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
    }
}
