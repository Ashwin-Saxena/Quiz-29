package com.example.quiz29;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import  android.app.ProgressDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.morteza.kordi.quizzinga.R;

import java.util.Timer;
import java.util.TimerTask;

public class profile extends AppCompatActivity {
private FirebaseAuth mAuth;
private Button signout,btn;
private TextView email;
private   ProgressDialog progressDialog;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    progressDialog=ProgressDialog.show(profile.this,"","Please Wait" ,true);
        mAuth = FirebaseAuth.getInstance();
    email=findViewById(R.id.username);
    signout=findViewById(R.id.signout);
btn = findViewById(R.id.button);
    signout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            logout();
        }
    });

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(profile.this,Dashboard.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    });
//Using Timer to delay the invisiblity of Progress Bar
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//               pr.setVisibility(View.INVISIBLE);
//            }
//        },4000);





//Using Handler to delay the invisiblity of Progress Bar
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


stopp();
            }
        },5000);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentuser = mAuth.getCurrentUser();
        if(currentuser!=null)
        {

            updateUI(currentuser);

        }
    }
public void updateUI(FirebaseUser currentuser)
{String a=currentuser.getUid();
    DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference usnaref=rootref.child("My_Users").child(a);
    usnaref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String name = dataSnapshot.child("Username").getValue(String.class);
            email.setText("Hi ! "+name);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

            Toast.makeText(profile.this, "Failed to read value."+ databaseError.toException(),Toast.LENGTH_SHORT).show();
        }
    });


}

private void logout()
{mAuth.signOut();
finish();}

    @Override
    public void onBackPressed() {
        mAuth.signOut();
        finish();

    }

    private void stopp()
    {
        progressDialog.dismiss();

    }

}