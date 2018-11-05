package e.android.gaglespil;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HighScore extends Fragment {
    private static final String TAG = "Highscore";

    DAO dao = new DAO();
    ListView brugerlist;

    BrugerList adapter;

    List<Bruger> brugerliste;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.highscore, container, false);

        brugerlist = view.findViewById(R.id.brugerlist);


        brugerlist.setAdapter(adapter);

        adapter = new BrugerList(getActivity(), brugerliste);

        brugerliste = new ArrayList<>();

        Log.d(TAG, "create " + brugerliste.size());

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        brugerliste.add(new Bruger("fff", "Alen", 500));
        brugerliste.add(new Bruger("qqq", "sda", 33));
        brugerliste.add(new Bruger("caq", "LP", 3333));
        adapter.notifyDataSetChanged();

        Log.d(TAG, "onsStart " + brugerliste.size());

/*
        dao.mDatabase().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot brugerSnapshot : dataSnapshot.getChildren()) {
                    Bruger bruger = brugerSnapshot.getValue(Bruger.class);

                    brugerliste.add((bruger));

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


*/

    }
}
