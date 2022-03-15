package com.example.xpressdelivery;

import static com.google.android.gms.common.util.CollectionUtils.mapOf;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ParcelDetails extends AppCompatActivity {


    TextView parcelID, product_type, issued_date, receiver, r_mobile, r_address, sender, r_email,amount,weight;
    FirebaseFirestore db;
    String selected = "1. Issued";
    LinearLayout l1, l2, l3, l4, l5;
    Button update_status_btn,delete_btn;
    LinearLayout linear1;


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
        r_email = findViewById(R.id.r_email);
        amount = findViewById(R.id.amount);
        weight = findViewById(R.id.weight);
        delete_btn = findViewById(R.id.delete_btn);

        linear1 = findViewById(R.id.linear1);

        update_status_btn = findViewById(R.id.update_status_btn);


        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);
        l5 = findViewById(R.id.l5);


        db = FirebaseFirestore.getInstance();


        DocumentReference documentReference1 = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference1.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                String userStatus = ""+value.getString("userStatus");

                if(userStatus.equals("Admin")){

                    linear1.setVisibility(View.VISIBLE);
                }


            }
        });


        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"1. Issued", "2. Way to Warehouse", "3. In Warehouse", "4. Way to Receiver", "5. Delivered"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                selected = dropdown.getSelectedItem().toString();
                System.out.println(selected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });




        DocumentReference documentReference = db.collection("parcel").document(pID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                parcelID.setText("Parcel ID - \n" + value.getString("pID"));
                product_type.setText("Product Type : " + value.getString("pType"));
                issued_date.setText("Issued Date : " + value.getString("issueDate"));
                receiver.setText("Receiver : " + value.getString("rName") + "  *");
                r_email.setText("Receiver : " + value.getString("rEmail"));
                r_mobile.setText("Receiver Mobile : " + value.getString("rMobile"));
                r_address.setText("Receiver Address : " + value.getString("rAdd"));
                sender.setText("Sender : " + value.getString("sName") + "  *");
                sender.setText("Sender : " + value.getString("sName") + "  *");
                weight.setText("Parcel Weight : " + value.getString("weight" + "KG"));

                String amo = value.getString("weight");
                int am = Integer.parseInt(amo);
                am = (am * 10) ;

                amount.setText("Payment amount : " + (am + 100) + "৳");

                String issued = value.getString("issued");
                String wayToWH = value.getString("wayToWH");
                String inWH = value.getString("inWH");
                String wayToR = value.getString("wayToR");
                String delivered = value.getString("delivered");


                if (delivered.equals("true")) {
                    int spinnerPosition = adapter.getPosition("5. Delivered");
                    dropdown.setSelection(spinnerPosition);
                    l5.setVisibility(View.VISIBLE);
                    l4.setVisibility(View.VISIBLE);
                    l3.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.VISIBLE);
                    System.out.println("5555555555555555555");
                } else if (wayToR.equals("true")) {
                    int spinnerPosition = adapter.getPosition("4. Way to Receiver");
                    dropdown.setSelection(spinnerPosition);
                    l4.setVisibility(View.VISIBLE);
                    l3.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.VISIBLE);
                    System.out.println("4444444444444444444");
                } else if (inWH.equals("true")) {
                    int spinnerPosition = adapter.getPosition("3. In Warehouse");
                    dropdown.setSelection(spinnerPosition);
                    l3.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.VISIBLE);
                    System.out.println("333333333333333333");
                } else if (wayToWH.equals("true")) {
                    int spinnerPosition = adapter.getPosition("2. Way to Warehouse");
                    dropdown.setSelection(spinnerPosition);
                    l2.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.VISIBLE);
                    System.out.println("222222222222222222222");
                } else if (issued.equals("true")) {
                    int spinnerPosition = adapter.getPosition("1. Issued");
                    dropdown.setSelection(spinnerPosition);
                    l1.setVisibility(View.VISIBLE);
                    System.out.println("111111111111111111");
                }


            }
        });


        update_status_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (selected.equals("5. Delivered")) {

                    db.collection("parcel")
                            .document(pID)
                            .update(
                                    "delivered","true",
                                    "wayToR","true",
                                    "inWH","true",
                                    "wayToWH","true",
                                    "issued","true"
                            );

                }
                else if (selected.equals("4. Way to Receiver")) {

                    db.collection("parcel")
                            .document(pID)
                            .update(
                                    "delivered","false",
                                    "wayToR","true",
                                    "inWH","true",
                                    "wayToWH","true",
                                    "issued","true"
                            );

                }
                else if (selected.equals("3. In Warehouse")) {

                    db.collection("parcel")
                            .document(pID)
                            .update(
                                    "delivered","false",
                                    "wayToR","false",
                                    "inWH","true",
                                    "wayToWH","true",
                                    "issued","true"
                            );

                }
                else if (selected.equals("2. Way to Warehouse")) {

                    db.collection("parcel")
                            .document(pID)
                            .update(
                                    "delivered","false",
                                    "wayToR","false",
                                    "inWH","false",
                                    "wayToWH","true",
                                    "issued","true"
                            );

                }
                else if (selected.equals("1. Issued")) {

                    db.collection("parcel")
                            .document(pID)
                            .update(
                                    "delivered","false",
                                    "wayToR","false",
                                    "inWH","false",
                                    "wayToWH","false",
                                    "issued","true"
                            );

                }




                recreate();
            }
        });


        delete_btn.setOnClickListener(v->{

            db.collection("parcel")
                    .document(pID)
                    .delete();

            startActivity(new Intent(getApplicationContext(),HomeScreen.class));
            finish();

        });


    }
}