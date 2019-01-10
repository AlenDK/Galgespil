package e.android.gaglespil;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.jinatonic.confetti.CommonConfetti;
import com.github.jinatonic.confetti.ConfettiManager;


public class Vinder_frag extends Fragment implements View.OnClickListener {

    DAO dao = new DAO();
    int score;
    String navn;
    TextView vindertext;
    EditText navnV;
    Button b1;
    MediaPlayer victorySong;
    private Animation animation;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vinder_layout, container, false);

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation);

        victorySong = MediaPlayer.create(getActivity(), R.raw.crabrave);
        victorySong.start();

        CommonConfetti.rainingConfetti(container, new int[]{Color.WHITE, Color.BLACK, Color.BLUE, Color.RED, Color.GREEN})
                .stream(10000);

        vindertext = view.findViewById(R.id.vinderText);
        navnV = view.findViewById(R.id.navnV);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            score = bundle.getInt("score");
            vindertext.setText("Du brugte " + bundle.getString("keys") + " antal forsøg!" + " \n"
                    + "Du gættede ordet: " + bundle.getString("ord") + " \n"
                    + "Din score blev: " + score);
        }

        b1 = (Button) view.findViewById(R.id.tilbageTilHighScore2);

        b1.setOnClickListener(this);

        b1.startAnimation(animation);


        return view;

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tilbageTilHighScore2:
                b1.startAnimation(animation);

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