package e.android.gaglespil;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {
  /*  SelveSpillet spil = new SelveSpillet();
    DAO dao = new DAO();
    ListView brugerlist;

    List<Bruger> brugerliste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);


        brugerlist = (ListView) findViewById(R.id.brugerlist);

        brugerliste = new ArrayList<>();

        @Override
        public void onStart() {
            super.onStart();

            dao.mDatabase().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot brugerSnapshot : dataSnapshot.getChildren()) {
                        Bruger bruger = brugerSnapshot.getValue(Bruger.class);

                        brugerliste.add((bruger));

                    }

                    BrugerList adapter = new BrugerList(HighScoreActivity, brugerliste);
                    brugerlist.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });



        }
    }

*/
}



