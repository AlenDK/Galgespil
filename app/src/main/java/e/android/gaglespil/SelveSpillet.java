package e.android.gaglespil;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
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


    DAO dao = new DAO();
    GalgeLogik galgeLogik = new GalgeLogik();
    Dialoger dialog = new Dialoger();
    TextView ord, Forkert, point;
    int points, antalforsøg;
    EditText gæt;
    Button getKnap;
    ImageView billede;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selvespillet, container, false);

        /*
        new DownloadFilesTask().execute();
        */


        Forkert = view.findViewById(R.id.Forkert);
        ord = view.findViewById(R.id.textOrd);
        gæt = view.findViewById(R.id.edit);
        point = view.findViewById(R.id.point);
        point.setText("Score: " + points);


        billede = view.findViewById(R.id.imageView);
        ændreBillede();


        getKnap = (Button) view.findViewById(R.id.button) ;
        getKnap.setOnClickListener(this);

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
        } else if (galgeLogik.getBrugteBogstaver().contains(bogstav)) {
            gæt.setError("Bogstavet er gættet på");
            return;
        } else if (bogstav == "1") {
            gæt.setError("Du må kun gætte på bogstaver");
            return;
        }
        galgeLogik.gætBogstav(bogstav);
        updateForsøg();
        gæt.setText("");
        if(galgeLogik.erSidsteBogstavKorrekt() == true) {
            updatePoints(50);
        } else {
            updatePoints(-50);

        }
        gæt.setError(null);
            opdaterSkærm();
        }

    public void ændreBillede() {
        switch (galgeLogik.getAntalForkerteBogstaver()) {
            case 1: billede.setImageResource(R.drawable.forkert1);
                break;
            case 2: billede.setImageResource(R.drawable.forkert2);
                break;
            case 3: billede.setImageResource(R.drawable.forkert3);
                break;
            case 4: billede.setImageResource(R.drawable.forkert4);
                break;
            case 5: billede.setImageResource(R.drawable.forkert5);
                break;
            case 6: billede.setImageResource(R.drawable.forkert6);
                break;
            default: billede.setImageResource(R.drawable.galge);
        }

    }


    public void updateForsøg() {
        antalforsøg += 1;
        point.setText("" + antalforsøg);
    }
    public void updatePoints(int i) {
        points += i;
        point.setText("Score: " + points);
    }

    private class DownloadFilesTask extends AsyncTask  {

        protected Object doInBackground(Object... arg0) {
            try {
                galgeLogik.hentOrdFraDr();
                return "Virker";
            } catch (Exception e) {
                e.printStackTrace();
                return "fejl";
            }

        }

    }


    public void opdaterSkærm() {
        ændreBillede();
        ord.setText(galgeLogik.getSynligtOrd());
        Forkert.setText("" + galgeLogik.getBrugteBogstaver());

//dialog skal være her
        if(galgeLogik.erSpilletVundet()) {


            updatePoints(100);

            Bundle bundle = new Bundle();
            bundle.putString("keys", "" + antalforsøg);

            Vinder_frag vinderfraq = new Vinder_frag();
            vinderfraq.setArguments(bundle);

            galgeLogik.nulstil();
            points = 0;
            opdaterSkærm();

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, vinderfraq)
                    .addToBackStack(null)
                    .commit();
        }
        if(galgeLogik.erSpilletTabt()) {

            updatePoints(-100);

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
