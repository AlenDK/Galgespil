package e.android.gaglespil;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends Fragment implements View.OnClickListener {

    GalgeLogik galgeLogik = new GalgeLogik();
    SharedPreferences prefs ;
    TextView procentVundet;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);


        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());





        procentVundet = (TextView) view.findViewById(R.id.procentVundet);

        Button b1 = (Button) view.findViewById(R.id.hjælp);
        Button b2 = (Button) view.findViewById(R.id.Spil);
        Button b3 = (Button) view.findViewById(R.id.highscore);
        Button b4 = (Button) view.findViewById(R.id.multiplayer);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

        double antalV = (double) prefs.getInt("RoundV", 0);
        double antal = (double) prefs.getInt("Round", 0);
        double procentV = antalV / antal * 100;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        procentVundet.setText("Du har vundet " + prefs.getInt("RoundV", 0) + " spil ud af " + prefs.getInt("Round", 0) + " \n"
                + "Din winrate er på: " + df.format(procentV));



/*
        if (getArrayList("muligeord").size() < 1) {
            Log.d("test", "lillebitte");
        } else {
            Log.d("test", "størst");
        }
*/


        return view;
    }

    @Override
    public void onClick(View view) {

        FragmentManager fragManager = getFragmentManager();
        fragManager.popBackStack();


        switch (view.getId()) {
            case R.id.hjælp:

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new Hjælp())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.Spil:

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new SelveSpillet())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.highscore:

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new HighScore())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.multiplayer:

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new VælgOrd())
                        .addToBackStack(null)
                        .commit();
                break;

        }

    }



    private class AsyncTaskBackground extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
     /*       progressBar.setVisibility(View.VISIBLE);
            gæt.setVisibility(View.INVISIBLE);
            getKnap.setVisibility(View.INVISIBLE);
            ord.setVisibility(View.INVISIBLE);
            point.setVisibility(View.INVISIBLE);
            billede.setVisibility(View.INVISIBLE);
            Forkert.setVisibility(View.INVISIBLE); */
        }

        protected Object doInBackground(Object... arg0) {

            try {
                galgeLogik.hentOrdFraDr();
                return "Du har hentet ord fra DR";

            } catch (Exception e) {
                e.printStackTrace();
                return "Der er sket en fejl, mens der er blevet hentet ord fra DR";
            }
        }

        @Override
        protected void onPostExecute(Object arg0) {
            galgeLogik.nulstil();
        }

    }










}
