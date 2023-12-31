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
    public synchronized void masayaOtur(){
        boolean oturabilirMi = true;
        for (Musteri musteri1 : Restaurant.musteriler) {
            if(musteri1.isOncelikliMi() && musteri1.getMasa() == null){
                oturabilirMi = false;
                break;
            }
        }
        synchronized (Restaurant.masalar)  {
            for (Masa masa : Restaurant.masalar) {
                if(!masa.isDoluMu()){//Dolu değilse
                    if(this.isOncelikliMi()){
                        this.setMasa(masa);
                        this.getMasa().setDoluMu(true);
                        this.getMasa().setMusteri(this);
                        System.out.println(this.getIsim() + " " + this.getMasa().getIsim() + "a oturdu ve oncelikli");
                        Restaurant.dosyayaYaz(this.getIsim() + " " + this.getMasa().getIsim() + "a oturdu ve oncelikli");
                        break;
                    }
                    else if(!this.isOncelikliMi() && oturabilirMi){
                        this.setMasa(masa);
                        this.getMasa().setDoluMu(true);
                        this.getMasa().setMusteri(this);
                        System.out.println(this.getIsim() + " " + this.getMasa().getIsim() + "a oturdu");
                        Restaurant.dosyayaYaz(this.getIsim() + " " + this.getMasa().getIsim() + "a oturdu");
                        break;
                    }
                }
            }
        }
    }
    public void yemekYe(){
        this.setYemekOlduMu(true);
        try {
            System.out.println(this.getIsim() + " yemeğini yiyiyor");
            Restaurant.dosyayaYaz(this.getIsim() + " yemeğini yiyiyor");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        odemeYap();
    }
    public  void odemeYap(){
        synchronized (lock){
            System.out.println(this.getIsim() + " ödeme yapıyor");
            Restaurant.kasa.setMusteri(this);
            Restaurant.dosyayaYaz(this.getIsim() + " ödeme yapıyor");
            this.setOdemeYapildiMi(true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Restaurant.kasa.setMusteri(null);
        }
        this.getMasa().setDoluMu(false);
        this.getMasa().setMusteri(null);
        this.setMasa(null);
        Restaurant.asilMusteriler.remove(this);
    }
    public synchronized void yemekBekle(){
        while(!this.isYemekOlduMu())
        {

        }
    }
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while(this.getMasa() == null){
            long end = System.currentTimeMillis();
            float sec = (end - start) / 1000F;
            if(sec > 20){
                System.out.println(this.getIsim() + " 20 saniye bekledi ve masaya oturamadı.");
                Restaurant.dosyayaYaz(this.getIsim() + " 20 saniye bekledi ve masaya oturamadı.");
                break;
            }
            masayaOtur();
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