package e.android.gaglespil;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BrugerList extends  ArrayAdapter<Bruger> {

        private Activity context;
        private List<Bruger> brugerList;

        public BrugerList(Activity context, List<Bruger> brugerList) {
            super(context, R.layout.list_layout, brugerList);
            this.context = context;
            this.brugerList = brugerList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();

            View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

            TextView nameView = (TextView) listViewItem.findViewById(R.id.navnLayout);
            TextView scoreView = (TextView) listViewItem.findViewById(R.id.scoreLayout);


            Bruger bruger = brugerList.get(position);


            nameView.setText(bruger.getNavn());
            scoreView.setText("" + bruger.getScore());

            return listViewItem;

        }



}
