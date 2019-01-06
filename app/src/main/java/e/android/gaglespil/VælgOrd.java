package e.android.gaglespil;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class VælgOrd extends Fragment implements View.OnClickListener {

    GalgeLogik galgeLogik = new GalgeLogik();
    ListView liste;
    String clickOrd;
    VælgOrdList adapter, test;
    List<String> ordListe = galgeLogik.muligeOrd;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vaelgord_layout, container, false);

        /*
        for (int i = 0; i < galgeLogik.muligeOrd.size(); i++ ) {
            ordListe = galgeLogik.muligeOrd;
        }
        */

        liste = view.findViewById(R.id.listeOrd);

        adapter = new VælgOrdList(getActivity(), ordListe);

        liste.setAdapter(adapter);

        return view;

    }

    @Override
    public void onClick(View view) {

        Log.d("hejsa", clickOrd);

/*
        Bundle bundle = new Bundle();
        bundle.putString("nytord", String.valueOf(liste.getPositionForView(view)));

        SelveSpillet selveSpillet = new SelveSpillet();
        selveSpillet.setArguments(bundle);
  */
    }
}
