import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;
public class Problem1 extends JFrame {
    int pencereBoyutX = 800;
    int pencereBoyutY = 600;
    int toplam = 0;
    int oncelikli = 0;
    int x = 10;
    int y = 10;
    Insets workingArea;
    final int targetFPS = 60;
    BufferedImage image;
    JTextField adimSayisi;
    static PhysicsEngine pe = new PhysicsEngine();
    public static void problem1(int musteriSayisi, int oncelikliMusteriSayisi, int adimSayisi){
        ArrayList<Garson> garsonlar = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Restaurant.kasa = new Kasa();
        pe.addMember(Restaurant.kasa);
        Thread thread = new Thread(Restaurant.kasa);
        thread.start();
        int asagi = 0;
        int masaX = 70;
        int masaY = 70;
        int masaBoyutX = 100;
        for(int i = 1; i <= 6; i++){
            if(i % (4 + 3 * asagi) == 0 ){
                masaY+= 200;
                masaX = 70;
                asagi++;
            }
            Masa masa = new Masa("Masa " + i,masaX,masaY,masaBoyutX);
            Restaurant.masalar.add(masa);
            pe.addMember(masa);
            masaX += 120;
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
            Restaurant.asilMusteriler.add(musteri);
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
    private void grafikGuncelle() {
        Graphics frameGraphic = getGraphics();
        Graphics bufferGraphic = this.image.getGraphics();

        bufferGraphic.setColor(Color.GRAY);
        bufferGraphic.fillRect(0,0, pencereBoyutX, pencereBoyutY);
        bufferGraphic.setColor(Color.BLACK);
        pe.drawtoScreen(bufferGraphic);

        frameGraphic.drawImage(this.image, workingArea.left, workingArea.top,this);
    }
    private void dongu(){
        long targetTime = 1_000_000_000 / targetFPS;
        boolean konrolcü = true;
        problem1(toplam, oncelikli, Integer.parseInt(adimSayisi.getText()));
        while(konrolcü) {
            long startingOfLoop = System.nanoTime();
            long remainingTime = targetTime -(System.nanoTime() - startingOfLoop);
            try {
                if(remainingTime > 0)
                    Thread.sleep(remainingTime/1000000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mantıkGuncelle();
            grafikGuncelle();
        }
    }
    private void mantıkGuncelle(){

    }
    Problem1(){
        workingArea = getInsets();
        setSize(workingArea.left + pencereBoyutX + workingArea.right,
                workingArea.top + pencereBoyutY + workingArea.bottom);
        image = new BufferedImage(pencereBoyutX, pencereBoyutY, BufferedImage.TYPE_INT_RGB);;
        setTitle("Problem1 ");
        setLayout(null);

        adimSayisi = new JTextField();
        adimSayisi.setFont(new Font("",Font.BOLD,17));
        adimSayisi.setBounds(300,100,180,40);
        add(adimSayisi);

        JLabel lAdimSayisi = new JLabel("Adım Sayısını Giriniz");
        lAdimSayisi.setFont(new Font("",Font.BOLD,17));
        lAdimSayisi.setBounds(100,100,400,40);
        add(lAdimSayisi);

        JButton adimSayisiTamam = new JButton("Tamam");
        adimSayisiTamam.setFont(new Font("",Font.BOLD,20));
        adimSayisiTamam.setBounds(500,100,150,40);
        add(adimSayisiTamam);

        JButton baslat = new JButton("Başlat");
        baslat.setFont(new Font("",Font.BOLD,20));
        baslat.setBounds(300,500,150,40);
        baslat.setVisible(false);
        add(baslat);
        ArrayList<JTextField> textFields = new ArrayList<>();
        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<JTextField> textFieldsOncelikli = new ArrayList<>();
        adimSayisiTamam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                lAdimSayisi.setVisible(false);
                adimSayisiTamam.setVisible(false);
                adimSayisi.setVisible(false);
                repaint();
                int yEkseni = 50;
                for(int i = 0;i < Integer.parseInt(adimSayisi.getText()); i++){
                    JLabel musteriSayisi = new JLabel("Müşteri Sayısını giriniz" + "(" + (i + 1) + ". Adım)");
                    musteriSayisi.setFont(new Font("",Font.BOLD,17));
                    musteriSayisi.setBounds(100,40 + (yEkseni),400,40);
                    labels.add(musteriSayisi);
                    add(musteriSayisi);

                    JTextField tMusteriSayisi = new JTextField();
                    tMusteriSayisi.setFont(new Font("",Font.BOLD,17));
                    tMusteriSayisi.setBounds(500,40 + (yEkseni),175,40);
                    add(tMusteriSayisi);
                    textFields.add(tMusteriSayisi);
                    yEkseni+=50;

                    JLabel oncelikliMusteriSayisi = new JLabel("Öncelikli müşteri Sayısını giriniz" + "(" + (i + 1) + ". Adım)");
                    oncelikliMusteriSayisi.setFont(new Font("",Font.BOLD,17));
                    oncelikliMusteriSayisi.setBounds(100,40 + (yEkseni),400,40);
                    labels.add(oncelikliMusteriSayisi);
                    add(oncelikliMusteriSayisi);

                    JTextField tOncelikliMusteriSayisi = new JTextField();
                    tOncelikliMusteriSayisi.setFont(new Font("",Font.BOLD,17));
                    tOncelikliMusteriSayisi.setBounds(500,40 + (yEkseni),175,40);
                    add(tOncelikliMusteriSayisi);
                    textFieldsOncelikli.add(tOncelikliMusteriSayisi);
                    yEkseni+=50;
                }
                baslat.setVisible(true);
                repaint();
            }
        });
        baslat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toplam = 0;
                oncelikli = 0;
                for (JTextField text : textFields) {
                    toplam += Integer.parseInt(text.getText());
                    text.setVisible(false);
                }
                for (JTextField text : textFieldsOncelikli) {
                    oncelikli += Integer.parseInt(text.getText());
                    text.setVisible(false);
                }
                dongu();
            }
        });
    }
}
