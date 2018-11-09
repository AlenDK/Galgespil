package e.android.gaglespil;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Vinder_frag extends Fragment implements View.OnClickListener {

    DAO dao = new DAO();
    Bruger bruger = new Bruger();
    int score;
    String navn;
    TextView antalforsøg, scoreV;
    EditText navnn;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vinder_layout, container, false);


        antalforsøg = view.findViewById(R.id.antalforsøg);
        scoreV = view.findViewById(R.id.scoreV);
        navnn = view.findViewById(R.id.navnn);



        Bundle bundle = this.getArguments();
        if(bundle!= null) {
            score = bundle.getInt("score");
            antalforsøg.setText("Du brugte " + bundle.getString("keys") + " antal forsøg!" + " \n"
                    + "Du gættede ordet: " + bundle.getString("ord") + " \n"
                    +  "Din score blev: " + score );

        }





        Button b1 = (Button) view.findViewById(R.id.tilbageTilHighScore2);



        b1.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tilbageTilHighScore2:

                if(navnn.getText().toString().length() == 0) {
                    navnn.setError("Indtast et navn!");
                    return;
                } else {
                    navn = navnn.getText().toString();
                }

                dao.nyHighScore(dao.pushBruger(),navn, score );

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new HighScore())
                        .commit();
                break;
        }

    }

}