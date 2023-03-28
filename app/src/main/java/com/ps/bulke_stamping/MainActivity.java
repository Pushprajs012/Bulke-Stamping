package com.ps.bulke_stamping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.ps.bulke_stamping.myuser.UserDashboard;
import com.ps.bulke_stamping.myuser.otpverify;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
        private Button otpbtn;
        private String  phoneno;
        private EditText phone;
        private ProgressDialog progressDialog;
        private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setSubtitle("For Buy eStamp In Bulk");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbar)));
        phone = findViewById(R.id.entermobileno);
        otpbtn=findViewById(R.id.forotp);

       googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.CREDENTIALS_API)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                .build();
        googleApiClient.connect();

        getnumber();



        otpbtn.setOnClickListener(view -> {
            if (phone.length()!=10) {
                phone.setError("Please enter a valid phone number");



                // displaying a toast message.
                Toast.makeText(MainActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
            } else {
                // if the text field is not empty we are calling our
                // send OTP method for getting OTP from Firebase.
                Intent i = new Intent(this, otpverify.class);

                phoneno = "+91" + phone.getText().toString();
                i.putExtra("phoneno", phoneno);
                startActivity(i);
            }

        });






        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient singhClint= GoogleSignIn.getClient(this,gso);


        findViewById(R.id.google).setOnClickListener(view -> {

            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("कृपया लोगिन होने का इन्तजार करे...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        Intent i= singhClint.getSignInIntent();
        startActivityForResult(i,100);

        });

        FirebaseUser firebaseUser=((AppActivity)getApplication()).getFirebaseAuth().getCurrentUser();
        // Check condition
        if(firebaseUser!=null)
        {
           // startActivity(new Intent(MainActivity.this,ProfileActivity.class)
            //        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                com.google.android.gms.auth.api.credentials.Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                if (credential != null) {

                    String myphoneno = credential.getId();
                    int autono=myphoneno.length()-10;
                   phoneno=myphoneno.substring(autono);
                    phone.setText(phoneno);

                }
            }
        }
        else if (requestCode == 100) {
                Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);

                try {
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                    ((AppActivity) getApplication()).getFirebaseAuth().signInWithCredential(authCredential)
                            .addOnSuccessListener(MainActivity.this, authResult -> {
                                Toast.makeText(this, "okkk", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, UserDashboard.class));
                                progressDialog.dismiss();


                            })
                            .addOnFailureListener(MainActivity.this, authResult -> {
                                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();

                            });
                } catch (ApiException e) {
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }
            }
        }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void getnumber(){

        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();
        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(),
                    10, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }

    }
}

