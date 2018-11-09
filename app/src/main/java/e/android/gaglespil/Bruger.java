package e.android.gaglespil;

import android.support.annotation.NonNull;

public class Bruger implements Comparable<Bruger> {

    public String id;
    public String navn;
    public long score;

    public Bruger() {

    }

    public Bruger(String id, String navn, long score) {
        this.id = id;
        this.navn = navn;
        this.score = score;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(@NonNull Bruger bruger) {

        if (this.score > bruger.score) {
            return -1;
        } else if (this.score < bruger.score) {
            return 1;
        } else {
            return 0;
        }
    }
}
