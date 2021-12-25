package com.example.xpressdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

public class RegisterScreen extends AppCompatActivity {

    EditText register_email,register_pass,register_name,register_c_pass,register_mobile;
    Button register_now_btn;

    FirebaseAuth mAuth;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);


        register_email = findViewById(R.id.register_email);
        register_pass = findViewById(R.id.register_pass);
        register_name = findViewById(R.id.register_name);
        register_c_pass = findViewById(R.id.register_c_pass);
        register_mobile = findViewById(R.id.register_mobile);


        register_now_btn = findViewById(R.id.register_now_btn);
        mAuth = FirebaseAuth.getInstance();

        register_now_btn.setOnClickListener(v->{

            //sdipon.sutradhar@gmail.com

            String email = register_email.getText().toString();
            String pass = register_pass.getText().toString();
            String name = register_name.getText().toString();
            String c_pass = register_c_pass.getText().toString();
            String mobile = register_mobile.getText().toString();

            UserModel userModel = new UserModel(name,email,mobile);


            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),VerifyEmailScreen.class));

                            FirebaseUser user = mAuth.getCurrentUser();


                            //ApiFuture<WriteResult> future = db.collection("cities").document("LA").set(docData);

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Registration Failed\n"+task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Registration Failed !\n"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        });


    }
}