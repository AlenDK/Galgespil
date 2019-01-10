package e.android.gaglespil;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VælgOrd extends Fragment implements View.OnClickListener {

    GalgeLogik galgeLogik = GalgeLogik.getInstance();
    ListView liste;
    String clickOrd;
    VælgOrdList adapter;
    List<String> ordListe;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vaelgord_layout, container, false);

        if (galgeLogik.getArrayList("muligeord", getActivity()) == null) {
            ordListe = galgeLogik.muligeOrd;
        } else {
            ordListe = galgeLogik.getArrayList("muligeord", getActivity());
            ordListe.remove(0);
        }

        liste = view.findViewById(R.id.listeOrd);

        adapter = new VælgOrdList(getActivity(), ordListe);

        liste.setAdapter(adapter);

        return view;

    }

    @Override
    public void onClick(View view) {

        Log.d("hejsa", clickOrd);

    }

}
