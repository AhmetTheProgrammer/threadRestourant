import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Problem1 extends JFrame {
    public static void problem1(int musteriSayisi, int oncelikliMusteriSayisi, int adimSayisi){
        ArrayList<Garson> garsonlar = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Kasa kasa = new Kasa();
        kasa.start();

        for(int i = 1; i <= 6; i++){
            Masa masa = new Masa("Masa " + i);
            Restaurant.masalar.add(masa);
        }
        Set<Integer> secilenler = new HashSet<>();

        for (int i = 0; i < musteriSayisi; i++) {
            int oncelik;
            do {
                oncelik = random.nextInt(musteriSayisi) + 1;
            } while (secilenler.contains(oncelik));
            secilenler.add(oncelik);
            Musteri musteri = new Musteri("Müşteri " + oncelik);
            if (oncelikliMusteriSayisi > 0) {
                musteri.setOncelikliMi(true);
                oncelikliMusteriSayisi--;
            }
            Restaurant.musteriler.add(musteri);
        }

        for (Musteri musteri : Restaurant.musteriler) {
            musteri.masayaOtur();
            Thread t = new Thread(musteri);
            t.start();
        }
        //En az 6 müşterinin masası set edilene kadar bekler
        while(true){
            int doluMasaSayisi = 0;
            for (Masa masa : Restaurant.masalar) {
                if(masa.isDoluMu()){
                    doluMasaSayisi++;
                }
            }
            if(doluMasaSayisi >= 6){
                break;
            }
        }

        for (int i = 1; i <= 2; i++) {
            Asci asci = new Asci("Aşçı " + i);
            Restaurant.ascilar.add(asci);
            asci.start();
        }
        for (int i = 1; i <= 3; i++) {
            Garson garson = new Garson("Garson " + i);
            garsonlar.add(garson);
            Thread t=new Thread(garson);
            t.start();
        }


    }
    Problem1(){
        JPanel  panel = new JPanel();
        add(panel);
        setSize(800,800);
        setTitle("Problem1 ");
        panel.setLayout(null);

        JTextField adimSayisi = new JTextField();
        adimSayisi.setFont(new Font("",Font.BOLD,17));
        adimSayisi.setBounds(300,100,180,40);
        panel.add(adimSayisi);

        JLabel lAdimSayisi = new JLabel("Adım Sayısını Giriniz");
        lAdimSayisi.setFont(new Font("",Font.BOLD,17));
        lAdimSayisi.setBounds(100,100,400,40);
        panel.add(lAdimSayisi);

        JButton adimSayisiTamam = new JButton("Tamam");
        adimSayisiTamam.setFont(new Font("",Font.BOLD,20));
        adimSayisiTamam.setBounds(500,100,150,40);
        panel.add(adimSayisiTamam);

        JButton baslat = new JButton("Başlat");
        baslat.setFont(new Font("",Font.BOLD,20));
        baslat.setBounds(300,700,150,40);
        panel.add(baslat);
        ArrayList<JTextField> textFields = new ArrayList<>();
        ArrayList<JTextField> textFieldsOncelikli = new ArrayList<>();
        adimSayisiTamam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                lAdimSayisi.setVisible(false);
                adimSayisiTamam.setVisible(false);
                adimSayisi.setVisible(false);
                panel.repaint();
                int yEkseni = 50;
                for(int i = 0;i < Integer.parseInt(adimSayisi.getText()); i++){
                    JLabel musteriSayisi = new JLabel("Müşteri Sayısını giriniz" + "(" + (i + 1) + ". Adım)");
                    musteriSayisi.setFont(new Font("",Font.BOLD,17));
                    musteriSayisi.setBounds(100,50 + (yEkseni),400,40);
                    panel.add(musteriSayisi);

                    JTextField tMusteriSayisi = new JTextField();
                    tMusteriSayisi.setFont(new Font("",Font.BOLD,17));
                    tMusteriSayisi.setBounds(500,50 + (yEkseni),175,40);
                    panel.add(tMusteriSayisi);
                    textFields.add(tMusteriSayisi);
                    yEkseni+=50;

                    JLabel oncelikliMusteriSayisi = new JLabel("Öncelikli müşteri Sayısını giriniz" + "(" + (i + 1) + ". Adım)");
                    oncelikliMusteriSayisi.setFont(new Font("",Font.BOLD,17));
                    oncelikliMusteriSayisi.setBounds(100,50 + (yEkseni),400,40);
                    panel.add(oncelikliMusteriSayisi);

                    JTextField tOncelikliMusteriSayisi = new JTextField();
                    tOncelikliMusteriSayisi.setFont(new Font("",Font.BOLD,17));
                    tOncelikliMusteriSayisi.setBounds(500,50 + (yEkseni),175,40);
                    panel.add(tOncelikliMusteriSayisi);
                    textFieldsOncelikli.add(tOncelikliMusteriSayisi);
                    yEkseni+=50;
                }
            }
        });
        baslat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int toplam = 0;
                int oncelikli = 0;
                for (JTextField text : textFields) {
                    toplam += Integer.parseInt(text.getText());
                }
                for (JTextField text : textFieldsOncelikli) {
                    oncelikli += Integer.parseInt(text.getText());
                }
                problem1(toplam, oncelikli, Integer.parseInt(adimSayisi.getText()));
            }
        });
    }
}
