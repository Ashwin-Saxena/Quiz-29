package com.example.quiz29;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.morteza.kordi.quizzinga.R;

import java.util.Timer;
import java.util.TimerTask;

public class loading extends AppCompatActivity {
private ProgressBar pr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        pr=findViewById(R.id.progressBar);
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                pr.setVisibility(View.VISIBLE);
//            }
//        },1000);

        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pr.setVisibility(View.VISIBLE);
            }
        },1000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(loading.this,MainActivity.class);
                ActivityOptions options=ActivityOptions.makeCustomAnimation(loading.this,R.anim.slide_in_right,R.anim.slide_out_left);
                finishAffinity();
                startActivity(intent,options.toBundle());
            }
        },3000);
    }

    public  void setName()
    {System.out.println("Fdf");}

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}


