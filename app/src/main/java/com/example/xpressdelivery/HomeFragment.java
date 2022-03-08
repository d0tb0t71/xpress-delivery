package com.example.xpressdelivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    View view;

    TextView add_parcel_btn;
    LinearLayout add_parcel_view;
    CardView card_add;

    RecyclerView recyclerViewParcel;

    ParcelAdapter parcelAdapter;
    ArrayList<ParcelModel> list;
    FirebaseFirestore db;

    String userStatus="",myEmail = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);


        add_parcel_btn = view.findViewById(R.id.add_parcel_btn);
        add_parcel_view = view.findViewById(R.id.add_parcel_view);
        recyclerViewParcel = view.findViewById(R.id.recyclerViewParcel);
        card_add = view.findViewById(R.id.card_add);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerViewParcel.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        parcelAdapter = new ParcelAdapter(getContext(),list);
        recyclerViewParcel.setAdapter(parcelAdapter);

        db = FirebaseFirestore.getInstance();


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

        DocumentReference documentReference1 = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference1.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                userStatus = ""+value.getString("userStatus");
                myEmail = ""+value.getString("email");

                if(userStatus.equals("admin")){
                    card_add.setVisibility(View.VISIBLE);
                }


            }
        });



        db.collection("parcel")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Toast.makeText(getContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        for(DocumentChange dc : value.getDocumentChanges()){

                            if(dc.getType() == DocumentChange.Type.ADDED){

                                if(userStatus.equals("admin")){
                                    list.add(dc.getDocument().toObject(ParcelModel.class));
                                }
                                else{
                                    ParcelModel parcelModel = dc.getDocument().toObject(ParcelModel.class);

                                    if(parcelModel.getrEmail().equals(myEmail)){
                                        list.add(dc.getDocument().toObject(ParcelModel.class));
                                    }
                                }

                            }

                            parcelAdapter.notifyDataSetChanged();

                        }

                    }
                });




        return view;
    }
}