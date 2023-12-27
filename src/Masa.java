import java.awt.*;

class Masa extends PhysicMember{
    private String isim;
    private boolean doluMu;
    int x;
    int y;
    int masaBoyut;
    private Musteri musteri;
    public Masa(String isim,int x, int y, int boyut){
        this.isim = isim;
        this.doluMu = false;//Bütün masalar boş oluşsun
        this.x = x;
        this.y = y;
        this.masaBoyut = boyut;
    }
    public boolean isDoluMu() {
        return doluMu;
    }
    public void setDoluMu(boolean doluMu) {
        this.doluMu = doluMu;
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
    public void drawtoScreen(Graphics g){
        if(this.isDoluMu()){
            g.setColor(Color.RED);
        }
        else{
            g.setColor(Color.GREEN);
        }
        g.fillOval(x,y,masaBoyut,masaBoyut);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        if(this.getMusteri() != null){
            g.drawString(this.getMusteri().getIsim(),x + 30,y + 50);
        }
        else{
            g.drawString("ŞU ANLIK BOŞ",x + 5,y + 50);
        }

    }
}