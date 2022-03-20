package com.cst2335.A040408723;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class DetailsFragment extends Fragment {
    ArrayAdapter<String> itemsAdapter;

    int position = 0;
    boolean isSend;
    private boolean isSend1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){

            if(getArguments() != null) {
                position = getArguments().getInt("position", 0);
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_details, container, false);
        TextView textview1=view.findViewById(R.id.here);
        TextView textview2=view.findViewById(R.id.id);
        CheckBox checkbox1=view.findViewById(R.id.checkboxmsg);

        textview1.setText(textview1.toString());
        textview2.setText(textview2.toString());
        checkbox1.setChecked(isSend1);

        textview1.getText();
        textview2.getText();

        return view;
    }



    public void onViewCreated(View view, Bundle savedInstanceState){





    }

    public void onButtonClick(){

    }
}