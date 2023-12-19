import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

public class Restaurant {
    public static ConcurrentLinkedDeque<Musteri> musteriler = new ConcurrentLinkedDeque<>();
    //public static thread safe bir ascilar arraylisti oluşturur
    public static CopyOnWriteArrayList<Asci> ascilar = new CopyOnWriteArrayList<>();
    public static ArrayList<Masa> masalar = new ArrayList<>();
    Restaurant(){

    }
}
