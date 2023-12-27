import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

class Asci extends Thread {
    private String isim;
    LinkedBlockingQueue<Musteri> musterilerim = new LinkedBlockingQueue<>(2);

    private boolean mesgulMu;

    public Asci(String isim){
        this.isim = isim;
        this.mesgulMu = false;
    }
    public void yemekHazırla(){
        this.setMesgulMu(true);
        Iterator<Musteri> musteriler = this.musterilerim.iterator();
        while (musteriler.hasNext()){
            System.out.println(this.getIsim() + " " + musteriler.next().getIsim() + " 'in yemeğini hazırlıyor");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Iterator<Musteri> musteriler2 = this.musterilerim.iterator();
        while (musteriler2.hasNext()){
            musteriler2.next().yemekYe();
        }
        this.musterilerim.clear();
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
        //Restorandaki müşterileri bitene kadar çalışır
        do{
            if(this.musterilerim.size() == 2){
                yemekHazırla();
            }
            else if(this.musterilerim.size() == 1){
                yemekHazırla();
            }
        }while (!Restaurant.asilMusteriler.isEmpty());
    }
    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }
    public boolean isMesgulMu() {
        return mesgulMu;
    }
    public void setMesgulMu(boolean mesgulMu) {
        this.mesgulMu = mesgulMu;
    }
}