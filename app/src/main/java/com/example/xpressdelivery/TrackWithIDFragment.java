package com.example.xpressdelivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class TrackWithIDFragment extends Fragment {

   View view;
   EditText track_id;
   Button track_btn;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_track_with_i_d, container, false);

        track_id = view.findViewById(R.id.track_id);
        track_btn = view.findViewById(R.id.track_btn);

        db = FirebaseFirestore.getInstance();

        track_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(track_id.getText().toString()!="" && track_id.getText().toString().contains("XD")){


                    DocumentReference docIdRef = db.collection("parcel").document(track_id.getText().toString());
                    docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Intent intent = new Intent(getContext(),ParcelDetails.class);
                                    intent.putExtra("pID",track_id.getText().toString());
                                    startActivity(intent);
                                } else {
                                    track_id.setError("Enter An Valid ID");
                                }
                            } else {
                                track_id.setError("Enter An Valid ID");
                            }
                        }
                    });


                }
                else{
                   // Toast.makeText(getActivity(), "Enter An Valid ID", Toast.LENGTH_SHORT).show();
                    track_id.setError("Enter An Valid ID");
                }

            }
        });

        return view;
    }
}