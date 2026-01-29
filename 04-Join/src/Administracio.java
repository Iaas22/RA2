public class Administracio {
    private int num_poblacio_activa;
    private Treballador[] poblacio_activa;

    public Administracio() {
        num_poblacio_activa = 50;
        poblacio_activa = new Treballador[50];
        
        for (int i = 0; i < num_poblacio_activa; i++) {
            poblacio_activa[i] = new Treballador(
                25000.0f,
                20,
                65,
                "Ciutada" + i
            );
        }
    }

    public static void main(String[] args) {
        Administracio admin = new Administracio();
        
        for (Treballador treballador : admin.poblacio_activa) {
            treballador.start();
        }
        
        for (Treballador treballador : admin.poblacio_activa) {
            try {
                treballador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        for (Treballador treballador : admin.poblacio_activa) {
            System.out.printf("%s -> edat: %d / total: %.2f%n",
                treballador.getName(),
                treballador.getEdat(),
                treballador.getCobrat()
            );
        }
    }

}
