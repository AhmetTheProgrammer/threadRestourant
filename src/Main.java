import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArrayList<Garson> garsonlar = new ArrayList<>();
        int toplam = 0;
        int musteriCount = 1;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Adım sayısını giriniz:");
        int adimSayisi = scanner.nextInt();
        Kasa kasa = new Kasa();
        kasa.start();
        for(int i = 1; i <= 6; i++){
            Masa masa = new Masa("Masa " + i);
            Restaurant.masalar.add(masa);
        }
        while (adimSayisi  > 0){
            System.out.println("Müşteri sayısı girin:");
            int musteriSayisi = scanner.nextInt();
            toplam += musteriSayisi;
            System.out.println("Öncelikli müşteri sayısı girin:");
            int oncelikliMusteriSayisi = scanner.nextInt();
            for(int i = musteriCount; i <= toplam; i++){
                if(oncelikliMusteriSayisi > 0){
                    int oncelik = random.nextInt(0,2);
                    if(oncelik == 1){
                        Musteri musteri = new Musteri("Müşteri " + i);
                        musteri.setOncelikliMi(true);
                        Restaurant.musteriler.addFirst(musteri);
                        musteriCount++;
                        oncelikliMusteriSayisi--;
                    }
                    else{
                        Musteri musteri = new Musteri("Müşteri " + i);
                        musteriCount++;
                        Restaurant.musteriler.add(musteri);
                    }
                }
                else{
                    Musteri musteri = new Musteri("Müşteri " + i);
                    musteriCount++;
                    Restaurant.musteriler.add(musteri);
                }
            }
            adimSayisi--;
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
}













