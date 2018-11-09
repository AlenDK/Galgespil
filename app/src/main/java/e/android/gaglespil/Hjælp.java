package e.android.gaglespil;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class Hjælp extends Fragment {
    private static final String TAG = "Hjælp";

    TextView tekst;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.hjaelp, container, false);

       tekst = view.findViewById(R.id.regler);

    tekst.setText("Her er regelsættet for mit gaglespil: " + " \n"
            + "Hvis du gætter et rigtigt bogstav får du 10 points " + " \n"
            + "Hvis du gætter et forkert bogstav får du -5 points " + " \n"
            + "Hvis du gætter ordet får du 25 points " + " \n"
            + "Hvis du ikke kan gætte ordet får du -25 points " + " \n"
            + "Ordene bliver hentet fra https://dr.dk " + " \n"
            + "Efter du er færdig med et spil kan du skrive dit navn eller id, og se hvor du ligger på highscore!" + "\n");



        return view;
    }
}
