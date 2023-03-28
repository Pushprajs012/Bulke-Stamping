package com.ps.bulke_stamping;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ps.bulke_stamping.DetailClasses.UserDetail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppActivity extends Application {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    private ExecutorService executorService;
    private DatabaseReference myRef;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    private UserDetail userDetail;

    public DatabaseReference getMyRef() {
        return myRef;
    }



    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        firebaseAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        myRef = database.getReference("estamp");
        executorService= Executors.newSingleThreadExecutor();




    }
}
