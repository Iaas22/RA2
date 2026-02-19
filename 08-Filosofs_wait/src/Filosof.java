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

    public void menjar() {
        try {
            System.out.println(nom + " està menjant");
            int temps = (int) (Math.random() * 1000 + 1000); // 1s a 2s
            Thread.sleep(temps);
            System.out.println(nom + " ha acabat de menjar");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pensar() {
        try {
            System.out.println(nom + " està pensant");
            int temps = (int) (Math.random() * 1000 + 1000); // 1s a 2s
            Thread.sleep(temps);
            System.out.println(nom + " ha acabat de pensar");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void agafarForquillaEsquerra() throws InterruptedException {
        synchronized (forquillaEsquerra) {
            while (forquillaEsquerra.getPropietari() != Forquilla.LLIURE) {
                forquillaEsquerra.wait();
            }
            forquillaEsquerra.setPropietari(Integer.parseInt(nom.substring(3)));
            System.out.println(nom + " ha agafat la forquilla esquerra " + forquillaEsquerra.getNumero());
        }
    }

    public void agafarForquillaDreta() throws InterruptedException {
        synchronized (forquillaDreta) {
            while (forquillaDreta.getPropietari() != Forquilla.LLIURE) {
                forquillaDreta.wait();
            }
            forquillaDreta.setPropietari(Integer.parseInt(nom.substring(3)));
            System.out.println(nom + " ha agafat la forquilla dreta " + forquillaDreta.getNumero());
        }
    }

    public void agafarForquilles() throws InterruptedException {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
    }

    public void deixarForquillaEsquerra() {
        synchronized (forquillaEsquerra) {
            forquillaEsquerra.setPropietari(Forquilla.LLIURE);
            System.out.println(nom + " ha deixat la forquilla esquerra " + forquillaEsquerra.getNumero());
            forquillaEsquerra.notifyAll();
        }
    }

    public void deixarForquillaDreta() {
        synchronized (forquillaDreta) {
            forquillaDreta.setPropietari(Forquilla.LLIURE);
            System.out.println(nom + " ha deixat la forquilla dreta " + forquillaDreta.getNumero());
            forquillaDreta.notifyAll();
        }
    }

    public void deixarForquilles() {
        deixarForquillaEsquerra();
        deixarForquillaDreta();
    }

    @Override
    public void run() {
        while (true) {
            try {
                pensar();
                agafarForquilles();
                menjar();
                deixarForquilles();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

