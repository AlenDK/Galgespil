package e.android.gaglespil;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;







public class VælgOrdList extends ArrayAdapter<String> {

    int i;
    GalgeLogik galgeLogik = new GalgeLogik();
    private Activity context;
    private List<String> ordList;

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

        Button wordView = (Button) listViewItem.findViewById(R.id.buttonWord);



        String liste = ordList.get(position);


        wordView.setText(liste);

        return listViewItem;

    }


}
