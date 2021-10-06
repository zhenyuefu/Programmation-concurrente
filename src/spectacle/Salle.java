package spectacle;

public class Salle {
    private final int nbRangs;
    private final int nbPlacesParRang;
    private int nbPlacesLibres;
    private boolean[][] placesLibres;

    public Salle(final int nbRangs, final int nbPlacesParRang) {
        this.nbRangs = nbRangs;
        this.nbPlacesParRang = nbPlacesParRang;
        this.nbPlacesLibres = nbRangs * nbPlacesParRang;
        placesLibres = new boolean[nbRangs][nbPlacesParRang];
        for (int i = 0; i < nbRangs; i++) {
            for (int j = 0; j < nbPlacesParRang; j++) {
                placesLibres[i][j] = true;
            }
        }
    }

    public synchronized boolean capaciteOK(final int i) {
        System.out.println("nbPlaces:" + nbPlacesLibres);
        if (i <= nbPlacesLibres) {
            return true;
        }
        return false;
    }

    /**
     * renvoie -1 s’il n’y pas n places libres cote a cote au rang i. Sinon, renvoie
     * la position j qui est la premiere du bloc de n places libres au rang i
     */
    public synchronized int nContiguesAuRangI(final int n, final int i) {
        int nbPlaces = 0;
        for (int j = 0; j < nbPlacesParRang; j++) {
            if (placesLibres[i][j])
                nbPlaces++;
            else
                nbPlaces = 0;
            if (nbPlaces == n)
                return j - n;
        }
        return -1;
    }

    public synchronized boolean reserverContigues(int n) {
        if (!capaciteOK(n))
            return false;
        for (int i = 0; i < nbRangs; i++) {
            final int pos = nContiguesAuRangI(n, i);
            if (pos != -1) {
                nbPlacesLibres -= n;
                for (int j = pos; n >= 0; j--, n--) {
                    placesLibres[i][j] = false;
                }
                return true;
            }
        }

        return false;
    }

    public synchronized boolean reserver(int n) {
        if (!capaciteOK(n))
            return false;
        if (!reserverContigues(n)) {
            for (int i = 0; i < nbRangs; i++) {
                for (int j = 0; j < nbPlacesParRang; j++) {
                    if (placesLibres[i][j]) {
                        n--;
                        placesLibres[i][j] = false;
                        nbPlacesLibres--;
                        if (n == 0)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nbRangs; i++) {
            for (int j = 0; j < nbPlacesParRang; j++) {
                sb.append(placesLibres[i][j] ? "x" : "o");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
