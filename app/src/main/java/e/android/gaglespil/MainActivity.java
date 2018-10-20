package e.android.gaglespil;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.activity_main, container, false);




        Button b1 = (Button) rod.findViewById(R.id.hjælp);
        Button b2 = (Button) rod.findViewById(R.id.Spil);
        Button b3 = (Button) rod.findViewById(R.id.highscore);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

     return rod;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.hjælp:

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new Hjælp())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.Spil:

                Intent intent2 = new Intent(getActivity(), SelveSpillet.class);
                startActivity(intent2);
                break;

            case R.id.highscore:

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new HighScore())
                        .addToBackStack(null)
                        .commit();
                break;

        }

    }

}
