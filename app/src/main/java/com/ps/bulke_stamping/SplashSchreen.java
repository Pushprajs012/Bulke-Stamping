package com.ps.bulke_stamping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ps.bulke_stamping.Admin.AdminOrder;
import com.ps.bulke_stamping.myuser.UserDashboard;
import com.ps.bulke_stamping.DetailClasses.UserDetail;

public class SplashSchreen extends AppCompatActivity {

    AppActivity appActivity;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_schreen);
        getSupportActionBar().hide();
        appActivity=(AppActivity) getApplication();
        user = appActivity.getFirebaseAuth().getCurrentUser();

        Window window=getWindow();
        window.setStatusBarColor(ContextCompat.getColor(SplashSchreen.this,R.color.black));

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(appActivity.getFirebaseAuth().getCurrentUser() != null){

                    appActivity.getMyRef().child("user").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            UserDetail userDetail=snapshot.getValue(UserDetail.class);
                            appActivity.setUserDetail(snapshot.getValue(UserDetail.class));
                            System.out.println(userDetail==null);
                            Intent intent=new Intent(SplashSchreen.this, UserDashboard.class);

                            if (userDetail == null){
                                intent.putExtra("isuserhavedetail", false);
                                startActivity(intent);
                            }
                            else if (appActivity.getUserDetail().getUsertype().equals("admin")){
                              startActivity(new Intent(SplashSchreen.this, AdminOrder.class));
                            }

                            else{
                                intent.putExtra("isuserhavedetail", true);
                                startActivity(intent);}
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
        else    {
                startActivity(new Intent(SplashSchreen.this,MainActivity.class));
                finish();
}
            }
        },3000);


    }
}