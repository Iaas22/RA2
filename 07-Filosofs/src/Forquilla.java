public class Forquilla {
    private int numero;
    private boolean enUs;

    public Forquilla(int numero) {
        this.numero = numero;
        this.enUs = true;
    }

    public synchronized int getNumero() {
        return numero;
    }

    public synchronized void setNumero(int numero) {
        this.numero = numero;
    }

    public synchronized boolean isEnUs() {
        return enUs;
    }

    public synchronized void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }
}
