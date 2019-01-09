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

    GalgeLogik galgeLogik = new GalgeLogik();
    ListView liste;
    String clickOrd;
    VælgOrdList adapter, test;
    SelveSpillet spillet = new SelveSpillet();
    List<String> ordListe = galgeLogik.muligeOrd;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vaelgord_layout, container, false);

        /*
        for (int i = 0; i < galgeLogik.muligeOrd.size(); i++ ) {
            ordListe = galgeLogik.muligeOrd;
        }
        */


        ordListe = getArrayList("muligeord");

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

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = prefs.getString(key,null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }


}
