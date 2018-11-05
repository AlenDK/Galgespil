package e.android.gaglespil;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class HighScore extends Fragment {
    private static final String TAG = "Highscore";

    SelveSpillet spil = new SelveSpillet();
    ListView brugerlist;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.highscore, container, false);


        brugerlist = (ListView) view.findViewById(R.id.brugerlist);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();





    }
}
