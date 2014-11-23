package com.manuel.animations;



import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentPrueba extends Fragment {

    Color color = new Color();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_layout, container, false);


        TextView tv = (TextView)v.findViewById(R.id.tv1);

        Bundle bundle = getArguments();

        tv.setBackgroundColor(bundle.getInt("Color"));



        return v;
    }


}
