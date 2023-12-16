import java.util.ArrayList;

public class Garson extends Thread{
    private String isim;
    private ArrayList<Musteri> musteriler;
    private ArrayList<Asci> ascilar;
    private boolean siparisAldıMı;
    private static final Object lock = new Object();
    public Garson(String isim){
        this.isim = isim;
        this.siparisAldıMı = false;
    }
    @Override
    public void run() {
        super.run();
        while (!this.musteriler.isEmpty()) {
            synchronized (lock) {
                if(!this.musteriler.isEmpty()){
                    Musteri musteri = this.musteriler.get(0);
                    System.out.println(this.getIsim() + " :" + musteri.getIsim() + "'nin siparişini alıyor.");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    for (Asci asci : this.ascilar) {
                        if(asci.isMesgulMu() == false){
                            asci.getMusteriler().add(musteri);
                            break;
                        }
                    }
                    this.musteriler.remove(0);
                    this.siparisAldıMı = true;

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
    public ArrayList<Musteri> getMusteriler() {
        return musteriler;
    }
    public void setMusteriler(ArrayList<Musteri> musteriler) {
        this.musteriler = musteriler;
    }

    public ArrayList<Asci> getAscilar() {
        return ascilar;
    }

    public void setAscilar(ArrayList<Asci> ascilar) {
        this.ascilar = ascilar;
    }
}