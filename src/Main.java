import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArrayList<Garson> garsonlar = new ArrayList<>();
        int musteriSayisi;
        int adimSayisi;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adım sayısını giriniz");
        adimSayisi=scanner.nextInt();
        Kasa kasa = new Kasa();
        kasa.start();
        for(int i = 1; i <= 6; i++){
            Masa masa = new Masa("Masa " + i);
            Restaurant.masalar.add(masa);
        }
        System.out.println("Müşteri sayısı girin:");
        musteriSayisi = scanner.nextInt();
        for(int i = 1; i <= musteriSayisi; i++){
            Musteri musteri = new Musteri("Müşteri " + i);
            Restaurant.musteriler.add(musteri);
            Thread t=new Thread(musteri);
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













