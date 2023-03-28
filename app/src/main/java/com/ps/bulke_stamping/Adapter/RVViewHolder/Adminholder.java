package com.ps.bulke_stamping.Adapter.RVViewHolder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.ps.bulke_stamping.R;

public class Adminholder extends RecyclerView.ViewHolder {

    public AppCompatTextView getOrderno() {
        return orderno;
    }

    public AppCompatTextView getPaise() {
        return paise;
    }

    public AppCompatTextView getNo() {
        return no;
    }


    private final AppCompatTextView orderno,paise,no;

    public Spinner getSpinner() {
        return spinner;
    }

    private final Spinner spinner;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;


    public Adminholder(@NonNull View itemView) {
        super(itemView);
        orderno=itemView.findViewById(R.id.rvoderno);
        paise=itemView.findViewById(R.id.rvrupaystamp);
        no=itemView.findViewById(R.id.rvnoofstamp);
        spinner=itemView.findViewById(R.id.sp);




    }
}

