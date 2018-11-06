package e.android.gaglespil;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Vinder_frag extends Fragment implements View.OnClickListener {

    SelveSpillet spil = new SelveSpillet();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vinder_layout, container, false);


        Button b1 = (Button) view.findViewById(R.id.tilbageTilHighScore2);

        b1.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tilbageTilHighScore2:

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new HighScore())
                        .addToBackStack(null)
                        .commit();
                break;
        }

    }

}