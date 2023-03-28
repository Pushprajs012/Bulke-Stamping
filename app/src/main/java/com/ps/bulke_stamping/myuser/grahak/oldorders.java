package com.ps.bulke_stamping.myuser.grahak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ps.bulke_stamping.Adapter.OldOrderadapter;
import com.ps.bulke_stamping.AppActivity;
import com.ps.bulke_stamping.DetailClasses.OrderDetail;
import com.ps.bulke_stamping.R;

import java.util.ArrayList;

public class oldorders extends AppCompatActivity {

    private RecyclerView recyclerView;
   private OldOrderadapter oldOrderadapter;

    public AppActivity getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(AppActivity appActivity) {
        this.appActivity = appActivity;
    }

    private AppActivity appActivity;
    private ArrayList<OrderDetail> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldorders);
        init();

        oldOrderadapter=new OldOrderadapter(arrayList,oldorders.this);
        recyclerView.setAdapter(oldOrderadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(oldorders.this));


        appActivity.getMyRef().child("Order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    OrderDetail orderDetail = item.getValue(OrderDetail.class);
                    System.out.println(appActivity.getFirebaseAuth().getUid());
                    System.out.println(orderDetail.getGrahakuid());
                    if (appActivity.getFirebaseAuth().getUid().equals(orderDetail.getGrahakuid())) {
                        arrayList.add(0,orderDetail);
                    }


                    oldOrderadapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void init(){
        recyclerView=findViewById(R.id.rv);
        appActivity=(AppActivity) getApplication();
        arrayList=new ArrayList<OrderDetail>();

    }


}