package com.example.xpressdelivery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ParcelDetails extends AppCompatActivity {


    TextView parcelID, product_type, issued_date, receiver, r_mobile, r_address, sender;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_details);


        String pID = getIntent().getStringExtra("pID");


        parcelID = findViewById(R.id.parcelID);
        product_type = findViewById(R.id.product_type);
        issued_date = findViewById(R.id.issued_date);
        receiver = findViewById(R.id.receiver);
        r_mobile = findViewById(R.id.r_mobile);
        r_address = findViewById(R.id.r_address);
        sender = findViewById(R.id.sender);


        db = FirebaseFirestore.getInstance();


        DocumentReference documentReference = db.collection("parcel").document(pID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                parcelID.setText("Parcel ID - \n" + value.getString("pID"));
                product_type.setText("Product Type : " + value.getString("pType"));
                issued_date.setText("Issued Date : " + value.getString("issueDate"));
                receiver.setText("Receiver : " + value.getString("rName")+"  *");
                r_mobile.setText("Receiver Mobile : " + value.getString("rMobile"));
                r_address.setText("Receiver Address : " + value.getString("name"));
                sender.setText("Sender : " + value.getString("sName")+"  *");



            }
        });


    }
}