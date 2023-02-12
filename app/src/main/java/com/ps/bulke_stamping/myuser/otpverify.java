package com.ps.bulke_stamping.myuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.ps.bulke_stamping.AppActivity;
import com.ps.bulke_stamping.MainActivity;
import com.ps.bulke_stamping.R;
import com.ps.bulke_stamping.myuser.grahak.GrahkDetail;

import java.util.concurrent.TimeUnit;

public class otpverify extends AppCompatActivity {
    private TextView setphone;
    private String mVerificationId,fullotp;
    private EditText otp1,otp2,otp3,otp4,otp5,otp6;
    private Button btnverify;
    private ProgressDialog progressDialog;
    private EditText[] editTexts;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);

        findid();



        Intent intent = getIntent();

        String mobileno = intent.getStringExtra("phoneno");
        String vid = intent.getStringExtra("verifyid");
        setphone.setText(mobileno);

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(((AppActivity)getApplication()).getFirebaseAuth())
                        .setPhoneNumber(String.valueOf(mobileno))       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {

                                Toast.makeText(otpverify.this, "You are login", Toast.LENGTH_SHORT).show();
                                signInWithPhoneAuthCredential(credential);
                                startActivity(new Intent(otpverify.this,UserDeshboard.class));



                            }


                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                // This callback is invoked in an invalid request for verification is made,
                                // for instance if the the phone number format is not valid.

                                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                    // Invalid request
                                } else if (e instanceof FirebaseTooManyRequestsException) {
                                    // The SMS quota for the project has been exceeded
                                }

                                // Show a message and update the UI
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                                mVerificationId = verificationId;
                                System.out.println(verificationId);

                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


        btnverify.setOnClickListener(view ->
        {
            progressDialog=new ProgressDialog(otpverify.this);
            progressDialog.setTitle("कृपया लोगिन होने का इन्तजार करे...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            fullotp= otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString()+otp5.getText().toString()+otp6.getText().toString();

            verifyVerificationCode(fullotp);
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        ((AppActivity)getApplication()).getFirebaseAuth().signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {


                        FirebaseUser user = task.getResult().getUser();
                        System.out.println(user);





                        // Update UI
                        startActivity(new Intent(otpverify.this,UserDeshboard.class));
                        progressDialog.dismiss();

                    } else {
                        // Sign in failed, display a message and update the UI

                        task.getException();// The verification code entered was invalid
                    }
                });
    }

    private void findid(){
        setphone=findViewById(R.id.showphoneno);
        btnverify=findViewById(R.id.enter);
        otp1=findViewById(R.id.otp1);
        otp2=findViewById(R.id.otp2);
        otp3=findViewById(R.id.otp3);
        otp4=findViewById(R.id.otp4);
        otp5=findViewById(R.id.otp5);
        otp6=findViewById(R.id.otp6);
        editTexts = new EditText[]{otp1,otp2,otp3,otp4,otp5,otp6};
        otp1.addTextChangedListener(new PinTextWatcher(0));
        otp2.addTextChangedListener(new PinTextWatcher(1));
        otp3.addTextChangedListener(new PinTextWatcher(2));
        otp4.addTextChangedListener(new PinTextWatcher(3));
        otp5.addTextChangedListener(new PinTextWatcher(4));
        otp6.addTextChangedListener(new PinTextWatcher(5));

        otp1.setOnKeyListener(new PinOnKeyListener(0));
        otp2.setOnKeyListener(new PinOnKeyListener(1));
        otp3.setOnKeyListener(new PinOnKeyListener(2));
        otp4.setOnKeyListener(new PinOnKeyListener(3));
        otp5.setOnKeyListener(new PinOnKeyListener(4));
        otp6.setOnKeyListener(new PinOnKeyListener(5));

      if (otp1.length()==0) { otp1.requestFocus();
      }

      else if (otp2.length()==0)

      { otp2.requestFocus();
      }
      else if (otp3.length()==0)

      { otp3.requestFocus();
      }
      else if (otp4.length()==0)

      { otp4.requestFocus();
      }
      else if (otp5.length()==0)

      { otp5.requestFocus();
      }
      else if (otp6.length()==0)

      { otp6.requestFocus();
      }

    }
    private void verifyVerificationCode(String fullotp) {
        //creating the credential
        System.out.println(mVerificationId);
        System.out.println(fullotp);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, fullotp);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }


    private class PinTextWatcher implements TextWatcher {

        private int currentIndex;
        private boolean isFirst = false, isLast = false;
        private String newTypedString = "";

        PinTextWatcher(int currentIndex) {
            this.currentIndex = currentIndex;

            if (currentIndex == 0)
                this.isFirst = true;
            else if (currentIndex == editTexts.length - 1)
                this.isLast = true;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            newTypedString = s.subSequence(start, start + count).toString().trim();
        }

        @Override
        public void afterTextChanged(Editable s) {

            String text = newTypedString;

            /* Detect paste event and set first char */
            if (text.length() > 1)
                text = String.valueOf(text.charAt(0)); // TODO: We can fill out other EditTexts

            editTexts[currentIndex].removeTextChangedListener(this);
            editTexts[currentIndex].setText(text);
            editTexts[currentIndex].setSelection(text.length());
            editTexts[currentIndex].addTextChangedListener(this);

            if (text.length() == 1)
                moveToNext();
            else if (text.length() == 0)
                moveToPrevious();
        }

        private void moveToNext() {
            if (!isLast)
                editTexts[currentIndex + 1].requestFocus();

            if (isAllEditTextsFilled() && isLast) { // isLast is optional
                editTexts[currentIndex].clearFocus();
                hideKeyboard();
            }
        }

        private void moveToPrevious() {
            if (!isFirst)
                editTexts[currentIndex - 1].requestFocus();
        }

        private boolean isAllEditTextsFilled() {
            for (EditText editText : editTexts)
                if (editText.getText().toString().trim().length() == 0)
                    return false;
            return true;
        }

        private void hideKeyboard() {
            if (getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }

    }

    public class PinOnKeyListener implements View.OnKeyListener {

        private int currentIndex;

        PinOnKeyListener(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (editTexts[currentIndex].getText().toString().isEmpty() && currentIndex != 0)
                    editTexts[currentIndex - 1].requestFocus();
            }
            return false;
        }



    }

}


