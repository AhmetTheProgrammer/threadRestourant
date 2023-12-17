import java.util.ArrayList;

class Musteri extends Thread {
    private String isim;
    private Masa masa;
    private boolean siparisAlindiMi;
    private boolean yemekOlduMu;
    private static final Object lock = new Object();
    public Musteri(String isim){
        this.isim = isim;
        this.yemekOlduMu = false;
    }
    public synchronized void masayaOtur(){
        for (Masa masa : Restaurant.masalar) {
            if(!masa.isDoluMu()){//Dolu değilse
                this.setMasa(masa);
                this.getMasa().setDoluMu(true);
                System.out.println(this.getIsim() + " " + this.getMasa().getIsim() + "a oturdu");
                break;
            }
        }
        yemekBekle();
    }
    public synchronized void yemekBekle(){
        while(!this.isYemekOlduMu()){

        }
        yemekYe();
    }
    public synchronized void yemekYe(){
        try {
            System.out.println(this.getIsim() + "yemeğini yiyiyor");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this.isAlive());
    }
    @Override
    public void run() {
        super.run();
        masayaOtur();
        yemekBekle();
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
}