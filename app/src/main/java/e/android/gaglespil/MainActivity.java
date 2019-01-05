package e.android.gaglespil;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.text.DecimalFormat;

public class MainActivity extends Fragment implements View.OnClickListener {

    SharedPreferences prefs;
    TextView procentVundet;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);


        procentVundet = (TextView) view.findViewById(R.id.procentVundet);

        Button b1 = (Button) view.findViewById(R.id.hjælp);
        Button b2 = (Button) view.findViewById(R.id.Spil);
        Button b3 = (Button) view.findViewById(R.id.highscore);
        Button b4 = (Button) view.findViewById(R.id.multiplayer);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        double antalV = (double) prefs.getInt("RoundV", 0);
        double antal = (double) prefs.getInt("Round", 0);
        double procentV = antalV / antal * 100;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        procentVundet.setText("Du har vundet " + prefs.getInt("RoundV", 0) + " spil ud af " + prefs.getInt("Round", 0) + " \n"
                + "Din winrate er på: " + df.format(procentV));

        return view;
    }

    @Override
    public void onClick(View view) {

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


}
