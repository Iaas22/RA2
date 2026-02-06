public class Filosof extends Thread {
    private String nom;
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private int gana;

    public Filosof(String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        this.nom = nom;
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        this.gana = 0;
    }

  
    public int getGana() {
        return gana;
    }

    public String getNom() {
        return nom;
    }
}
