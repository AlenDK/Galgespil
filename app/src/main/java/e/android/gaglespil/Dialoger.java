package e.android.gaglespil;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

public class Dialoger extends AppCompatDialogFragment {



    String titel, besked;

public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle(getTitel())
            .setMessage(getBesked())
            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface,int i) {
    }
});

return builder.create();
        }

public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

public void setBesked(String besked) {
        this.besked = besked;
    }

    public String getBesked() {
        return besked;
    }

}
