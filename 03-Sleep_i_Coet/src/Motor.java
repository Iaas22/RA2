public class Motor extends Thread {
    private int id;
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;
    
    public Motor(int id) {
        super("Motor " + id);
        this.id = id;
    }
    
    public synchronized void passarAPotencia(int potencia) {
        if (potencia >= 0 && potencia <= 10) {
            this.potenciaObjectiu = potencia;
        }
    }
    
    @Override
    public void run() {
        while (true) {
            if (potenciaObjectiu > potenciaActual) {
                potenciaActual++;
                System.out.println("Motor " + id + ": Incre. Objectiu " + potenciaObjectiu + " Actual " + potenciaActual);
                try {
                    Thread.sleep((long) (Math.random() * 1000 + 1000)); 
                } catch (InterruptedException e) {}
            } else if (potenciaObjectiu < potenciaActual) {
                potenciaActual--;
                System.out.println("Motor " + id + ": Decre. Objectiu " + potenciaObjectiu + " Actual " + potenciaActual);
                try {
                    Thread.sleep((long) (Math.random() * 1000 + 1000)); 
                } catch (InterruptedException e) {}
            } else if (potenciaObjectiu == potenciaActual && potenciaActual > 0) {
                System.out.println("Motor " + id + ": FerRes Objectiu " + potenciaObjectiu + " Actual " + potenciaActual);
                try {
                    Thread.sleep((long) (Math.random() * 1000 + 1000));
                } catch (InterruptedException e) {}
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
        }
    }
}
