
public class Fumador implements Runnable {
    private Estanc estanc;
    private int id;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int fumades;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        this.tabac = null;
        this.llumi = null;
        this.paper = null;
        this.fumades = 0;
    }

    public void compraTabac() {
        System.out.println("Fumador " + id + " comprant Tabac");
        while ((this.tabac = estanc.venTabac()) == null) {
            try {
                synchronized (estanc) {
                    estanc.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void compraPaper() {
        System.out.println("Fumador " + id + " comprant Paper");
        while ((this.paper = estanc.venPaper()) == null) {
            try {
                synchronized (estanc) {
                    estanc.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void compraLlumi() {
        System.out.println("Fumador " + id + " comprant Llumi");
        while ((this.llumi = estanc.venLlumi()) == null) {
            try {
                synchronized (estanc) {
                    estanc.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void fuma() {
        System.out.println("Fumador " + id + " fumant");
        try {
            Thread.sleep((long) (500 + Math.random() * 500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.tabac = null;
        this.llumi = null;
        this.paper = null;
        fumades++;
        System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            compraTabac();
            compraPaper();
            compraLlumi();
            fuma();
        }
    }
}


    
