package com.example.xpressdelivery;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;


public class ProfileFragment extends Fragment {

   View view;

   ImageView profile_image;
   FirebaseFirestore db;
   FirebaseAuth mAuth;
   FirebaseUser user;

   TextView name;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        name = view.findViewById(R.id.profile_name);


        mAuth =FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        String uid = user.getUid();



        /*DocumentReference documentReference = db.collection("users").document(uid);
        documentReference.addSnapshotListener((Executor) this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                name.setText(value.getString("name"));


            }
        });*/



        view =  inflater.inflate(R.layout.fragment_profile, container, false);
        return view;


    }
}