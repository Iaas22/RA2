public class Dona extends Thread {
    private final BanyUnisex lavabo;

    public Dona(String nom, BanyUnisex lavabo) {
        super(nom);
        this.lavabo = lavabo;
    }

    @Override
    public void run() {
        try {
            lavabo.entraDona(getName());
            utilitzaLavabo();
            lavabo.surtDona(getName());
        } catch (InterruptedException e) {
            interrupt();
        }
    }

    private void utilitzaLavabo() throws InterruptedException {
        long temps = 2000L + (long) (Math.random() * 1000L);
        Thread.sleep(temps);
    }
}
