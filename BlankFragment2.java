package com.example.piero.provolezione1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlankFragment2 extends Fragment {
    private static final String VAL_INIZIALE="VI";
    private static final String VAL_FINALE="VF";


    String mValue;
    TextView mLabel;
    public  static BlankFragment2 newInstance(){
        return newInstance("----");
    }
    public static BlankFragment2 newInstance(String aString){
        Bundle vBundle =new Bundle();
        vBundle.putString(VAL_INIZIALE,aString);
        BlankFragment2 vFragment=new BlankFragment2();
        vFragment.setArguments(vBundle);
        return vFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vView= inflater.inflate(R.layout.layoutfragment2,container,false);
        mLabel=vView.findViewById(R.id.textView);
        Bundle vStartBundle=getArguments();
        if (vStartBundle!=null)
            mValue =vStartBundle.getString(VAL_INIZIALE);
        if (savedInstanceState!=null)
            mValue=savedInstanceState.getString(VAL_FINALE);
        updateLabel(mValue);
        return vView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(VAL_FINALE,mValue);
    }
    public void  updateLabel(String m){
        mValue=m;
        if (mLabel!=null)
        mLabel.setText(m);
    }

}
