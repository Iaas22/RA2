
public class Barri {
    private Estanc estanc;
    private Fumador[] fumadors;

    public Barri() {
        estanc = new Estanc();
        fumadors = new Fumador[3];
        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }
    }

    public void ficaEnMarxa() {
        Thread[] threadsFumadors = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threadsFumadors[i] = new Thread(fumadors[i]);
            threadsFumadors[i].start();
        }

        Thread threadEstanc = new Thread(estanc);
        threadEstanc.start();

        for (int i = 0; i < 3; i++) {
            try {
                threadsFumadors[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        estanc.tancarEstanc();
    }

    public static void main(String[] args) {
        Barri barri = new Barri();
        barri.ficaEnMarxa();
    }
}

