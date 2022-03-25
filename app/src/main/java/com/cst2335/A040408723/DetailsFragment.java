package com.cst2335.A040408723;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class DetailsFragment extends Fragment {

    boolean isTablet = false;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        TextView textview1 = view.findViewById(R.id.here);
        TextView id1 = view.findViewById(R.id.id);
        CheckBox checkbox1 = view.findViewById(R.id.checkboxmsg);
        Button hide = view.findViewById(R.id.hide);

        bundle = getArguments();

        //fetch the message, id, and boolean info back from bundle
        String message = bundle.getString("Message");
        long id = bundle.getLong("positionID");
        boolean isSend=bundle.getBoolean("isSend");

        //locate message, id
        textview1.setText(message);
        id1.setText(String.valueOf(id));

        //if this is the send message, checkbox will be checked.
        if(isSend){
            checkbox1.setChecked(true);
        }else{
            checkbox1.setChecked(false);
        }

        //hide button function
            hide.setOnClickListener(view1 -> {
                if (isTablet) {
                    DetailsFragment fragment=new DetailsFragment();

                    //getActivity().onBackPressed();
                    //getActivity().getSupportFragmentManager().popBackStack();

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flbox1,fragment)
                            .commit();

                    }
                    getActivity().finish();
            });
            return view;
        }
    }

