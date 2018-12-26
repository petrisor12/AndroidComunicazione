package com.example.piero.provolezione1;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
private TextView textView;

private int numFragment=0;
private static String FIRSTVALUE="ABC";
private static String LASTVALUE="ABCe";
// dichiaro una interfaccia per comunicare con l'activity che include il fragment
public interface ImandaDatiDaFragment{
  void   mandaDatiDaFragment(int avalue);
}
//dichiaro un'instanza della interfaccia
ImandaDatiDaFragment imandaDatiDaFragment;
// la first best practice per questo fragment
    public static BlankFragment newInstance (){

        return new BlankFragment();

    }
    // la seccond best practice per questo fragment
    public static BlankFragment newInstance (int startValue){
        //dichiaro un bundle per salvare il dato passato quando si instanzia questa classe fragment
        Bundle bundle = new Bundle();
        bundle.putInt(FIRSTVALUE,startValue);
       BlankFragment blankFragment =new BlankFragment();
       blankFragment.setArguments(bundle);
       return blankFragment;


    }


// dichiaro il fragment e lo assoccio al suo layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vView=inflater.inflate(R.layout.layoutfragment,container,false);

        textView=vView.findViewById(R.id.txtResultFragment);

        MostraNr();


        Button btnAggiungi = vView.findViewById(R.id.btnAggiungi);
        btnAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numFragment++;
                //tramite l'instanza della interfaccia comunico dati alla activity che comprende il presente fragment
                imandaDatiDaFragment.mandaDatiDaFragment(numFragment);
                MostraNr();
            }
        });
        Button btnRimovi = vView.findViewById(R.id.btnRimovi);
        btnRimovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numFragment--;
                imandaDatiDaFragment.mandaDatiDaFragment(numFragment);
                MostraNr();
            }
        });

        return vView;

    }
    // quando si crea il fragment, recupero i dati da bundle(se ci sono) oppure dal savedInstanceState(se ci sono)

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle vBundle =getArguments();
        if (vBundle!=null){
            numFragment=vBundle.getInt(FIRSTVALUE);
        }
        if (savedInstanceState!=null){
            numFragment=savedInstanceState.getInt(LASTVALUE);
        }
    }

    private void MostraNr(){
        textView.setText("labelPrimoFragment  "+numFragment);

    }
    //salvo i dati tramite onSaveInstanceState
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LASTVALUE,numFragment);
    }

//il metodo onAttach per instanziare l'interfaccia
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

            if (context instanceof ImandaDatiDaFragment){
                imandaDatiDaFragment=(ImandaDatiDaFragment)context;
            }
    }
    // il metodo onDestroy per distrugere l'instanza del'interfaccia
    @Override
    public void onDestroy() {
        super.onDestroy();
        imandaDatiDaFragment=null;
    }
}
