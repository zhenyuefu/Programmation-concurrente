package Garage;

public class PoolHangars {
    private final Hangar[] pool;

    public PoolHangars(int nbHangar) {
        pool = new Hangar[nbHangar];
        for (int i = 0; i < nbHangar; i++) {
            pool[i] = new Hangar();
        }
    }

    public int chercherDispo() {
        for (Hangar hangar : pool) {
            if (hangar.isFree()) {
                return hangar.getNumero();
            }
        }
        return 0;
    }

    public void entrer(int numHangar, int idLoco) {
        pool[numHangar].entrer(idLoco);
    }
}
