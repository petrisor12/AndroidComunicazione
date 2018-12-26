package com.example.piero.provolezione1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class DialogMultiplica extends DialogFragment {
    public interface IDialogMultiplica {
        void multiplica();
    }


    IDialogMultiplica iDialogMultiplica;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder cancella = new AlertDialog.Builder(getActivity());
        cancella.setMessage("vuoi multiplicare?");
        cancella.setTitle("Titolo cassewa di dialogo per multiplica");
        cancella.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iDialogMultiplica.multiplica();
            }
        });
        cancella.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return cancella.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IDialogMultiplica)
        iDialogMultiplica=(IDialogMultiplica)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iDialogMultiplica=null;
    }
}