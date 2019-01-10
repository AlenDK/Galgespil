package e.android.gaglespil;

import android.app.Activity;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BrugerList extends ArrayAdapter<Bruger> {

    private Activity context;
    private List<Bruger> brugerList;
    TextView placeringView, scoreView, nameView;
    int i = 1;

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

        nameView = (TextView) listViewItem.findViewById(R.id.navnLayout);
        scoreView = (TextView) listViewItem.findViewById(R.id.scoreLayout);
        placeringView = (TextView) listViewItem.findViewById(R.id.placering);


        Bruger bruger = brugerList.get(position);




        nameView.setText(bruger.getNavn());
        scoreView.setText("" + bruger.getScore());
        placeringView.setText(position+1 + "");

        Log.d( "tag" , "create " +brugerList.get(position));


        Log.d( "tag" , "create " +brugerList.size());
   /*    for (int i = 1; i < brugerList.size(); i++) {

           Log.d( "tag" , "lol " + i);

           placeringView.setText("h"+ i++);
        }
*/


        return listViewItem;

    }


    public void setPlacering(int i) {
    }


}
