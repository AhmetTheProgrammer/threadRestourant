import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Restaurant {
    public static ConcurrentLinkedQueue<Musteri> musteriler = new ConcurrentLinkedQueue<>();
    //public static thread safe bir ascilar arraylisti oluşturur
    public static ArrayList<Asci> ascilar = new ArrayList<>();
    public static ArrayList<Masa> masalar = new ArrayList<>();
    Restaurant(){

    }
}
