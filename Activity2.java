package com.example.piero.provolezione1;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends Activity implements BlankFragment.ImandaDatiDaFragment {
    int number;
    TextView txtResult;
    private BlankFragment mFragmentA;
    private BlankFragment2 mFragmentB;
    private final static String First_Fragment = "First_Fragment";
    private final static String Seccond_Fragment = "Seccond_Fragment";

    // creo una nuova instanza di activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // colego la nuova activity al suo layout

        setContentView(R.layout.layoutsecondaactivity);
        txtResult = findViewById(R.id.txtResultSecondaActivity);
        //creo un nuovo bundle che recupera i dati tramite getIntent()
        //se ci sono dati li recupero- prima dichiaro una vaiabile locale di tipèo Integer e le metto nelle variabili di questa activity
        Bundle vBundleActivity = getIntent().getExtras();
        if(vBundleActivity!= null) {
            Integer vInteger =vBundleActivity.getInt("NUMERO");
            number = vInteger*3;
            txtResult.setText(" labelseccondoActivity  "+number);
        }
// instanzio i due fragmennt tramite il FragmentTranzaction, nei rispetivi container
        if (savedInstanceState == null) {

            FragmentTransaction vFt = getFragmentManager().beginTransaction();
            mFragmentA = BlankFragment.newInstance(number);
            mFragmentB = BlankFragment2.newInstance();
            vFt.add(R.id.container1, mFragmentA, First_Fragment);
            vFt.add(R.id.container2, mFragmentB, Seccond_Fragment);
            vFt.commit();
        } else {
            mFragmentA = (BlankFragment) getFragmentManager().findFragmentByTag(First_Fragment);
            mFragmentB = (BlankFragment2) getFragmentManager().findFragmentByTag(Seccond_Fragment);
        }


    }


// override del metodo della interfaccia dichiarata nel BlankFragment e implementata in questa activity
    @Override
    public void mandaDatiDaFragment(int avalue) {
       //txtResult.setText(""+avalue);
        //rimando i dati al fragment B appelando il metodo publico di questo fragment
        mFragmentB.updateLabel("labelseccondoFragment  " + avalue);

    }
}