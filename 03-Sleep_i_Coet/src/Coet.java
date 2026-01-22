public class Coet {
    private Motor[] motors;
    
    public Coet() {
        motors = new Motor[4];
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor(i);
            motors[i].start();
        }
    }
    
    public void passarAPotencia(int potencia) {
        System.out.println("Passant a potencia " + potencia);
        for (int i = 0; i < 4; i++) {
            motors[i].passarAPotencia(potencia);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Coet coet = new Coet();
        
        coet.passarAPotencia(3);
        Thread.sleep(5000);
        
        coet.passarAPotencia(7);
        Thread.sleep(5000);
        
        coet.passarAPotencia(0);
    }
}
