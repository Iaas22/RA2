public class DormAleatori extends Thread {
    private long timeCreation;
    
    public DormAleatori(String name) {
        super(name);
        this.timeCreation = System.currentTimeMillis();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            long interval = (long) (Math.random() * 1000);
            long totalTime = System.currentTimeMillis() - timeCreation;
            System.out.println(getName() + " (" + i + ") a dormir  " + interval + "ms total  " + totalTime + "ms");
            
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {}
        }
        System.out.println(getName() + " s'acabà");
    }
    
    public static void main(String[] args) {
        new DormAleatori("Joan").start();
        new DormAleatori("Pep").start();
        System.out.println("-- Fi de main -----------");
    }
}
