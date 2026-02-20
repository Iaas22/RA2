public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;

    public Taula(int numFilosofs) {
        comensals = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            Forquilla esquerra = forquilles[i];
            Forquilla dreta = forquilles[(i + 1) % numFilosofs];
            comensals[i] = new Filosof("filosof" + i, esquerra, dreta);
        }
    }

    public void showTaula() {
        System.out.println("\n=== Estado de la Taula ===");
        for (int i = 0; i < comensals.length; i++) {
            System.out.print("Filòsof " + i + ": Forquilla " + i + ", Forquilla " + (i + 1) % comensals.length);
            System.out.println();
        }
        System.out.println("==========================\n");
    }

    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            filosof.start();
        }
    }

    public static void main(String[] args) {
        int numFilosofs = 5;
        Taula taula = new Taula(numFilosofs);
        taula.showTaula();
        taula.cridarATaula();
    }
}
