import java.util.ArrayList;

class Musteri extends Thread {
    private String isim;
    private ArrayList<Masa> masalar;
    private Masa masa;
    private boolean yemekOlduMu;
    private static final Object lock = new Object();
    public Musteri(String isim, ArrayList<Masa> masalar){
        this.isim = isim;
        this.yemekOlduMu = false;
        this.masalar = masalar;
    }
    public synchronized void masayaOtur(){
        for (Masa masa : this.getMasalar()) {
            if(!masa.isDoluMu()){//Dolu değilse
                this.setMasa(masa);
                this.getMasa().setDoluMu(true);
                System.out.println(this.getIsim() + " " + this.getMasa().getIsim() + "a oturdu");
                break;
            }
        }
    }
    public synchronized void yemekYe(){
        while(!this.isYemekOlduMu()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            System.out.println(this.getIsim() + "yemeğini yiyiyor");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        super.run();

        masayaOtur();
        yemekYe();
    }
    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }
    public ArrayList<Masa> getMasalar() {
        return masalar;
    }
    public void setMasalar(ArrayList<Masa> masalar) {
        this.masalar = masalar;
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
}