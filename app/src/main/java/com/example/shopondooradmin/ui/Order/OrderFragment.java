package com.example.shopondooradmin.ui.Order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopondooradmin.OrderAdapter;
import com.example.shopondooradmin.OrderModel;
import com.example.shopondooradmin.R;
import com.example.shopondooradmin.ui.UseridModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderFragment  extends Fragment{
    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseDatabase database;
    List<UseridModel> useridModellist;
    RecyclerView order_recyclerView;
    OrderAdapter viewOrderAdapter;
    List<OrderModel> viewOrderModelList;
    ConstraintLayout constraintLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_order, container, false);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        useridModellist=new ArrayList<>();
        constraintLayout=root.findViewById(R.id.my_order_constraint);
        constraintLayout.setVisibility(View.VISIBLE);
        order_recyclerView=root.findViewById(R.id.order_rec);
        order_recyclerView.setVisibility(View.GONE);
        order_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));




        viewOrderModelList=new ArrayList<>();
        viewOrderAdapter=new OrderAdapter(getActivity(),viewOrderModelList,useridModellist);
        order_recyclerView.setAdapter(viewOrderAdapter);


        db.collection("UserId")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                UseridModel useridModel=document.toObject(UseridModel.class);
                                useridModellist.add(useridModel);
                                String id=useridModel.getUid();
                                String uid=id.trim();
                                db.collection("CurrentUser").document(uid)
                                        .collection("MyOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()) {
                                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                                String documentId = documentSnapshot.getId();

                                                OrderModel viewOrderModel = documentSnapshot.toObject(OrderModel.class);
                                                viewOrderModel.setDocumentId(documentId);
                                                viewOrderModelList.add(viewOrderModel);
                                                viewOrderAdapter.notifyDataSetChanged();
                                                order_recyclerView.setVisibility(View.VISIBLE);
                                                constraintLayout.setVisibility(View.GONE);
                                            }

                                        } else {
                                            Toast.makeText(getContext(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            Collections.sort(viewOrderModelList, new Comparator<OrderModel>() {
                                @Override
                                public int compare(OrderModel o1, OrderModel o2) {
                                    return o1.getCurrentDate().compareTo(o2.getCurrentDate());
                                }
                            });
                            Collections.sort(viewOrderModelList, new Comparator<OrderModel>() {
                                @Override
                                public int compare(OrderModel o1,OrderModel o2) {
                                    return o1.getCurrentTime().compareTo(o2.getCurrentTime());
                                }
                            });
                        } else {
                            Toast.makeText(getActivity(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }

}
