public class Filosof extends Thread {
    private String nom;
    private int id;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;

    public Filosof(int numero, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        this.id = numero;
        this.nom = "fil" + numero;
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        this.gana = 0;
    }

    public void menjar() {
        try {
            System.out.println(nom + " està menjant");
            int temps = (int) (Math.random() * 1000 + 1000); 
            Thread.sleep(temps);
            System.out.println(nom + " ha acabat de menjar");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pensar() {
        try {
            System.out.println(nom + " està pensant");
            int temps = (int) (Math.random() * 1000 + 1000); 
            Thread.sleep(temps);
            System.out.println(nom + " ha acabat de pensar");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int tempsEntreIntents() {
        return (int) (Math.random() * 500 + 500);
    }

    private void esperarEnForquilla(Forquilla forquilla) throws InterruptedException {
        synchronized (forquilla) {
            forquilla.wait(tempsEntreIntents());
        }
    }

    private boolean intentarAgafarForquilla(Forquilla forquilla) {
        synchronized (forquilla) {
            if (forquilla.getPropietari() == Forquilla.LLIURE) {
                forquilla.setPropietari(id);
                return true;
            }
            return false;
        }
    }

    public boolean agafarForquillaEsquerra() {
        boolean agafada = intentarAgafarForquilla(forquillaEsquerra);
        if (agafada) {
            System.out.println(nom + " ha agafat la forquilla esquerra " + forquillaEsquerra.getNumero());
        }
        return agafada;
    }

    public boolean agafarForquillaDreta() {
        boolean agafada = intentarAgafarForquilla(forquillaDreta);
        if (agafada) {
            System.out.println(nom + " ha agafat la forquilla dreta " + forquillaDreta.getNumero());
        }
        return agafada;
    }

    public void agafarForquilles() throws InterruptedException {
        while (true) {
            if (agafarForquillaEsquerra()) {
                if (agafarForquillaDreta()) {
                    return;
                }
                deixarForquillaEsquerra();
                gana++;
                esperarEnForquilla(forquillaDreta);
            } else {
                gana++;
                esperarEnForquilla(forquillaEsquerra);
            }
        }
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

    public int getGana() {
        return gana;
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

