package com.cst2335.A040408723;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class DetailsFragment extends Fragment {

    boolean isTablet = false;
    private static final String ARG_NUM1="param1";
    private static final String ARG_NUM2="param2";
    int position;

    public DetailsFragment(){

    }

    public static DetailsFragment newInstance(String param1, String param2){
        DetailsFragment fragment=new DetailsFragment();
        Bundle args=new Bundle();
        args.putString(ARG_NUM1, param1);
        args.putString(ARG_NUM2,param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            // Get back arguments
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
        TextView id1=view.findViewById(R.id.id);
        CheckBox checkbox1=view.findViewById(R.id.checkboxmsg);
        Button hide=view.findViewById(R.id.hide);

        Bundle bundle=getArguments();

        String message=bundle.getString("Message");
        long id=bundle.getLong("ID");
        long idChat=bundle.getLong("idChat");

        textview1.setText(message);
        id1.setText(String.valueOf(id));

        hide.setOnClickListener(view1 -> {
            if(isTablet){
            ChatRoomActivity chat=(ChatRoomActivity)getActivity() ;

                chat.getSupportFragmentManager()
                        .beginTransaction()
                        .remove(getParentFragment())
                        .commit();
            }else{
                Intent nextActivity=new Intent();
                nextActivity.putExtra("Message", message);
                nextActivity.putExtra("Delete ID", id);
                nextActivity.putExtra("idChat", idChat);
                getActivity().setResult(Activity.RESULT_OK, nextActivity);
                getActivity().finish();
            }
        });

        return view;
    }
    public void setIsTablet(boolean isTablet) {
        this.isTablet = isTablet;
    }
}