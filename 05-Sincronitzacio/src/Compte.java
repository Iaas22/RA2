public class Compte {
    private float saldo;
    private static Compte instancia;

    private Compte() {
        this.saldo = 0;
    }

    public static synchronized Compte getInstancia() {
        if (instancia == null) {
            instancia = new Compte();
        }
        return instancia;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public synchronized void ingressar(float quantitat) {
        this.saldo += quantitat;
    }

    public synchronized void retirar(float quantitat) {
        this.saldo -= quantitat;
    }
}
