package com.ps.bulke_stamping.myuser.grahak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.airbnb.lottie.LottieAnimationView;
import com.ps.bulke_stamping.R;

public class Successoder extends AppCompatActivity {
     private AppCompatTextView showoderno,odermassage, callingmassage;
     private  LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysuccess);

        init();

        Bundle intent=getIntent().getExtras();
       String oderno=intent.getString("odernumber");
       Boolean istrue=intent.getBoolean("istrue");

        System.out.println(istrue+"value of istrue");
        System.out.println(oderno);

        if (istrue) {
            lottieAnimationView.setAnimation(R.raw.newone);
            odermassage.setText(R.string.successmassgae);
            showoderno.setText(getString(R.string.printcoderno)+oderno);
            callingmassage.setText(R.string.callingmassge);
            lottieAnimationView.playAnimation();
        }
        else {
            lottieAnimationView.setAnimation(R.raw.fail);
            odermassage.setText(R.string.oderfail);
            callingmassage.setVisibility(View.INVISIBLE);
            lottieAnimationView.playAnimation();

        }
    }

    private void init(){
        lottieAnimationView = findViewById(R.id.animation);
        odermassage=findViewById(R.id.successmassage);
        callingmassage=findViewById(R.id.callingmassage);
        showoderno=findViewById(R.id.oderid);
    }


}
