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

public class Taber_frag extends Fragment implements View.OnClickListener {

    DAO dao = new DAO();
    SelveSpillet spil = new SelveSpillet();
    TextView taberOrd, scoreT;
    EditText navnn;
    int score;
    String navn;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.taber_layout, container, false);


        taberOrd = view.findViewById(R.id.tabteOrd);
        scoreT = view.findViewById(R.id.scoreT);
        navnn = view.findViewById(R.id.navnnn);


        Bundle bundle = this.getArguments();
        if(bundle!= null) {
            taberOrd.setText(bundle.getString("ord"));
            score = bundle.getInt("score");
             scoreT.setText("Din score blev:" + score);
        }

        Button b1 = (Button) view.findViewById(R.id.tilbageTilHighScore1);

        b1.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tilbageTilHighScore1:

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