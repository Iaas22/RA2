import java.util.ArrayList;
import java.util.List;

public class Estanc implements Runnable {
    private List<Tabac> tabacs;
    private List<Llumi> llumis;
    private List<Paper> papers;
    private boolean tancat;

    public Estanc() {
        this.tabacs = new ArrayList<>();
        this.llumis = new ArrayList<>();
        this.papers = new ArrayList<>();
        this.tancat = false;
    }

    public synchronized void addTabac(Tabac t) {
        tabacs.add(t);
        System.out.println("Afegint tabac");
        notifyAll();
    }

    public synchronized void addLlumi(Llumi l) {
        llumis.add(l);
        System.out.println("Afegint Llumí");
        notifyAll();
    }

    public synchronized void addPaper(Paper p) {
        papers.add(p);
        System.out.println("Afegint Paper");
        notifyAll();
    }

    public synchronized Tabac venTabac() {
        if (!tabacs.isEmpty()) {
            return tabacs.remove(0);
        }
        return null;
    }

    public synchronized Paper venPaper() {
        if (!papers.isEmpty()) {
            return papers.remove(0);
        }
        return null;
    }

    public synchronized Llumi venLlumi() {
        if (!llumis.isEmpty()) {
            return llumis.remove(0);
        }
        return null;
    }

    public synchronized void tancarEstanc() {
        tancat = true;
    }

    public void nouSubministrament() {
        int opcio = (int) (Math.random() * 3);
        switch (opcio) {
            case 0:
                addTabac(new Tabac());
                break;
            case 1:
                addLlumi(new Llumi());
                break;
            case 2:
                addPaper(new Paper());
                break;
        }
    }

    @Override
    public void run() {
        System.out.println("Estanc obert");
        while (!tancat) {
            nouSubministrament();
            try {
                Thread.sleep((long) (500 + Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Estanc tancat");
    }
}
