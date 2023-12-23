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
        synchronized (Restaurant.masalar)  {
            for (Masa masa : Restaurant.masalar) {
                if(!masa.isDoluMu()){//Dolu değilse
                    this.setMasa(masa);
                    this.getMasa().setDoluMu(true);
                    if(this.isOncelikliMi()){
                        System.out.println(this.getIsim() + " " + this.getMasa().getIsim() + "a oturdu ve oncelikli");
                        Restaurant.dosyayaYaz(this.getIsim() + " " + this.getMasa().getIsim() + "a oturdu ve oncelikli");
                        break;
                    }
                    else if(!this.isOncelikliMi()){
                        System.out.println(this.getIsim() + " " + this.getMasa().getIsim() + "a oturdu");
                        Restaurant.dosyayaYaz(this.getIsim() + " " + this.getMasa().getIsim() + "a oturdu");
                        break;
                    }
                }
            }
        }
    }

    public void AsciIslemleri(Asci asci, Musteri musteri){
        asci.setMesgulMu(true);
        System.out.println(asci.getIsim() +" "+musteri.getIsim()+" 'in yemeğinin hazırlıyor");
        Restaurant.dosyayaYaz(asci.getIsim() +" "+musteri.getIsim()+" 'in yemeğinin hazırlıyor");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        musteri.setYemekOlduMu(true);
        asci.setMusteri(null);
        asci.setMesgulMu(false);
        yemekYe(musteri);
    }
    public  void yemekYe(Musteri musteri){
        synchronized (lock) {
            try {
                System.out.println(this.getIsim() + "yemeğini yiyiyor");
                Restaurant.dosyayaYaz(this.getIsim() + "yemeğini yiyiyor");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            odemeYap();
        }
    }
    public  void odemeYap(){
        synchronized (lock){
            System.out.println(this.getIsim()+" ödeme yaptı");
            Restaurant.dosyayaYaz(this.getIsim()+" ödeme yaptı");
            this.setOdemeYapildiMi(true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.getMasa().setDoluMu(false);
        this.setMasa(null);
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