package com.example.xpressdelivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    View view;

    TextView add_parcel_btn;
    LinearLayout add_parcel_view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);


        add_parcel_btn = view.findViewById(R.id.add_parcel_btn);
        add_parcel_view = view.findViewById(R.id.add_parcel_view);


        add_parcel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddParcelPage.class));
            }
        });

        add_parcel_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddParcelPage.class));
            }
        });


        return view;
    }
}