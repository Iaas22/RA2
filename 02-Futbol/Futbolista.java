

public class Futbolista extends Thread {
    private static int NUM_JUGADORS = 11;
    private int NUM_TIRADES=20;
    private float PROBABILITAT = 0.5f;
    private int ngolsintirades;

    public Futbolista(String nombre) {
        super(nombre);
        ngolsintirades = 0;
    }

    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            if (Math.random() < PROBABILITAT) {
                ngolsintirades++;
            }
        }
    }

    public int getGoles() {
        return ngolsintirades;
    }

    public static void main(String[] args) throws InterruptedException {
        String[] nombres = {"AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", "JJJ", "KKK"};
        Futbolista equipo[] = new Futbolista[NUM_JUGADORS];
        
        System.out.println("Inicia dels xuts --------------------");
        
    
        for (int i = 0; i < equipo.length; i++) {
            equipo[i] = new Futbolista(nombres[i]);
            equipo[i].start();
        }
        
        for (int i = 0; i < equipo.length; i++) {
            equipo[i].join();
        }
        
        System.out.println("Fi dels xuts -----------------------");
        System.out.println("--- Estadísticas ---");
        int totalGoles = 0;
        for (int i = 0; i < equipo.length; i++) {
            int goles = equipo[i].getGoles();
            totalGoles += goles;
            System.out.println(equipo[i].getName() + " -> " + goles + " gols");
        }
    }
}

