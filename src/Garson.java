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
        synchronized (lock){
            Musteri musteri = Restaurant.musteriler.poll();
            if(musteri == null || musteri.getMasa() == null){//müşteri yoksa sipariş alma
                // lock.wait();
                Restaurant.musteriler.add(musteri);
            }else if(musteri.getMasa() != null){//masası varsa sipariş al
                System.out.println(this.getIsim() + " :" + musteri.getIsim() + "'nin siparişini alıyor.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                while(musteri.isYemekOlduMu() == false){
                    for(int i=0;i<Restaurant.ascilar.size();i++){
                      /*  if(Restaurant.ascilar.get(i).musteri == null && Restaurant.ascilar.get(i).isMesgulMu()==false && musteri.isOdemeYapildiMi()==false){ // 0. aşçının müşterisi yoksa
                            Restaurant.ascilar.get(i).setMusteri(musteri);
                            Restaurant.ascilar.get(i).setMesgulMu(true);
                            musteri.AsciIslemleri(Restaurant.ascilar.get(i),musteri); // boş olan aşçıyı müşteriye yolla
                            break;
                        }*/

                   //  System.out.println(Restaurant.ascilar.get(0).musterilerim.size());
                        if((Restaurant.ascilar.get(0).musterilerim.remainingCapacity() > 0) && (Restaurant.ascilar.get(0).isMesgulMu()==false) && (musteri.isOdemeYapildiMi()==false)){
                            Restaurant.ascilar.get(0).setMusteri(musteri);
                            Restaurant.ascilar.get(0).musterilerim.offer(musteri);
                            Restaurant.ascilar.get(0).setMesgulMu(true);
                            musteri.AsciIslemleri(Restaurant.ascilar.get(0),musteri); // boş olan aşçıyı müşteriye yolla
                            break;
                        }
                        if(Restaurant.ascilar.get(1).musterilerim.remainingCapacity() > 0  && (Restaurant.ascilar.get(0).isMesgulMu()==false) && (musteri.isOdemeYapildiMi()==false)){
                            Restaurant.ascilar.get(1).setMusteri(musteri);
                            Restaurant.ascilar.get(1).musterilerim.offer(musteri);
                            Restaurant.ascilar.get(1).setMesgulMu(true);
                            if(Restaurant.ascilar.get(0).musterilerim != null  && Restaurant.ascilar.get(0).musterilerim.size()>1 ){
                                Restaurant.ascilar.get(0).musterilerim.poll();
                            }
                            musteri.AsciIslemleri(Restaurant.ascilar.get(1),musteri); // boş olan aşçıyı müşteriye yolla
                            break;
                        }
                    }
               }
                bekle();
            }
        }
    }
    public synchronized void bekle(){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        while(!Restaurant.musteriler.isEmpty()){
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