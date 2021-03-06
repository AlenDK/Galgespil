package e.android.gaglespil;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;


public class SelveSpillet extends Fragment implements View.OnClickListener {


    SharedPreferences prefs;

    GalgeLogik galgeLogik = new GalgeLogik();
    TextView ord, Forkert, point, hentDR;
    int score, antalforsøg, gennemførtV, gennemført;
    EditText gæt;
    Button getKnap;
    ImageView billede;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selvespillet, container, false);


        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());


        progressBar = view.findViewById(R.id.progress);
        Forkert = view.findViewById(R.id.forkert);
        ord = view.findViewById(R.id.textOrd);
        gæt = view.findViewById(R.id.edit);
        point = view.findViewById(R.id.point);
        hentDR = view.findViewById(R.id.drtext);
        billede = view.findViewById(R.id.imageView);

        getKnap = (Button) view.findViewById(R.id.button);
        getKnap.setOnClickListener(this);

        galgeLogik.nulstil();

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            galgeLogik.setOrdet(bundle.getString("valgtord"));
            galgeLogik.opdaterSynligtOrd();
            ord.setText(galgeLogik.getSynligtOrd());

        } else {
            galgeLogik.muligeOrd = galgeLogik.getArrayList("muligeord", getActivity());
            galgeLogik.muligeOrd.remove(0);
            galgeLogik.nulstil();
            ord.setText(galgeLogik.getSynligtOrd());
        }

        point.setText("Score: " + score);
        ændreBillede();

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
            updatePoints(10);
        } else {
            updatePoints(-5);

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
        score += i;
        point.setText("Score: " + score);
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

    public void opdaterSkærm() {
        ændreBillede();
        ord.setText(galgeLogik.getSynligtOrd());
        Forkert.setText("" + galgeLogik.getBrugteBogstaver());

        if (galgeLogik.erSpilletVundet()) {

            updatePoints(25);


            updateSpil();
            updateGennemført();

            Bundle bundle = new Bundle();
            bundle.putString("ord", galgeLogik.getOrdet());
            bundle.putInt("score", score);
            bundle.putString("keys", "" + antalforsøg);

            Vinder_frag vinderfraq = new Vinder_frag();
            vinderfraq.setArguments(bundle);

            galgeLogik.nulstil();
            antalforsøg = 0;
            score = 0;
            opdaterSkærm();

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, vinderfraq)
                    .commit();
        }
        if (galgeLogik.erSpilletTabt()) {

            updatePoints(-25);
            updateSpil();

            Bundle bundle = new Bundle();
            bundle.putString("ord", galgeLogik.getOrdet());
            bundle.putInt("score", score);

            Taber_frag taberfraq = new Taber_frag();
            taberfraq.setArguments(bundle);

            galgeLogik.nulstil();
            score = 0;

            opdaterSkærm();

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, taberfraq)
                    .commit();
        }
    }


}
