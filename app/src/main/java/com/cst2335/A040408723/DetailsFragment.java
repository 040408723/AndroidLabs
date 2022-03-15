package com.cst2335.A040408723;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


public class DetailsFragment extends Fragment {

    int position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                position = getArguments().getInt("position", 0);
            }
        }
    }

    private TextView textview1;
    private TextView textview2;
    private  TextView checkbox1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_details,container,false);
        textview1=view.findViewById(R.id.here);
        textview2=view.findViewById(R.id.id);
        checkbox1=view.findViewById(R.id.checkboxmsg);
        //return inflater.inflate(R.layout.fragment_details, container, false);
        return view;
    }

    public void onButtonClick(){

    }
}