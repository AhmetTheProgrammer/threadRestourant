import javax.swing.*;
import java.util.*;

public class Problem1Gui extends JFrame {
    /*public static void problem1(int musteriSayisi, int oncelikliMusteriSayisi, int adimSayisi){
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
    */
   public Problem1Gui(int toplam ,int oncelikli,int adimSayisi){
        JPanel  panel = new JPanel();
        add(panel);
        setSize(800,800);
        setTitle("Problem1 Gui ");
        panel.setLayout(null);
        int y = 0;
        int x = 0;
        for(int i = 1; i <= 6 ;i++){
            JButton button =new JButton();
            button.setBounds(100 + x,100+y ,100,50);
            panel.add(button);
            if(i % 3 == 0){
                y += 70;
                x = -110;
            }
            x+=110;
        }
    }
}
