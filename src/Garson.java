import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Garson implements Runnable{
    private String isim;
    private boolean siparisAldıMı;
    private boolean siparisIletildiMi;
    private  final  Object  lock =new Object();
    public Garson(String isim){
        this.isim = isim;
        this.siparisAldıMı = false;
    }
    public  void siparisAl() throws InterruptedException {
        Musteri musteri = Restaurant.musteriler.poll();
        if(musteri == null){
            return;
        }
        if(musteri.getMasa() != null){
            System.out.println(this.getIsim() + " " + musteri.getIsim() + " 'in siparişini aldı");
            Restaurant.dosyayaYaz(this.getIsim() + " " + musteri.getIsim() + " 'in siparişini aldı");
            Thread.sleep(2000);
            synchronized (Restaurant.ascilar){
                for (Asci asci : Restaurant.ascilar) {
                    if(asci.musterilerim.remainingCapacity() > 0){
                        asci.musterilerim.offer(musteri);
                        break;
                    }
                }
            }
            bekle();
        }
        else{
            Restaurant.asilMusteriler.add(musteri);
        }
    }
    public synchronized void bekle(){
        try {
            Thread.sleep(11100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        while(!Restaurant.asilMusteriler.isEmpty()){
            try {
                siparisAl();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }
    public boolean isSiparisAldıMı() {
        return siparisAldıMı;
    }
    public void setSiparisAldıMı(boolean siparisAldıMı) {
        this.siparisAldıMı = siparisAldıMı;
    }

    public boolean isSiparisIletildiMi() {
        return siparisIletildiMi;
    }

    public void setSiparisIletildiMi(boolean siparisIletildiMi) {
        this.siparisIletildiMi = siparisIletildiMi;
    }
}