package com.ps.bulke_stamping.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ps.bulke_stamping.Adapter.RVViewHolder.Adminholder;
import com.ps.bulke_stamping.Admin.AdminOrder;
import com.ps.bulke_stamping.DetailClasses.OrderDetail;
import com.ps.bulke_stamping.R;

import java.util.ArrayList;

public class AdminOrderAdapter extends RecyclerView.Adapter<Adminholder> {
    private int lposs=-1;

    private ArrayList<OrderDetail> arrayList;
   private AdminOrder adminOrder;
    public AdminOrderAdapter(ArrayList<OrderDetail> rvOrders, AdminOrder adminOrder) {
            this.arrayList=rvOrders;
            this.adminOrder = adminOrder;
    }

    @NonNull
    @Override
    public Adminholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.rvladminorders,parent,false);
       return new Adminholder(view);
    }

    @Override
    public void onBindViewHolder(Adminholder holder, int position) {
        holder.getOrderno().setText(arrayList.get(position).getOderno());
        holder.getNo().setText(arrayList.get(position).getEtsampno());
        holder.getPaise().setText(arrayList.get(position).getEstampprice());
        setAnimation(holder.itemView, position);



        holder.getSpinner().setAdapter(new ArrayAdapter<>(adminOrder, android.R.layout.simple_list_item_1, new String[]{"New", "Printing", "Complete", "Cancelled"}));

        holder.getSpinner().setSelection(arrayList.get(position).getStatus());




        holder.getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                holder.getSpinner().setSelection(arrayList.get(position).setStatus(i));
              //  adminOrder.orderDetail.setStatus(holder.getSpinner().getSelectedItemPosition());


               adminOrder.getAppActivity().getMyRef().child("Order").child(arrayList.get(position).getOderno()).setValue(new OrderDetail(arrayList.get(position).getGrahakuid(),arrayList.get(position).getEstamparticle(),arrayList.get(position).getOderno(),arrayList.get(position).getEtsampno(),arrayList.get(position).getEstampprice(),arrayList.get(position).getDatetime(),arrayList.get(position).getPartyname(),i)).addOnCompleteListener(task -> {
                   if (task.isSuccessful()){
                       System.out.println(adminOrder.orderDetail+"safal");

                       Toast.makeText(adminOrder, "Status Change", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       Toast.makeText(adminOrder, "Try Again", Toast.LENGTH_SHORT).show();
                   }
               });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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
