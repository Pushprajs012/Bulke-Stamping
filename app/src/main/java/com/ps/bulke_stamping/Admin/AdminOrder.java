package com.ps.bulke_stamping.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ps.bulke_stamping.Adapter.AdminOrderAdapter;
import com.ps.bulke_stamping.AppActivity;
import com.ps.bulke_stamping.DetailClasses.OrderDetail;
import com.ps.bulke_stamping.MainActivity;
import com.ps.bulke_stamping.R;

import java.util.ArrayList;

public class AdminOrder extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdminOrderAdapter adminOrderAdapter;

    public AppActivity getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(AppActivity appActivity) {
        this.appActivity = appActivity;
    }

    private AppActivity appActivity;
    public OrderDetail orderDetail;
    private ArrayList<OrderDetail> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldorders);
        init();

        adminOrderAdapter = new AdminOrderAdapter(arrayList, AdminOrder.this);
        recyclerView.setAdapter(adminOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminOrder.this));


        appActivity.getMyRef().child("Order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    orderDetail = item.getValue(OrderDetail.class);

                    arrayList.add(0, orderDetail);


                    adminOrderAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.rv);
        appActivity = (AppActivity) getApplication();
        arrayList = new ArrayList<OrderDetail>();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.adminmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.adminlogout:
                appActivity.getFirebaseAuth().getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));

                break;

        }
            return super.onOptionsItemSelected(item);


    }
}