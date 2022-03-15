package com.example.xpressdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeScreen extends AppCompatActivity {

    ImageView home_nav_btn,track_nav_btn,message_nav_btn,profile_nav_btn;
    ImageView nav_1,nav_2,nav_3,nav_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);



        home_nav_btn = findViewById(R.id.home_nav_btn);
        track_nav_btn = findViewById(R.id.track_nav_btn);
        message_nav_btn = findViewById(R.id.message_nav_btn);
        profile_nav_btn = findViewById(R.id.profile_nav_btn);


        nav_1 = findViewById(R.id.nav_1);
        nav_2 = findViewById(R.id.nav_2);
        nav_3 = findViewById(R.id.nav_3);
        nav_4 = findViewById(R.id.nav_4);


        replaceFragment(new HomeFragment());


        home_nav_btn.setOnClickListener(v->{

            replaceFragment(new HomeFragment());

        });
        track_nav_btn.setOnClickListener(v->{

            replaceFragment(new TrackWithIDFragment());

        });
        message_nav_btn.setOnClickListener(v->{

            replaceFragment(new ContactUsFragment());

        });
        profile_nav_btn.setOnClickListener(v->{

            replaceFragment(new ProfileFragment());

        });






    }

    void replaceFragment(Fragment fragment){


        String frag = fragment.getClass().toString();

        if(frag.contains("HomeFragment")){
            nav_1.setVisibility(View.VISIBLE);
            nav_2.setVisibility(View.GONE);
            nav_3.setVisibility(View.GONE);
            nav_4.setVisibility(View.GONE);

        }
        else if(frag.contains("TrackWithIDFragment")){
            nav_1.setVisibility(View.GONE);
            nav_2.setVisibility(View.VISIBLE);
            nav_3.setVisibility(View.GONE);
            nav_4.setVisibility(View.GONE);

        }else if(frag.contains("ContactUsFragment")){
            nav_1.setVisibility(View.GONE);
            nav_2.setVisibility(View.GONE);
            nav_3.setVisibility(View.VISIBLE);
            nav_4.setVisibility(View.GONE);
        }
        else if(frag.contains("ProfileFragment")){
            nav_1.setVisibility(View.GONE);
            nav_2.setVisibility(View.GONE);
            nav_3.setVisibility(View.GONE);
            nav_4.setVisibility(View.VISIBLE);

        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }
}