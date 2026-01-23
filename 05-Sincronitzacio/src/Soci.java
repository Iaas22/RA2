import java.util.Random;

public class Soci extends Thread {
    private Compte compte;
    private float aportacio = 10f;
    private int esperaMax = 100;
    private Random random;
    private int maxAnys = 10;

    public Soci(String nom) {
        super(nom);
        this.compte = Compte.getInstancia();
        this.random = new Random();
    }

    public Compte getCompte() {
        return compte;
    }

    @Override
    public void run() {
        for (int mes = 1; mes <= maxAnys * 12; mes++) {
            if (mes % 2 == 0) {
                compte.ingressar(aportacio);
            } else {
                compte.retirar(aportacio);
            }
            for (int i = 0; i < random.nextInt(esperaMax) * 10; i++) {
            }
        }
    }
}
