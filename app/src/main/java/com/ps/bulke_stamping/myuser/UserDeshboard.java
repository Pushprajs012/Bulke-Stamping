package com.ps.bulke_stamping.myuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ps.bulke_stamping.AppActivity;
import com.ps.bulke_stamping.DetailClasses.OderDetail;
import com.ps.bulke_stamping.MainActivity;
import com.ps.bulke_stamping.R;
import com.ps.bulke_stamping.myuser.grahak.GrahkDetail;
import com.ps.bulke_stamping.myuser.grahak.Successoder;
import com.ps.bulke_stamping.myuser.grahak.oldoders;

import java.util.ArrayList;
import java.util.Date;


public class UserDeshboard extends AppCompatActivity {
    private AppActivity appActivity;
    private FirebaseUser user;
    private Spinner spinnerartical;
    private EditText forammount,forno;

    private TextInputLayout etname;
    private ArrayList<String> articlearraylist;
    private ArrayAdapter<String> articlearrayadapter;
    private String selected;
    private Button oderbuttn,finaloder;
    long datacount=0;
    private int stampno,stampammount,extraammount;
    private AlertDialog alertDialogrs;
    private TextView tvammount,tvextracharge,tvtotalammount,tvarticle,tvpartyname,tvstampno,tvpricewithoutextracharge,txtMarquee,txtMarqueetwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_deshboard);

        getSupportActionBar().setTitle("Ps Cyber Cafe");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbar)));


        initobject();
        txtMarquee.setSelected(true);
        spinnerartical.setAdapter(articlearrayadapter);

        appActivity.getMyRef().child("Article").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                articlearraylist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    articlearraylist.add(dataSnapshot.getValue().toString());

                }
                articlearrayadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spinnerartical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected=articlearraylist.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       try{
            if (appActivity.getUserDetail().getPin()==null){

            }

        }

        catch (Exception e) {
            cheakuser();
        }

    }

    private void cheakuser(){

            AlertDialog alertDialog=new AlertDialog.Builder(UserDeshboard.this)
                    .setView(R.layout.alertdialoguserinfo)
                    .setInverseBackgroundForced(false)
                    .setCancelable(false)
                    .show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ConstraintLayout layout = alertDialog.findViewById(R.id.alertlayout);

            AnimationDrawable animationDrawable = (AnimationDrawable)
                    layout.getBackground();
            animationDrawable.setEnterFadeDuration(500);
            animationDrawable.setExitFadeDuration(1000);
            animationDrawable.start();

            alertDialog.findViewById(R.id.alersubmitbtn).setOnClickListener(view -> {
                alertDialog.cancel();
                startActivity(new Intent(UserDeshboard.this, GrahkDetail.class));

            });


    }

    private void initobject(){
        appActivity=(AppActivity) getApplication();
        etname=findViewById(R.id.enterpartyname);
        forammount=findViewById(R.id.ammount);
        txtMarquee = findViewById(R.id.name);
        forno=findViewById(R.id.noofestamp);
        oderbuttn=findViewById(R.id.oderestamp);
        spinnerartical=findViewById(R.id.spinnerforarticle);
        user = appActivity.getFirebaseAuth().getCurrentUser();
        articlearraylist=new ArrayList<>();
        articlearrayadapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, articlearraylist);
        articlearrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Aleart


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        oderbuttn.setOnClickListener(view -> {
            cheakfild();
            if (cheakfild()==true) {
                alertDialogrs = new AlertDialog.Builder(UserDeshboard.this)
                        .setView(R.layout.layoutforconfirm)
                        .setInverseBackgroundForced(false)
                        .show();
                alertDialogrs.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                ConstraintLayout layout = alertDialogrs.findViewById(R.id.stampconfirmlayout);

                AnimationDrawable animationDrawable = (AnimationDrawable)
                        layout.getBackground();
                animationDrawable.setEnterFadeDuration(500);
                animationDrawable.setExitFadeDuration(1000);
                animationDrawable.start();
                tvarticle = alertDialogrs.findViewById(R.id.tvfornameartivle);
                tvpartyname = alertDialogrs.findViewById(R.id.tvforname);
                tvstampno = alertDialogrs.findViewById(R.id.tvnoestamp);
                tvammount = alertDialogrs.findViewById(R.id.rsstamp);
                tvextracharge = alertDialogrs.findViewById(R.id.tvextracharge);
                tvpricewithoutextracharge = alertDialogrs.findViewById(R.id.tvrupay);
                tvtotalammount = alertDialogrs.findViewById(R.id.tvtotalrupay);
                finaloder = alertDialogrs.findViewById(R.id.oderbtn);
                txtMarqueetwo = alertDialogrs.findViewById(R.id.tvname);



                tvarticle.setText(selected);
                txtMarqueetwo.setSelected(true);
                tvpartyname.setText(etname.getEditText().getText().toString());
                tvstampno.setText(forno.getText().toString());
                tvammount.setText("₹" + forammount.getText().toString());
                tvpricewithoutextracharge.setText(String.valueOf(stampammount * stampno));

                if (stampammount == 10) {
                    extraammount = 5 * stampno;
                } else {
                    extraammount = 10 * stampno;
                }
                tvextracharge.setText("₹" + String.valueOf(extraammount));
                int tammount = (extraammount + (stampno * stampammount));
                tvtotalammount.setText("₹" + String.valueOf(tammount));

                finaloder.setOnClickListener(view1 -> {

                   oder();

                });
            }

        });



    }

    private boolean cheakfild(){

        if (!etname.getEditText().getText().toString().isEmpty() && !forno.getText().toString().isEmpty() &&  !forammount.getText().toString().isEmpty()){
            stampno=Integer.parseInt(forno.getText().toString());
            System.out.println("true, kuch khali nhi h");
            stampammount=Integer.parseInt(forammount.getText().toString());

            System.out.println(String.valueOf(stampammount+"ammorut"));
            System.out.println(String.valueOf(stampno+"no"));
            System.out.println(etname.getEditText().getText().toString());
            if (stampno>=30 && stampammount >=10 && stampammount<=210 && !selected.equals("Article चुने")){
                System.out.println("true, sb barabar h");

            return true;}
            else if (stampammount<=9){
                forammount.setError("कृपया ₹10 से ऊपर लिखे"); }
            else if (stampammount>=211){
                forammount.setError("कृपया ₹210 से नीचे लिखे"); }
            else if (stampno<=29){
                forno.setError("कम से कम 30 ईस्टाम्प ही ऑडर किये जा सकते है ।");
            }
            else
                System.out.println("false kuch khali h");

            return false;
        }
        else if (etname.getEditText().getText().toString().isEmpty()){
            etname.setError("कृपया पार्टी का नाम लिखे");

        }
        else if (forammount.getText().toString().isEmpty()){
            forammount.setError("कृपया पैसे लिखे");}
       
        else if (forno.getText().toString().isEmpty())
        {
            forno.setError("कितने ईस्टाम्प खरीदने है? कृपया लिखे");
        }
       

        return false;
    }


public void oder() {
        //OderDetail oderDetail= new OderDetail(appActivity.getFirebaseAuth().getUid(),etname.getEditText().getText().toString(),String.valueOf(stampno),String.valueOf(stampammount),new Date().toString(),String.valueOf(cheakchild()+1));


        appActivity.getMyRef().child("Oder").child(String.valueOf(cheakchild()+1)).setValue(new OderDetail(appActivity.getFirebaseAuth().getUid(),etname.getEditText().getText().toString(),String.valueOf(stampno),String.valueOf(stampammount),new Date().toString(),String.valueOf(datacount+1))).addOnCompleteListener(task -> {
            Intent successIntent = new Intent(this, Successoder.class);

            if (task.isSuccessful()){
                Toast.makeText(this, "Oder Successful", Toast.LENGTH_LONG).show();
                successIntent.putExtra("odernumber", String.valueOf(datacount));
                System.out.println(cheakchild()+"child");
                successIntent.putExtra("istrue",true);
                startActivity(successIntent);

            }
            else {
                Toast.makeText(this, "Oder Unsuccessful", Toast.LENGTH_LONG).show();
                successIntent.putExtra("odernumber", "null");
                successIntent.putExtra("istrue",false);
                startActivity(successIntent);
            }
        });

}

private long cheakchild() {

        appActivity.getMyRef().child("Oder").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if (snapshot.exists())
               {
                   datacount=(snapshot.getChildrenCount());

               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

      return datacount;
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.customermenu,menu);
        menu.getItem(4).setEnabled(false);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.coder:

                break;
            case R.id.lastoder:
            startActivity(new Intent(getApplicationContext(), oldoders.class));
                break;
            case R.id.aboutus:

                break;
            case R.id.help:
                try {
                    String mobile = "917088767698";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile)));
                }catch (Exception e){
                    Toast.makeText(this, "Please Install Whatsapp", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.logout:
                appActivity.getFirebaseAuth().getInstance().signOut();
                    startActivity(new Intent(this, MainActivity.class));
            break;
        }
        return super.onOptionsItemSelected(item);

    }


}