public class Associacio {
    private Soci[] socis;

    public Associacio() {
        socis = new Soci[1000];
        for (int i = 0; i < socis.length; i++) {
            socis[i] = new Soci("");
        }
    }

    public void iniciaCompteTemposSocis() {
        for (Soci soci : socis) {
            soci.start();
        }
    }

    public void esperaPeriodeSocis() {
        for (Soci soci : socis) {
            try {
                soci.join();
            } catch (InterruptedException e) {
                //ignorar
            }
        }
    }

    public void mostraBalancComptes() {
        System.out.println("Saldo final de la cuenta: " + Compte.getInstancia().getSaldo());
    }

    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        associacio.iniciaCompteTemposSocis();
        associacio.esperaPeriodeSocis();
        associacio.mostraBalancComptes();
    }
}
