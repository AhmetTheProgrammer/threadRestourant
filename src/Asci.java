import java.util.ArrayList;

class Asci extends Thread {
    private String isim;
    private static final Object lock = new Object();
    private boolean mesgulMu;
    private ArrayList<Musteri> musteriler = new ArrayList<>();
    public Asci(String isim){
        this.isim = isim;
        this.mesgulMu = false;
    }
    public synchronized void yemekHazırla(){
        if(this.getMusteriler().size() == 1) {
            this.setMesgulMu(true);
            System.out.println(this.getIsim() + " " + this.getMusteriler().get(0).getIsim() + "'in yemeğini  hazırlıyor");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.getMusteriler().get(0).setYemekOlduMu(true);
            this.getMusteriler().remove(0);
            this.setMesgulMu(false);
            while(true){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @Override
    public void run() {
        super.run();
        while (true) {
                yemekHazırla();
            }
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

    public ArrayList<Musteri> getMusteriler() {
        return musteriler;
    }

    public void setMusteriler(ArrayList<Musteri> musteriler) {
        this.musteriler = musteriler;
    }
}

