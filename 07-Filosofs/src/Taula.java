public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;
    private int numFilosofs;

    public Taula(int numFilosofs) {
        this.numFilosofs = numFilosofs;
        
        forquilles = new Forquilla[numFilosofs];
        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

      
        comensals = new Filosof[numFilosofs];
        for (int i = 0; i < numFilosofs; i++) {
            Forquilla forquillaEsquerra = forquilles[i];
            Forquilla forquillaDreta = forquilles[(i + 1) % numFilosofs];
            comensals[i] = new Filosof("fil" + i, forquillaEsquerra, forquillaDreta);
        }
    }

    public void showTaula() {
        System.out.println("==== TAULA ====");
        for (int i = 0; i < comensals.length; i++) {
            System.out.println("Filosof " + comensals[i].getNom() + " te les forquilles:");
            System.out.println("  - Esquerra: " + forquilles[i].getNumero());
            System.out.println("  - Dreta: " + forquilles[(i + 1) % numFilosofs].getNumero());
        }
        System.out.println("===============");
    }

    public void cridarATaula() {
        System.out.println("\nInician els filosofs a menjar...\n");
        for (Filosof filosof : comensals) {
            filosof.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(5);
        taula.showTaula();
        taula.cridarATaula();
    }
}
