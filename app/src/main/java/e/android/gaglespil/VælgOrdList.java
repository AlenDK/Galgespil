package e.android.gaglespil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class VælgOrdList extends ArrayAdapter<String> implements View.OnClickListener {

    int i;
    GalgeLogik galgeLogik = new GalgeLogik();
    private Activity context;
    private List<String> ordList;
    Button wordView;

    public VælgOrdList(@NonNull Activity context, List<String> ordList) {
        super(context, R.layout.vaelordlist_layout, ordList);
        this.context = context;
        this.ordList = ordList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.vaelordlist_layout, null, true);

        wordView = (Button) listViewItem.findViewById(R.id.buttonWord);


        wordView.setOnClickListener(this);

        String liste = ordList.get(position);


        wordView.setText(liste);

        return listViewItem;

    }


    @Override
    public void onClick(View view) {
        Button v = (Button) view;

        Bundle bundle = new Bundle();
        bundle.putString("valgtord", "" + v.getText().toString());

        SelveSpillet selveSpillet = new SelveSpillet();
        selveSpillet.setArguments(bundle);

        context.getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, selveSpillet)
                .commit();

    }
}
