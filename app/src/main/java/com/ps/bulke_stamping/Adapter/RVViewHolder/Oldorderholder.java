package com.ps.bulke_stamping.Adapter.RVViewHolder;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.ps.bulke_stamping.R;


public class Oldorderholder extends RecyclerView.ViewHolder {

    public AppCompatTextView getOrderno() {
        return orderno;
    }

    public AppCompatTextView getPaise() {
        return paise;
    }

    public AppCompatTextView getNo() {
        return no;
    }

    public AppCompatButton getCancelbtn() {
        return cancelbtn;
    }

    private final AppCompatTextView orderno;
    private final AppCompatTextView paise;
    private final AppCompatTextView no;

    public AppCompatTextView getPartyname() {
        return partyname;
    }

    public Button getBtn1() {
        return btn1;
    }

    public void setBtn1(Button btn1) {
        this.btn1 = btn1;
    }

    public Button getBtn2() {
        return btn2;
    }

    public void setBtn2(Button btn2) {
        this.btn2 = btn2;
    }

    public Button getBtn3() {
        return btn3;
    }

    public void setBtn3(Button btn3) {
        this.btn3 = btn3;
    }

    private AppCompatTextView partyname;

    public AppCompatTextView getPrintstatus() {
        return printstatus;
    }

    public LinearProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

    public void setProgressIndicator(LinearProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    private AppCompatTextView printstatus;
  private final AppCompatButton cancelbtn;
  private Button btn1,btn2,btn3;
  private LinearProgressIndicator progressIndicator;

    public Oldorderholder(@NonNull View itemView) {
        super(itemView);
        orderno=itemView.findViewById(R.id.rvoderno);
        paise=itemView.findViewById(R.id.rvrupaystamp);
        no=itemView.findViewById(R.id.rvnoofstamp);
        btn1=itemView.findViewById(R.id.btn1);
        btn2=itemView.findViewById(R.id.btn2);
        btn3=itemView.findViewById(R.id.btn3);
        partyname=itemView.findViewById(R.id.rvppartyname);
        cancelbtn=itemView.findViewById(R.id.cancel);
        printstatus=itemView.findViewById(R.id.status);
        progressIndicator=itemView.findViewById(R.id.progressBar);
    }
}

