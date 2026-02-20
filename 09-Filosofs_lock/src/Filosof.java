public class Filosof extends Thread {
    private String nom;
    private long inicíGana;
    private long fiGana;
    private long Gana;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;

    public Filosof(String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        this.nom = nom;
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        this.Gana = 0;
    }

    public void menjar() {
        fiGana = System.currentTimeMillis();
        try {
            System.out.println(nom + " està menjant");
            int temps = (int) (Math.random() * 1000 + 1000);
            Thread.sleep(temps);
            System.out.println(nom + " ha acabat de menjar");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void agafarForquilles() {
        forquillaEsquerra.agafar();
        System.out.println(nom + " ha agafat la forquilla esquerra");
        forquillaDreta.agafar();
        System.out.println(nom + " ha agafat la forquilla dreta");
    }

    public void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
    }

    public void agafarForquillaDreta() {
        forquillaDreta.agafar();
    }

    public void deixarForquilles() {
        forquillaDreta.deixar();
        System.out.println(nom + " ha deixat la forquilla dreta");
        forquillaEsquerra.deixar();
        System.out.println(nom + " ha deixat la forquilla esquerra");
    }

    public void pensar() {
        inicíGana = System.currentTimeMillis();
        try {
            System.out.println(nom + " està pensant");
            int temps = (int) (Math.random() * 1000 + 1000);
            Thread.sleep(temps);
            System.out.println(nom + " ha acabat de pensar");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long calcularGana() {
        return (fiGana - inicíGana) / 1000;
    }

    public void resetGana() {
        inicíGana = System.currentTimeMillis();
        Gana = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                resetGana();
                pensar();
                agafarForquilles();
                menjar();
                deixarForquilles();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

