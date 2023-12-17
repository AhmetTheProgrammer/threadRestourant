import java.util.ArrayList;
import java.util.Iterator;

public class Garson extends Thread{
    private String isim;
    private boolean siparisAldıMı;
    private boolean siparisIletildiMi;
    public Garson(String isim){
        this.isim = isim;
        this.siparisAldıMı = false;
    }
    public synchronized void siparisAl(){
        Musteri musteri = Restaurant.musteriler.poll();

        System.out.println(this.getIsim() + " :" + musteri.getIsim() + "'nin siparişini alıyor.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        do{
            Iterator iterator = Restaurant.ascilar.iterator();
            Asci asci;
            while(iterator.hasNext()){
                asci = (Asci) iterator.next();
                if(asci.getMusteri() == null){
                    asci.setMusteri(musteri);
                    this.setSiparisIletildiMi(true);
                    break;
                }
            }
        }while(!this.isSiparisIletildiMi());
        bekle();
    }
    public synchronized void bekle(){
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
        while(true){
            siparisAl();
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