package com.codegama.todolistapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.codegama.todolistapplication.R;

import butterknife.ButterKnife;

public class WelCome extends AppCompatActivity {
 int splash_timeout=5000;
TextView wel,leaning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        ButterKnife.bind(this);
        wel=findViewById(R.id.textView);
        leaning=findViewById(R.id.textView2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(WelCome.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },splash_timeout);
        Animation myanimation= AnimationUtils.loadAnimation(WelCome.this,R.anim.animation2);
        wel.startAnimation(myanimation);
        Animation myanimation2= AnimationUtils.loadAnimation(WelCome.this,R.anim.animation);
        leaning.startAnimation(myanimation2);
    }
}