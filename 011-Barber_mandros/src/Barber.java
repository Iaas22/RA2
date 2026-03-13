public class Barber extends Thread {
    private final Barberia barberia;
    private final Object condBarber;

    public Barber(String nom, Barberia barberia) {
        super(nom);
        this.barberia = barberia;
        this.condBarber = obtenirCondBarber(barberia);
    }

    private void executarTall(Client client) throws InterruptedException {
        System.out.println(getName() + " talla els cabells a " + client.getNom());
        Thread.sleep(900 + (long) (Math.random() * 100));
        System.out.println(getName() + " acaba amb " + client.getNom());
        client.tallarseElCabell();
    }

    private Object obtenirCondBarber(Barberia barberia) {
        try {
            java.lang.reflect.Field camp = Barberia.class.getDeclaredField("condBarber");
            camp.setAccessible(true);
            return camp.get(barberia);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("No s'ha pogut accedir a condBarber", e);
        }
    }
     @Override
    public void run() {
        try {
            while (true) {
                Client client;
                synchronized (condBarber) {
                    client = barberia.seguentClient();
                    while (client == null) {
                        condBarber.wait();
                        client = barberia.seguentClient();
                    }
                }

                executarTall(client);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}