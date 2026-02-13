public class Filosof extends Thread {
    private String nom;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;

    public Filosof(String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        this.nom = nom;
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        this.gana = 0;
    }

}
