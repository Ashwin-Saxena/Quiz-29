package com.example.quiz29;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.morteza.kordi.quizzinga.R;

public class login extends AppCompatActivity {
private EditText email , pass;
private Button login ;
private FirebaseAuth mAuth;
private ProgressDialog pr;

public void  register(View v)
{Intent intent =new Intent(login.this,MainActivity.class);
startActivity(intent);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        mAuth =FirebaseAuth.getInstance();

        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pr=ProgressDialog.show(login.this,"","Signing you In",true);
                signin();
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



    private void signin()
    {
        mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {   trans();
                    Toast.makeText(login.this,"Signing In Successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(login.this,"Signing In Failed",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void trans()
    {Intent intent =new Intent(this,Dashboard.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finishAffinity();
    }

}
