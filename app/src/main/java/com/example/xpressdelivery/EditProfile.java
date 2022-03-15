package com.example.xpressdelivery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EditProfile extends AppCompatActivity {

    EditText edit_name,edit_mobile,edit_address;
    Button update_now_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        edit_name = findViewById(R.id.edit_name);
        edit_mobile = findViewById(R.id.edit_mobile);
        edit_address = findViewById(R.id.edit_address);
        update_now_btn = findViewById(R.id.update_now_btn);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference documentReference = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                edit_name.setText(""+value.getString("name"));
                edit_mobile.setText(""+value.getString("mobile"));
                edit_address.setText(""+value.getString("address"));


            }
        });


        update_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edit_name.getText().toString();
                String mobile = edit_mobile.getText().toString();
                String address = edit_address.getText().toString();


                if(name.length()>3 && mobile.length()>10 && address.length()>3){

                    db.collection("users")
                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .update(
                                    "name",name,
                                    "mobile",mobile,
                                    "address",address
                            );

                    startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                    finish();

                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
}