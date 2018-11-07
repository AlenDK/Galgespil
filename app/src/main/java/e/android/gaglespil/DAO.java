package e.android.gaglespil;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAO {


    private DatabaseReference mDatabase;


    public DAO() {
        mDatabase = FirebaseDatabase.getInstance().getReference("brugere");

    }

    public DatabaseReference mDatabase() {
        return mDatabase;
    }

    public void nyHighScore(String id, String name, int score) {
        Bruger bruger = new Bruger(id, name, score);

        mDatabase.child(id).setValue(bruger);
    }

    public String pushBruger() {
        return mDatabase.push().getKey();
    }

}
