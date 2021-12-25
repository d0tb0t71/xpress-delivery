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

public class LoginScreen extends AppCompatActivity {

    Button login_to_register,login_now_btn;
    EditText login_email,login_pass;
    FirebaseAuth mAuth;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_to_register = findViewById(R.id.login_to_register);
        login_now_btn = findViewById(R.id.login_now_btn);
        login_email = findViewById(R.id.login_email);
        login_pass = findViewById(R.id.login_pass);


        mAuth = FirebaseAuth.getInstance();



        login_now_btn.setOnClickListener(v->{

            String email = login_email.getText().toString();
            String pass = login_pass.getText().toString();


            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    user = mAuth.getCurrentUser();
                    if(task.isSuccessful() ){
                        if(user.isEmailVerified()){
                            startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                        }
                        else
                        {
                            startActivity(new Intent(getApplicationContext(),VerifyEmailScreen.class));
                        }

                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Login Failed \n"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });




        });














        login_to_register.setOnClickListener(v->{

            startActivity(new Intent(getApplicationContext(),RegisterScreen.class));
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null ){

            if(user.isEmailVerified()){
                startActivity(new Intent(getApplicationContext(),HomeScreen.class));
            }
            else{
                startActivity(new Intent(getApplicationContext(),VerifyEmailScreen.class));
            }

        }


    }
}


