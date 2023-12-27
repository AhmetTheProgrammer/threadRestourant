public class Method {

    public static synchronized void masayaOtur(Musteri musteri){
        boolean oncelikliKaldiMi = false;
        synchronized (Restaurant.masalar)  {
            for (Masa masa : Restaurant.masalar) {
                if(!masa.isDoluMu()){//Dolu değilse
                    if(musteri.isOncelikliMi()){
                        musteri.setMasa(masa);
                        musteri.getMasa().setDoluMu(true);
                        System.out.println(musteri.getIsim() + " " + musteri.getMasa().getIsim() + "a oturdu ve oncelikli");

                        Restaurant.dosyayaYaz(musteri.getIsim() + " " + musteri.getMasa().getIsim() + "a oturdu ve oncelikli");
                        break;
                    }
                    //Restoran kuyruğundaki bütün müşterileri foreach ile döner
                    for (Musteri musteri1 : Restaurant.musteriler) {
                        if(musteri1.isOncelikliMi() == true){
                            oncelikliKaldiMi = true;
                        }
                    }
                    if(!musteri.isOncelikliMi() && !oncelikliKaldiMi){
                        musteri.setMasa(masa);
                        musteri.getMasa().setDoluMu(true);
                        System.out.println(musteri.getIsim() + " " + musteri.getMasa().getIsim() + "a oturdu");
                        Restaurant.dosyayaYaz(musteri.getIsim() + " " + musteri.getMasa().getIsim() + "a oturdu");
                        break;
                    }
                }
            }
        }
    }

    public static synchronized void AsciIslemleri(Asci asci, Musteri musteri){
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
    public static synchronized void yemekYe(Musteri musteri){
            try {
                System.out.println(musteri.getIsim() + "yemeğini yiyiyor");
                Restaurant.dosyayaYaz(musteri.getIsim() + "yemeğini yiyiyor");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            odemeYap(musteri);
    }
    public static synchronized void odemeYap(Musteri musteri){
            System.out.println(musteri.getIsim()+" ödeme yaptı");
            Restaurant.dosyayaYaz(musteri.getIsim()+" ödeme yaptı");
            musteri.setOdemeYapildiMi(true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        musteri.getMasa().setDoluMu(false);
        musteri.setMasa(null);
    }
}

