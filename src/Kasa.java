import java.awt.*;

class Kasa extends PhysicMember implements Runnable {
    private String isim;
    private Musteri musteri;
    public Kasa(){
        this.isim = "Kasa";
    }

    @Override
    public void drawtoScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(625,140,100,40);
        g.fillRect(665,180,20,20);
        g.drawRect(600,200,150,100);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("KASA",640,250);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        if(getMusteri() != null){
            g.drawString(getMusteri().getIsim(),635,170);
        }
        else{
            g.drawString(" ",635,170);
        }
    }

    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    @Override
    public void run() {

    }
}