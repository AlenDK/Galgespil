package e.android.gaglespil;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScore extends Fragment implements View.OnClickListener {
    private static final String TAG = "Highscore";

    DAO dao = new DAO();
    ListView liste;
    BrugerList adapter;
    ProgressBar progressBar;
    Button menu;
    List<Bruger> brugerliste;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.highscore, container, false);

        menu = (Button) view.findViewById(R.id.menu);
        menu.setOnClickListener(this);

        brugerliste = new ArrayList<>();
        progressBar = view.findViewById(R.id.progress);

        liste = view.findViewById(R.id.liste);

        adapter = new BrugerList(getActivity(), brugerliste);

        liste.setAdapter(adapter);

        new AsyncTaskBackground().execute();

        Log.d(TAG, "create " + brugerliste.size());

        return view;

    }

    @Override
    public void onClick(View view) {

        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new MainActivity())
                .addToBackStack(null)
                .commit();


    }


    private class AsyncTaskBackground extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            liste.setVisibility(View.INVISIBLE);
        }

        protected Object doInBackground(Object... arg0) {
            try {
                dao.mDatabase().addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot brugerSnapshot : dataSnapshot.getChildren()) {
                            Bruger bruger = brugerSnapshot.getValue(Bruger.class);

                            brugerliste.add((bruger));
                            Collections.sort(brugerliste);

                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
                return "HighScore listen er loaded";
            } catch (Exception e) {
                e.printStackTrace();
                return "Noget gik galt";
            }
        }

        @Override
        protected void onPostExecute(Object arg0) {
            progressBar.setVisibility(View.INVISIBLE);
            liste.setVisibility(View.VISIBLE);
        }

    }


}
