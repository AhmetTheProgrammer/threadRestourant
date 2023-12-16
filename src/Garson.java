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
    public synchronized void siparisAl(){
        Musteri musteri = this.getMusteriler().get(0);
        System.out.println(this.getIsim() + " :" + musteri.getIsim() + "'nin siparişini alıyor.");
        for (Asci asci : this.getAscilar()) {
            if(!asci.isMesgulMu()){
                asci.getMusteriler().add(musteri);
                break;
            }
        }
        this.getMusteriler().remove(0);
        this.setSiparisAldıMı(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(true){
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
        while (!this.isSiparisAldıMı()) {
            siparisAl();
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

    public boolean isSiparisAldıMı() {
        return siparisAldıMı;
    }

    public void setSiparisAldıMı(boolean siparisAldıMı) {
        this.siparisAldıMı = siparisAldıMı;
    }
}