package e.android.gaglespil;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Hjælp extends Fragment implements View.OnClickListener {
    private static final String TAG = "Hjælp";

    GalgeLogik galgeLogik = GalgeLogik.getInstance();
    ProgressBar progressBar;
    TextView tekst, hentDR;
    Button b1, b2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hjaelp, container, false);

        b1 = view.findViewById(R.id.hentOrd);
        b2 = view.findViewById(R.id.menu);
        tekst = view.findViewById(R.id.regler);
        progressBar = view.findViewById(R.id.progress);
        hentDR = view.findViewById(R.id.drtext);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        tekst.setText("Her er regelsættet for mit gaglespil: " + " \n"
                + "Hvis du gætter et rigtigt bogstav får du 10 points " + " \n"
                + "Hvis du gætter et forkert bogstav får du -5 points " + " \n"
                + "Hvis du gætter ordet får du 25 points " + " \n"
                + "Hvis du ikke kan gætte ordet får du -25 points " + " \n"
                + "Ordene bliver hentet fra https://dr.dk " + " \n"
                + "Efter du er færdig med et spil kan du skrive dit navn eller id, og se hvor du ligger på highscore!" + "\n");

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.menu:
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new MainActivity())
                        .commit();
                break;

            case R.id.hentOrd:
                new AsyncTaskBackground().execute();
                break;
        }
    }

    private class AsyncTaskBackground extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            hentDR.setVisibility(View.VISIBLE);
            tekst.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
        }

        protected Object doInBackground(Object... arg0) {
            try {
                galgeLogik.hentOrdFraDr();
                return "Du har hentet ord fra DR";
            } catch (Exception e) {
                e.printStackTrace();
                return "Der er sket en fejl, mens der er blevet hentet ord fra DR";
            }
        }

        @Override
        protected void onPostExecute(Object arg0) {
            progressBar.setVisibility(View.INVISIBLE);
            hentDR.setVisibility(View.INVISIBLE);
            tekst.setVisibility(View.VISIBLE);
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);

            galgeLogik.nulstil();
            galgeLogik.saveArrayList(galgeLogik.muligeOrd, "muligeord", getActivity());
        }
    }
}
