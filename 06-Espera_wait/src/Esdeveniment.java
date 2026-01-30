import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private List<Assistent> assistents;
    private int placesDisponibles;

    public Esdeveniment(int placesMaxim) {
        this.assistents = new ArrayList<>();
        this.placesDisponibles = placesMaxim;
    }

    public synchronized void ferReseva(Assistent assistent) {
        while (placesDisponibles == 0) {
            try {
                System.out.println(assistent.getNom() + " esperant places disponibles...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        assistents.add(assistent);
        placesDisponibles--;
        System.out.println(assistent.getNom() + " ha fet reserva. Places disponibles: " + placesDisponibles);
    }

    public synchronized void cancelaReseva(Assistent assistent) {
        if (assistents.contains(assistent)) {
            assistents.remove(assistent);
            placesDisponibles++;
            System.out.println(assistent.getNom() + " ha cancel·lat la reserva. Places disponibles: " + placesDisponibles);
            notifyAll();
        }
    }

    public synchronized int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public synchronized List<Assistent> getAssistents() {
        return new ArrayList<>(assistents);
    }
}
