package e.android.gaglespil;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SelveSpillet extends Fragment implements View.OnClickListener {


    DAO dao = new DAO();
    GalgeLogik galgeLogik = new GalgeLogik();
    Dialoger dialog = new Dialoger();
    TextView ord, Forkert;
    EditText gæt;
    Button getKnap;
    ImageView billede;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selvespillet, container, false);

        Forkert = view.findViewById(R.id.Forkert);
        ord = view.findViewById(R.id.textOrd);
        gæt = view.findViewById(R.id.edit);

        billede = view.findViewById(R.id.imageView);
        ændreBillede();


        ord.setText(galgeLogik.getSynligtOrd());

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
        }
        galgeLogik.gætBogstav(bogstav);
        gæt.setText("");
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




    public void opdaterSkærm() {
        ændreBillede();
        ord.setText(galgeLogik.getSynligtOrd());
        Forkert.setText("" + galgeLogik.getBrugteBogstaver());

//dialog skal være her


        if(galgeLogik.erSpilletVundet()) {
            String id = dao.pushBruger();
            String name = "Ll";
            int score = 10;
            dao.nyHighScore(id , name, score);

            galgeLogik.nulstil();
            opdaterSkærm();
        }
        if(galgeLogik.erSpilletTabt()) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new Taber_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }


}
