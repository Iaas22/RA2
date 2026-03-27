public class Home extends Thread {
    private final BanyUnisex lavabo;

    public Home(String nom, BanyUnisex lavabo) {
        super(nom);
        this.lavabo = lavabo;
    }

    @Override
    public void run() {
        try {
            lavabo.entraHome(getName());
            utilitzaLavabo();
            lavabo.surtHome(getName());
        } catch (InterruptedException e) {
            interrupt();
        }
    }

    private void utilitzaLavabo() throws InterruptedException {
        long temps = 1000L + (long) (Math.random() * 1000L);
        Thread.sleep(temps);
    }
}
