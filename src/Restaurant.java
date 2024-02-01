import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Restaurant {
    public static ConcurrentLinkedDeque<Musteri> musteriler = new ConcurrentLinkedDeque<>();
    public static ConcurrentLinkedQueue<Musteri> asilMusteriler = new ConcurrentLinkedQueue<>();
    public static String dosyaYolu = "C:\\Users\\cetle\\OneDrive\\Masaüstü\\yazlab3cikti.txt";
    public static ArrayList<Asci> ascilar = new ArrayList<>();
    public static ArrayList<Masa> masalar = new ArrayList<>();
    public static Kasa kasa;
    public static void dosyayaYaz(String metin){
        try {
            FileWriter fileWriter = new FileWriter(Restaurant.dosyaYolu, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(metin);

            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Restaurant(){

    }
}
