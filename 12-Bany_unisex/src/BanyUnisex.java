import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    private static final int BANY_BUIT = 0;
    private static final int BANY_AMB_HOMES = 1;
    private static final int BANY_AMB_DONES = 2;
    
    private static final int CAPACITAT_MAX = 3;
    
    private int estatActual;
    private int ocupants;
    private Semaphore capacitat;
    private ReentrantLock lockEstat;
    
    public BanyUnisex() {
        this.estatActual = BANY_BUIT;
        this.ocupants = 0;
        this.capacitat = new Semaphore(CAPACITAT_MAX, true);
        this.lockEstat = new ReentrantLock(true);
    }
    
    public void entraHome(String nom) throws InterruptedException {
        while (!capacitat.tryAcquire()) {
            Thread.sleep(100);
        }
        
        lockEstat.lock();
        try {
            while (estatActual == BANY_AMB_DONES) {
                lockEstat.unlock();
                Thread.sleep(100);
                lockEstat.lock();
            }
            
            ocupants++;
            estatActual = BANY_AMB_HOMES;
            System.out.println(nom + " vol entrar al bany");
            System.out.println(nom + " entra al bany. Ocupants: " + ocupants);
        } finally {
            lockEstat.unlock();
        }
    }
    
    public void entraDona(String nom) throws InterruptedException {
        while (!capacitat.tryAcquire()) {
            Thread.sleep(100);
        }
        
        lockEstat.lock();
        try {
            while (estatActual == BANY_AMB_HOMES) {
                lockEstat.unlock();
                Thread.sleep(100);
                lockEstat.lock();
            }
            
            ocupants++;
            estatActual = BANY_AMB_DONES;
            System.out.println(nom + " vol entrar al bany");
            System.out.println(nom + " entra al bany. Ocupants: " + ocupants);
        } finally {
            lockEstat.unlock();
        }
    }
    
    public void surtHome(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
            }
            
            System.out.println(nom + " surt del bany. Ocupants: " + ocupants);
        } finally {
            lockEstat.unlock();
        }
    }
    
    public void surtDona(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
            }
            
            System.out.println(nom + " surt del bany. Ocupants: " + ocupants);
        } finally {
            lockEstat.unlock();
        }
    }
    
    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();
        
        // Crear 5 fils de Home
        for (int i = 0; i < 5; i++) {
            new Home("Home-" + i, bany).start();
        }
        
        // Crear 5 fils de Dona
        for (int i = 0; i < 5; i++) {
            new Dona("Dona-" + i, bany).start();
        }
    }
}
