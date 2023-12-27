import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Musteri implements Runnable {
    private String isim;
    private Masa masa;
    private boolean siparisAlindiMi;
    private boolean yemekOlduMu;
    private  boolean odemeYapildiMi;
    private boolean oncelikliMi;
    private static final Object lock = new Object();
    public Musteri(String isim){
        this.isim = isim;
        this.yemekOlduMu = false;
        odemeYapildiMi=false;
    }


    public synchronized void yemekBekle(){
        while(!this.isYemekOlduMu())
        {

        }
    }

    @Override
    public void run() {
        //masası boşsa sürekli oturmaya çalışır
        while(this.getMasa() == null){
            Method.masayaOtur(this);
        }
    }
    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }
    public Masa getMasa() {
        return masa;
    }
    public void setMasa(Masa masa) {
        this.masa = masa;
    }

    public boolean isYemekOlduMu() {
        return yemekOlduMu;
    }

    public void setYemekOlduMu(boolean yemekOlduMu) {
        this.yemekOlduMu = yemekOlduMu;
    }

    public boolean isSiparisAlindiMi() {
        return siparisAlindiMi;
    }

    public void setSiparisAlindiMi(boolean siparisAlindiMi) {
        this.siparisAlindiMi = siparisAlindiMi;
    }

    public boolean isOdemeYapildiMi() {
        return odemeYapildiMi;
    }

    public void setOdemeYapildiMi(boolean odemeYapildiMi) {
        this.odemeYapildiMi = odemeYapildiMi;
    }

    public boolean isOncelikliMi() {
        return oncelikliMi;
    }

    public void setOncelikliMi(boolean oncelikliMi) {
        this.oncelikliMi = oncelikliMi;
    }
}