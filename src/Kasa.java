class Kasa extends Thread{
    private String isim;
    public Kasa(){
        this.isim = "Kasa";
    }

    @Override
    public void start() {
        super.start();

    }

    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }
}