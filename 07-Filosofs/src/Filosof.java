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

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                menjar();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filòsof: " + nom + " pensant");
        int tempsAleatori = (int) (Math.random() * (2000 - 1000 + 1) + 1000);
        Thread.sleep(tempsAleatori);
    }

    private void menjar() throws InterruptedException {
        boolean peutMenjar = false;
        
        while (!peutMenjar) {
            if (forquillaEsquerra.isEnUs()) {
                System.out.println("Filòsof: " + nom + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());
                forquillaEsquerra.setEnUs(false);

                if (forquillaDreta.isEnUs()) {
                    System.out.println("Filòsof: " + nom + " agafa la forquilla dreta " + forquillaDreta.getNumero());
                    forquillaDreta.setEnUs(false);
                    
                    System.out.println("Filòsof: " + nom + " menja");
                    int tempsMenjar = (int) (Math.random() * (2000 - 1000 + 1) + 1000);
                    Thread.sleep(tempsMenjar);
                    gana++;
                    
                    System.out.println("Filòsof: " + nom + " ha acabat de menjar");
                    forquillaDreta.setEnUs(true);
                    System.out.println("Filòsof: " + nom + " deixa la forquilla esquerra " + forquillaEsquerra.getNumero());
                    forquillaEsquerra.setEnUs(true);
                    System.out.println("Filòsof: " + nom + " gana=" + gana);
                    
                    peutMenjar = true;
                } else {
                  
                    forquillaEsquerra.setEnUs(true);
                    System.out.println("Filòsof: " + nom + " deixa l'esquerra(" + forquillaEsquerra.getNumero() + ") i espera (dreta ocupada)");
                    
                    int tempsEspera = (int) (Math.random() * (1000 - 500 + 1) + 500);
                    Thread.sleep(tempsEspera);
                }
            } else {
                System.out.println("Filòsof: " + nom + " espera l'esquerra(ocupada) " + forquillaEsquerra.getNumero());
                
                int tempsEspera = (int) (Math.random() * (1000 - 500 + 1) + 500);
                Thread.sleep(tempsEspera);
            }
        }
    }

    public int getGana() {
        return gana;
    }

    public String getNom() {
        return nom;
    }
}