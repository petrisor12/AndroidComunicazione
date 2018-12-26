package com.example.piero.provolezione1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements DialogReset.IDialogReset,DialogMultiplica.IDialogMultiplica {
   private TextView result;
private int num = 0;
private  static String SalvaDati="EWFASD";
    private  static String DIALOG="EWFASDS";
    private  static String DIALOGMULTIPLICA="EWFASDST";

// associo il layout a questa activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        result=findViewById(R.id.txtResult);

//recupero i dati salvati in savedInstanceState, se esistono tramite la stringa
        if (savedInstanceState !=null)
        {
            num=savedInstanceState.getInt(SalvaDati);
        }

//dichiaro i vari button e quello che fanno
       Button btnResult =findViewById(R.id.btnReset);
       btnResult.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // qui lancio un dialog fragment
              lanciaDialog();

           }
       });
        Button btnPiu = findViewById(R.id.btnPiu);
        btnPiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                show();
            }
        });
        Button btnMeno = findViewById(R.id.btnMeno);
        btnMeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                show();
            }
        });
        Button btnMultiplica=findViewById(R.id.bunMultiplica);
                btnMultiplica.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // qui lancio un dialog fragment
                        lanciaDialodMultiplica();
                    }
                });

    }
    // salvo i dati tramite saveInstanceState
    //salvo i dati tramite una striga

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

      outState.putInt(SalvaDati,num);

    }

    void show(){
        result.setText(""+num);

    }
    private void lanciaDialog(){
        DialogReset dialogReset = new DialogReset();
       dialogReset.show(getFragmentManager(),DIALOG);

    }
    private void lanciaDialodMultiplica(){
        //creo un nuovo dialog, instanza di classe
        DialogMultiplica dialogMultiplica =new DialogMultiplica();
        // espongo la nuova instanza
        dialogMultiplica.show(getFragmentManager(),DIALOGMULTIPLICA);

    }
    //implemento il metodo del interfaccia

    @Override
    public void reset() {
        num=0;
        show();

    }
    //implemento il metodo del interfaccia

    @Override
    public void multiplica() {

       goToOtherActivity();

    }
    //funzione che mi porta alla seconda activity
    private void goToOtherActivity(){
        //dichiaro un nuovo intent per questo packege e per l'alta activity da aprire
        //dichiaro un Bundle per salvare un dato da mandare al altra activity
        //metto dentro il bundle le cose da salvare a partire dal tipo di dato, usando una chiave stringa
        //aggiungo al intent questo bundle
        // faccio partire la nuova activity
        Intent vIntent =new Intent(this,Activity2.class);
        Bundle vBundle = new Bundle();
       vBundle.putInt("NUMERO",num);
        vIntent.putExtras(vBundle);
        startActivity(vIntent);
    }

}
