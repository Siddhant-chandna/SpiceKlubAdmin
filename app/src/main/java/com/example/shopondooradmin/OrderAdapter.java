package com.example.shopondooradmin;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopondooradmin.ui.UseridModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    List<OrderModel> OrderModellist;
    List<UseridModel> useridModelList;

    public OrderAdapter(Context context, List<OrderModel> orderModellist, List<UseridModel> useridModelList) {
        this.context = context;
        OrderModellist = orderModellist;
        this.useridModelList = useridModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new OrderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_order,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(OrderModellist.get(position).getProductImage()).into(holder.imageView);
        holder.name.setText(OrderModellist.get(position).getProductName());
        holder.price.setText(OrderModellist.get(position).getProductPrice());
        holder.quantity.setText(OrderModellist.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(OrderModellist.get(position).getTotaldiscountPrice()));
        holder.time.setText(OrderModellist.get(position).getCurrentTime());
        holder.date.setText(OrderModellist.get(position).getCurrentDate());
        holder.orderStatus.setText(OrderModellist.get(position).getOrderStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatusDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return OrderModellist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price,quantity,totalPrice,time,date,orderStatus;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.order_img);
            name=itemView.findViewById(R.id.order_name);
            price=itemView.findViewById(R.id.order_price);
            quantity=itemView.findViewById(R.id.order_quantity);
            totalPrice=itemView.findViewById(R.id.order_total_price);
            time=itemView.findViewById(R.id.order_time);
            date=itemView.findViewById(R.id.order_date);
            orderStatus=itemView.findViewById(R.id.orderStatus);

        }
    }
    private void showStatusDialog(int position) {
        final String[] status={"Order Recieved","Cooking","Reaching You","Delivered"};
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Update Order Status");
        int i=0;
        String S=OrderModellist.get(position).getOrderStatus().trim();
        if(S.equals("Cooking")){
            i=1;
        }
        else if(S.equals("Reaching You")){
            i=2;
        }
        else if(S.equals("Delivered")){
            i=3;
        }

        builder.setSingleChoiceItems(status, i, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selected=status[which];
                String Status=selected.trim();

                String id=OrderModellist.get(position).getDocumentId();
                String oid=id.trim();

                for(UseridModel model:useridModelList){
                    String uuid=model.getUid();
                    String uid=uuid.trim();

                    FirebaseFirestore.getInstance().collection("CurrentUser").document(uid)
                            .collection("MyOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    String documentId = documentSnapshot.getId();
                                    String docId=documentId.trim();

                                    if(docId.equals(oid)){
                                        FirebaseFirestore.getInstance().collection("CurrentUser").document(uid)
                                                .collection("MyOrder").document(docId).update("orderStatus",Status).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                    }
                                }
                            }
                        }
                    });
                }

            }
        });
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}