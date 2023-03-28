package com.ps.bulke_stamping.myuser.grahak;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ps.bulke_stamping.AppActivity;
import com.ps.bulke_stamping.R;
import com.ps.bulke_stamping.DetailClasses.UserDetail;

import java.util.ArrayList;

public class GrahkDetail extends AppCompatActivity {
    private Spinner spinnerdis,spinnerteh;
    AppActivity appActivity;
    private ArrayAdapter<String> arrayAdapter,arrayAdapterteh;
    private ArrayList<String> spinnerdislist;
     private ArrayList<String> spinnertehList;
     private String selected,selectedteh;
     private Button updatebtn;
     private TextInputLayout name,no,address,pin;
     private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grahakdetail);
        initobjects();

        if (user.getPhoneNumber()!=null)
        {
            int autono=user.getPhoneNumber().length()-10;
            String setphoneno=user.getPhoneNumber().substring(autono);
            no.getEditText().setText(setphoneno);
            no.getEditText().setFocusable(false);


        }



        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerdislist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapterteh = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnertehList);
        arrayAdapterteh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerdis.setAdapter(arrayAdapter);
        spinnerteh.setAdapter(arrayAdapterteh);


        appActivity.getMyRef().child("address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinnerdislist.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    System.out.println(item);
                    spinnerdislist.add(item.getValue().toString());
                }
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        spinnerdis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = spinnerdislist.get(i);
                System.out.println("printselect"+selected);


                appActivity.getMyRef().child("tehsil").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        spinnertehList.clear();
                        for (DataSnapshot item : snapshot.child(selected).getChildren()) {
                            System.out.println(item.getValue().toString());
                           // System.out.println(item.child(selected).getValue().toString());
                            System.out.println(item);
                            spinnertehList.add(item.getValue().toString());
                        }
                        arrayAdapterteh.notifyDataSetChanged();


                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });



        spinnerteh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedteh = spinnertehList.get(i);
                System.out.println(selectedteh+"teh");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }


            private void initobjects(){
        spinnerdis=findViewById(R.id.selectdis);
        spinnerteh=findViewById(R.id.selectteh);
        name=findViewById(R.id.TIL_NAME);
        no=findViewById(R.id.TIL_PHONE);
        address=findViewById(R.id.address);
        pin=findViewById(R.id.pincode);
        updatebtn=findViewById(R.id.BTN_UPDATE);
        appActivity=(AppActivity) getApplication();
        spinnerdislist=new ArrayList<>();
        spinnertehList = new ArrayList<>();
        user = appActivity.getFirebaseAuth().getCurrentUser();


            }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);



        updatebtn.setOnClickListener(view -> {
           if (validateSignupForm()) {
               appActivity.getMyRef().child("user").child(user.getUid()).setValue(new UserDetail(name.getEditText().getText().toString(), no.getEditText().getText().toString(), selected, selectedteh, address.getEditText().getText().toString(), pin.getEditText().getText().toString(),"grahak")).addOnCompleteListener(GrahkDetail.this, task -> {
                   if (task.isSuccessful()) {
                       Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                       //startActivity(new Intent(this, UserDashboard.class));
                       finish();

                   } else {
                       Toast.makeText(this, "Task Fail Plz Submit Again", Toast.LENGTH_SHORT).show();

                   }
               });
           }
        });

    }

    private boolean validateSignupForm() {

        if (!name.getEditText().getText().toString().isEmpty() && !no.getEditText().getText().toString().isEmpty() && !selected.equals("जिला चुने") && !selectedteh.equals("कृपया पहले जिला चुने") && !address.getEditText().getText().toString().isEmpty() && !pin.getEditText().getText().toString().isEmpty()) {
            return true;
        } else if (name.getEditText().getText().toString().isEmpty()) {
            name.getEditText().setError("कृपया नाम लिखे");
        } else if (no.getEditText().getText().toString().isEmpty()) {
            no.getEditText().setError("कृपया फोन नम्बर लिखे");
        } else if (address.getEditText().getText().toString().isEmpty()) {
            address.getEditText().setError("कृपया पता लिखे");
        } else if (pin.getEditText().getText().toString().isEmpty()) {
            pin.getEditText().setError("कृपया पिन कोड लिखे");
        } else if (selected.equals("जिला चुने")){
            Toast.makeText(this, "कृपया जिला चुने", Toast.LENGTH_SHORT).show();
    }
        else if (selectedteh.equals("कृपया पहले जिला चुने")){
            Toast.makeText(this, "कृपया तहसील चुने", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        System.exit(0);
    }
}
