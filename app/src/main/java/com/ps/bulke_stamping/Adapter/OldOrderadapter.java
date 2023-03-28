package com.ps.bulke_stamping.Adapter;




import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ps.bulke_stamping.DetailClasses.OrderDetail;
import com.ps.bulke_stamping.R;
import com.ps.bulke_stamping.Adapter.RVViewHolder.Oldorderholder;
import com.ps.bulke_stamping.myuser.grahak.oldorders;

import java.util.ArrayList;

public class OldOrderadapter extends RecyclerView.Adapter<Oldorderholder> {
    private int lposs=-1;

    private ArrayList<OrderDetail> arrayList;
   private oldorders oldorders;
    public OldOrderadapter(ArrayList<OrderDetail> rvOrders, oldorders oldorders) {
            this.arrayList=rvOrders;
            this.oldorders=oldorders;
    }


    @NonNull
    @Override
    public Oldorderholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.rvlastorders,parent,false);
       return new Oldorderholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Oldorderholder holder, int position) {
        holder.getOrderno().setText(arrayList.get(position).getOderno());
        holder.getNo().setText(arrayList.get(position).getEtsampno());
        holder.getPaise().setText(arrayList.get(position).getEstampprice());
        holder.getPartyname().setText(arrayList.get(position).getPartyname());
        setAnimation(holder.itemView, position);
                if (arrayList.get(position).getStatus()==0) {
                    holder.getPrintstatus().setText("New");
                    holder.getBtn1().startAnimation(AnimationUtils.loadAnimation(oldorders, R.anim.bubble));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        holder.getProgressIndicator().setProgress(20,true);
                    }
                } else if (arrayList.get(position).getStatus()==1) {
                    holder.getPrintstatus().setText("Printing");
                    holder.getBtn2().startAnimation(AnimationUtils.loadAnimation(oldorders, R.anim.bubble));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        holder.getProgressIndicator().setProgress(60,true);
                    }
                } else if (arrayList.get(position).getStatus()==2) {
                    holder.getPrintstatus().setText("Complete");
                    holder.getBtn3().startAnimation(AnimationUtils.loadAnimation(oldorders, R.anim.bubble));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        holder.getProgressIndicator().setProgress(100,true);
                    }
                } else if (arrayList.get(position).getStatus()==3) {
                    holder.getPrintstatus().setText("Cancel");
                    holder.getBtn3().startAnimation(AnimationUtils.loadAnimation(oldorders, R.anim.bubble));
                    holder.getProgressIndicator().setProgress(100);
                    holder.getProgressIndicator().setIndicatorColor(oldorders.getResources().getColor(R.color.Red));
                }
                else   {holder.getPrintstatus().setText("Error");
                    holder.getBtn3().startAnimation(AnimationUtils.loadAnimation(oldorders, R.anim.bubble));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        holder.getProgressIndicator().setProgress(100,true);
                    }
                }
        holder.getCancelbtn().setOnClickListener(view -> {
            holder.getPrintstatus().setText("Cancel");
            holder.getBtn3().startAnimation(AnimationUtils.loadAnimation(oldorders, R.anim.bubble));
            holder.getBtn1().clearAnimation();
            holder.getBtn2().clearAnimation();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.getProgressIndicator().setProgress(100,true);
            }
            holder.getProgressIndicator().setIndicatorColor(oldorders.getResources().getColor(R.color.Red));
            oldorders.getAppActivity().getMyRef().child("Order").child(arrayList.get(position).getOderno()).setValue(new OrderDetail(arrayList.get(position).getGrahakuid(),arrayList.get(position).getEstamparticle(),arrayList.get(position).getOderno(),arrayList.get(position).getEtsampno(),arrayList.get(position).getEstampprice(),arrayList.get(position).getDatetime(),arrayList.get(position).getPartyname(),3)).addOnCompleteListener(task -> {

                if (task.isSuccessful()){
                    Toast.makeText(oldorders, "ऑडर केंसिल", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(oldorders, "फिर से कोशिश करे", Toast.LENGTH_LONG).show();

                }
            });

        });


        if (arrayList.get(position).getStatus()==3||arrayList.get(position).getStatus()==2){

            holder.getCancelbtn().setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private void setAnimation(View view, int pos)
    {
        if (lposs<pos) {
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left);
            view.setAnimation(animation);
            lposs=pos;
        }
    }

}
