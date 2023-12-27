import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Problem2 extends JFrame {


    public int  hesapla(int kacSaniye, int kacMusteri,int toplamSure){
        int masaSayisi=kacMusteri;
        int garsonSayisi=kacMusteri;
        int asciSayisi=kacMusteri/2;

        int toplamGelenMusteri= (toplamSure/kacSaniye)*kacMusteri;

        int kazanc= toplamGelenMusteri - (masaSayisi+garsonSayisi+asciSayisi);
        return kazanc;

    }
    public Problem2(){
        JPanel  panel = new JPanel();
        add(panel);
        setSize(800,600);
        setTitle("Problem2 ");
        panel.setLayout(null);

        JTextField kacSaniye = new JTextField();
        kacSaniye.setFont(new Font("",Font.BOLD,17));
        kacSaniye.setBounds(300,100,180,40);
        panel.add(kacSaniye);

        JLabel lkacSaniye = new JLabel("Kaç saniyede ");
        lkacSaniye.setFont(new Font("",Font.BOLD,17));
        lkacSaniye.setBounds(100,100,400,40);
        panel.add(lkacSaniye);

        JTextField kacMusteri = new JTextField();
        kacMusteri.setFont(new Font("",Font.BOLD,17));
        kacMusteri.setBounds(300,150,180,40);
        panel.add(kacMusteri);

        JLabel lKacMusteri = new JLabel("Kaç müşteri ");
        lKacMusteri.setFont(new Font("",Font.BOLD,17));
        lKacMusteri.setBounds(100,150,400,40);
        panel.add(lKacMusteri);

        JTextField toplamSure = new JTextField();
        toplamSure.setFont(new Font("",Font.BOLD,17));
        toplamSure.setBounds(300,200,180,40);
        panel.add(toplamSure);

        JLabel ltoplamSure = new JLabel("Toplam Süre ");
        ltoplamSure.setFont(new Font("",Font.BOLD,17));
        ltoplamSure.setBounds(100,200,400,40);
        panel.add(ltoplamSure);

        JButton onayla = new JButton("Onayla");
        onayla.setFont(new Font("",Font.BOLD,20));
        onayla.setBounds(300,300,150,40);
        panel.add(onayla);

        onayla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kazanc= hesapla(Integer.parseInt(kacSaniye.getText()),Integer.parseInt(kacMusteri.getText()),Integer.parseInt(toplamSure.getText()));
                JLabel toplamKazanc = new JLabel("Toplam Kazanc "+ kazanc);
                toplamKazanc.setFont(new Font("",Font.BOLD,17));
                toplamKazanc.setBounds(300,400,400,40);
                panel.add(toplamKazanc);
                panel.repaint();
            }
        });

    }
}
