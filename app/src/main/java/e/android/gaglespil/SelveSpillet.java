package e.android.gaglespil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SelveSpillet extends AppCompatActivity implements View.OnClickListener {

    GalgeLogik galgeLogik = new GalgeLogik();
    Dialoger dialog = new Dialoger();
    TextView ord, Forkert;
    EditText gæt;
    Button getKnap;


    public void ændreBillede() {
        ImageView billede = findViewById(R.id.imageView);

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

    public void Dialog() {
    dialog.show(getSupportFragmentManager(), "Dialog");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selvespillet);

        Forkert = findViewById(R.id.Forkert);
        ord = findViewById(R.id.textOrd);
        gæt = findViewById(R.id.edit);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


        ord.setText(galgeLogik.getSynligtOrd());

        getKnap = (Button) findViewById(R.id.button) ;
        getKnap.setOnClickListener(this);
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


    public void opdaterSkærm() {
        ændreBillede();
        ord.setText(galgeLogik.getSynligtOrd());
        Forkert.setText("" + galgeLogik.getBrugteBogstaver());



        if(galgeLogik.erSpilletVundet()) {
            dialog.setTitel("Du har vundet!");
            dialog.setBesked("Ordet var " + galgeLogik.getOrdet() + ". Tryk på ok, for at prøve igen!" );
            Dialog();
            galgeLogik.nulstil();
            opdaterSkærm();
        }
        if(galgeLogik.erSpilletTabt()) {
            dialog.setTitel("Du har tabt!");
            dialog.setBesked("Ordet var " + galgeLogik.getOrdet() + ". Tryk på ok, for at prøve igen!" );
            Dialog();
            galgeLogik.nulstil();
            opdaterSkærm();
        }
    }


}
