package e.android.gaglespil;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Vinder_frag extends Fragment implements View.OnClickListener {

    DAO dao = new DAO();
    int score;
    String navn;
    TextView vindertext;
    EditText navnV;
    MediaPlayer victorySong;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vinder_layout, container, false);


        victorySong=MediaPlayer.create(getActivity(), R.raw.crabrave);

        victorySong.start();

        vindertext = view.findViewById(R.id.vinderText);
        navnV = view.findViewById(R.id.navnV);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            score = bundle.getInt("score");
            vindertext.setText("Du brugte " + bundle.getString("keys") + " antal forsøg!" + " \n"
                    + "Du gættede ordet: " + bundle.getString("ord") + " \n"
                    + "Din score blev: " + score);
        }

        Button b1 = (Button) view.findViewById(R.id.tilbageTilHighScore2);

        b1.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tilbageTilHighScore2:

                if (navnV.getText().toString().length() == 0) {
                    navnV.setError("Indtast et navn!");
                    return;
                } else {
                    navn = navnV.getText().toString();
                }

                dao.nyHighScore(dao.pushBruger(), navn, score);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new HighScore())
                        .commit();
                break;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        victorySong.release();
    }



}