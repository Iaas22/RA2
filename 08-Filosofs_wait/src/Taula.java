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
        for (int i = 0; i < comensals.length; i++) {
            int esquerra = forquilles[i].getNumero();
            int dreta = forquilles[(i + 1) % numFilosofs].getNumero();
            System.out.println("Comensals: fil" + i + " esq:" + esquerra + " dret:" + dreta);
        }
        System.out.println("-----------");
    }

    public void cridarATaula() {
        System.out.println();
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
