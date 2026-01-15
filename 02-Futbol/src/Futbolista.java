package src;


public class Futbolista extends Thread {
    private static int NUM_JUGADORS = 11;
    private int NUM_TIRADES=20;
    private float PROBABILITAT = 0.5f;
    private int ngols;
    private int ntirades;

    public Futbolista(String nombre) {
        super(nombre);
        ngols = 0;
        ntirades = 0;
    }

    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (Math.random() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public int getGoles() {
        return ngols;
    }

    public int getTirades() {
        return ntirades;
    }

    public static void main(String[] args) throws InterruptedException {
        String[] nombres = {"AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGGG", "HHH", "IIII", "JJJJ", "KKK"};
        Futbolista equipo[] = new Futbolista[NUM_JUGADORS];
        
        System.out.println("Inicia dels xuts --------------------");
        
    }
    
}
