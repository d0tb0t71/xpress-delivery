package com.example.xpressdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;

public class AddParcelPage extends AppCompatActivity {

    EditText rName_ET,rEmail_ET,rMobile_ET,rAdd_ET,sName_ET,pT_ET;
    Button add_parcel_now_btn;
    FirebaseFirestore db;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parcel_page);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        rName_ET = findViewById(R.id.rName_ET);
        rEmail_ET = findViewById(R.id.rEmail_ET);
        rMobile_ET = findViewById(R.id.rMobile_ET);
        rAdd_ET = findViewById(R.id.rAdd_ET);
        sName_ET = findViewById(R.id.sName_ET);
        pT_ET = findViewById(R.id.pT_ET);

        add_parcel_now_btn = findViewById(R.id.add_parcel_now_btn);


        add_parcel_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rName = ""+ rName_ET.getText().toString();
                String rEmail = "" + rEmail_ET.getText().toString();
                String rMobile = "" + rMobile_ET.getText().toString();
                String rAdd = "" + rAdd_ET.getText().toString();
                String sName = "" + sName_ET.getText().toString();
                String pType = "" + pT_ET.getText().toString();

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                long time = timestamp.getTime();
                String pID = "XD" + time;

                String issueDate = timestamp.toString();

                        ParcelModel parcel = new ParcelModel(pID,rName,rEmail,rMobile,rAdd,sName,pType,issueDate,"true","false","false","false","false");

                db.collection("parcel")
                        .document(pID)
                        .set(parcel);

                startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                finish();


            }
        });




    }
}