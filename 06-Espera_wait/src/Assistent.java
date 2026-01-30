public class Assistent extends Thread {
    private String nom;
    private Esdeveniment esdeveniment;

    public Assistent(String nom, Esdeveniment esdeveniment) {
        this.nom = nom;
        this.esdeveniment = esdeveniment;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {
        while (true) {
            if (Math.random() < 0.5) {
                esdeveniment.ferReseva(this);
            } else {
                esdeveniment.cancelaReseva(this);
            }

            try {
                long temps = (long) (Math.random() * 1000);
                Thread.sleep(temps);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
