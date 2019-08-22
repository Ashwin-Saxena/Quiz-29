package com.example.quiz29;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.morteza.kordi.quizzinga.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
private Button signup,logian;
private EditText email , pass,username;
public FirebaseAuth mAuth;


    public void Login(View v)
    {

        ActivityOptions options =
                ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.fade_in, R.anim.fade_out);
        Intent intent = new Intent(this,login.class);
//                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent,options.toBundle());
        finishAffinity();

    }
    static int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
//        logian=findViewById(R.id.login);
    signup = findViewById(R.id.signup);
    email = findViewById(R.id.email);
    pass = findViewById(R.id.pass);
    mAuth = FirebaseAuth.getInstance();

//login code for testing purposes
//    logian.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            signin();
//        }
//    });


        ////USE OF TIMER TO Open Login Activity After 3 Seconds//////
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//   Intent in = new Intent(MainActivity.this,login.class)  ;
//   startActivity(in);
//            }
//        },3000);
    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            signUp();
        }
    });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        if(currentuser!=null)
        {//Transition to next Activty
            trans();

        }
    }
    public void signUp()
    {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"Signing Up Successful",Toast.LENGTH_SHORT).show();

                    FirebaseDatabase.getInstance().getReference().child("My_Users").child(task.getResult().getUser().getUid()).child("Username").setValue(username.getText().toString());

                    trans();
                }
                else {
                    Toast.makeText(MainActivity.this,"Signing Up Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
//login code for testing purposes
//    private void signin()
//    {
//        mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                if(task.isSuccessful())
//                {
//                    Toast.makeText(MainActivity.this,"Signing In Successful",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(MainActivity.this,"Signing In Failed",Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//    }



    public void trans()
    {Intent intent =new Intent(this,Dashboard.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);}

        @Override
        public void onBackPressed() {
            System.exit(0);
        }


        }

//class  dsd
//{
//    public void fr(String[] args)
//    {MainActivity m = new MainActivity();
//    m.df("Vijay");
//
//    }
//
//}


