package com.example.piero.provolezione1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
// l'interfaccia del fragment dialog
public class DialogReset extends DialogFragment {
    public interface IDialogReset{
        void reset();
    }
    //instanza della intefaccia
    IDialogReset dialogResetInterfaceInstance ;

    // dichiaro il fragment dialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder cancella = new AlertDialog.Builder(getActivity());
        // set il fragment dialog
        cancella.setMessage("vuoi cancellare?");
        cancella.setTitle("Titolo cassewla di dialogo");
        cancella.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //communico tramite l'instanza della interfaccia
                dialogResetInterfaceInstance.reset();
            }
        });

        return cancella.create();
    }
//il metodo onAttach per instanziare l'interfaccia

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IDialogReset){
            dialogResetInterfaceInstance=(IDialogReset)context;
        }
    }
    // il metodo onDestroy per distrugere l'instanza del'interfaccia
    @Override
    public void onDetach() {
        super.onDetach();
        dialogResetInterfaceInstance = null;
    }
}
