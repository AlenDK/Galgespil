package e.android.gaglespil;

public class Bruger {

    public String id;
    public String navn;
    public int score;

    public Bruger() {

    }

    public Bruger(String id, String navn, int score) {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
