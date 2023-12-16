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
    @Override
    public void run() {
        super.run();
        while (true) {
            synchronized (lock) {
                if(this.musteriler.size() == 1){
                    this.mesgulMu = true;
                    System.out.println(this.getIsim() + " " + this.musteriler.get(0).getIsim() + "'in yemeğini  hazırlıyor");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    this.musteriler.get(0).setYemekOlduMu(true);
                    this.musteriler.remove(0);
                    this.mesgulMu = false;
                }
                else if(this.musteriler.size() == 2){
                    this.mesgulMu = true;
                    System.out.println(this.musteriler.get(0).getIsim() + "'in yemeği  hazırlanıyor");
                    System.out.println(this.musteriler.get(1).getIsim() + "'in yemeği  hazırlanıyor");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    this.musteriler.get(0).setYemekOlduMu(true);
                    this.musteriler.get(1).setYemekOlduMu(true);
                    this.mesgulMu = false;
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
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

