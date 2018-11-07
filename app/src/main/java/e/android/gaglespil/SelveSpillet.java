package e.android.gaglespil;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;


public class SelveSpillet extends Fragment implements View.OnClickListener {


    SharedPreferences prefs;

    DAO dao = new DAO();
    GalgeLogik galgeLogik = new GalgeLogik();
    Dialoger dialog = new Dialoger();
    TextView ord, Forkert, point, hentDR;
    int points, antalforsøg, gennemførtV, gennemført;
    EditText gæt;
    Button getKnap;
    ImageView billede;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selvespillet, container, false);


        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());


        progressBar = view.findViewById(R.id.progress);
        Forkert = view.findViewById(R.id.Forkert);
        ord = view.findViewById(R.id.textOrd);
        gæt = view.findViewById(R.id.edit);
        point = view.findViewById(R.id.point);
        hentDR = view.findViewById(R.id.drtext);

        billede = view.findViewById(R.id.imageView);

        getKnap = (Button) view.findViewById(R.id.button);
        getKnap.setOnClickListener(this);

        /*
        new AsyncTaskBackground().execute();
*/

        return view;
    }


    @Override
    public void onClick(View view) {
        if (galgeLogik.erSpilletSlut() == true) {
            getKnap.setText("Spil");
            galgeLogik.nulstil();
        }

        getKnap.setText("Gæt");
        String bogstav = gæt.getText().toString();
        if (bogstav.length() != 1) {
            gæt.setError("Skriv et bogstav");
            return;
        } else if (galgeLogik.getBrugteBogstaver().contains(bogstav.toLowerCase()) || galgeLogik.getBrugteBogstaver().contains(bogstav.toUpperCase())) {
            gæt.setError("Bogstavet er gættet på");
            return;
        }
        galgeLogik.gætBogstav(bogstav);
        updateForsøg();
        gæt.setText("");
        if (galgeLogik.erSidsteBogstavKorrekt() == true) {
            updatePoints(50);
        } else {
            updatePoints(-50);

        }
        gæt.setError(null);
        opdaterSkærm();
    }

    public void ændreBillede() {
        switch (galgeLogik.getAntalForkerteBogstaver()) {
            case 1:
                billede.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                billede.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                billede.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                billede.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                billede.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                billede.setImageResource(R.drawable.forkert6);
                break;
            default:
                billede.setImageResource(R.drawable.galge);
        }

    }


    public void updateForsøg() {
        antalforsøg += 1;
    }

    public void updatePoints(int i) {
        points += i;
        point.setText("Score: " + points);
    }

    public void updateGennemført() {
        gennemførtV = prefs.getInt("RoundV", 0);
        gennemførtV += 1;
        prefs.edit().putInt("RoundV", gennemførtV).commit();
    }

    public void updateSpil() {
        gennemført = prefs.getInt("Round", 0);
        gennemført += 1;
        prefs.edit().putInt("Round", gennemført).commit();
    }


    private class AsyncTaskBackground extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            gæt.setVisibility(View.INVISIBLE);
            getKnap.setVisibility(View.INVISIBLE);
            ord.setVisibility(View.INVISIBLE);
            point.setVisibility(View.INVISIBLE);
            billede.setVisibility(View.INVISIBLE);
            Forkert.setVisibility(View.INVISIBLE);
        }

        protected Object doInBackground(Object... arg0) {
            try {
                galgeLogik.hentOrdFraDr();
                return "Virker";
            } catch (Exception e) {
                e.printStackTrace();
                return "fejl";
            }
        }

        @Override
        protected void onPostExecute(Object arg0) {
            progressBar.setVisibility(View.INVISIBLE);
            gæt.setVisibility(View.VISIBLE);
            getKnap.setVisibility(View.VISIBLE);
            hentDR.setVisibility(View.INVISIBLE);
            ord.setVisibility(View.VISIBLE);
            point.setVisibility(View.VISIBLE);
            billede.setVisibility(View.VISIBLE);
            Forkert.setVisibility(View.VISIBLE);


            galgeLogik.nulstil();
            ord.setText(galgeLogik.getSynligtOrd());
            point.setText("Score: " + points);
            ændreBillede();
        }

    }


    public void opdaterSkærm() {
        ændreBillede();
        ord.setText(galgeLogik.getSynligtOrd());
        Forkert.setText("" + galgeLogik.getBrugteBogstaver());

//dialog skal være her
        if (galgeLogik.erSpilletVundet()) {

            updatePoints(100);


            updateSpil();
            updateGennemført();

            Bundle bundle = new Bundle();
            bundle.putInt("score", points);
            bundle.putString("keys", "" + antalforsøg);

            Vinder_frag vinderfraq = new Vinder_frag();
            vinderfraq.setArguments(bundle);

            galgeLogik.nulstil();
            antalforsøg = 0;
            points = 0;
            opdaterSkærm();

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, vinderfraq)
                    .addToBackStack(null)
                    .commit();
        }
        if (galgeLogik.erSpilletTabt()) {

            updatePoints(-100);
            updateSpil();

            Bundle bundle = new Bundle();
            bundle.putString("ord", galgeLogik.getOrdet());
            bundle.putInt("score", points);

            Taber_frag taberfraq = new Taber_frag();
            taberfraq.setArguments(bundle);

            galgeLogik.nulstil();
            points = 0;

            opdaterSkærm();

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, taberfraq)
                    .addToBackStack(null)
                    .commit();
        }
    }


}
