import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

class Asci extends Thread {
    private String isim;
    private static final Object lock = new Object();
    Musteri musteri;
    LinkedBlockingQueue<Musteri> musterilerim = new LinkedBlockingQueue<>(2);

    private boolean mesgulMu;

    public Asci(String isim){
        this.isim = isim;
        this.mesgulMu = false;
    }
    public synchronized void yemekHazırla(){
        this.setMesgulMu(true);
        System.out.println(this.getIsim() + " " + this.getMusteri().getIsim() + "'in yemeğini hazırlıyor...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.getMusteri().setYemekOlduMu(true);
        this.setMusteri(null);
        this.setMesgulMu(false);
    }
    public synchronized void bekle(){
        while(this.isMesgulMu()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void run() {
        super.run();
       /* while(true){
            if(this.getMusteri() != null){
             //   yemekHazırla();
            }
        }*/
    }
    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Musteri getMusteri() {
        return musteri;
    }
    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }
    public boolean isMesgulMu() {
        return mesgulMu;
    }
    public void setMesgulMu(boolean mesgulMu) {
        this.mesgulMu = mesgulMu;
    }
}

