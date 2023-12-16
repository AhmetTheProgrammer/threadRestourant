class Masa {
    private String isim;
    private boolean doluMu;
    public Masa(String isim){
        this.isim = isim;
        this.doluMu = false;//Bütün masalar boş oluşsun
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
}