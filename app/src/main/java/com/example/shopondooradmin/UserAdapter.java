package com.example.shopondooradmin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;
//
//public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
//
//    Context context;
//    List<UserModel> userModelList;
//    FirebaseAuth auth;
//    FirebaseDatabase database;
//
//    public UserAdapter(Context context, List<UserModel> userModelList) {
//        this.context = context;
//        this.userModelList = userModelList;
//        auth=FirebaseAuth.getInstance();
//        database=FirebaseDatabase.getInstance();
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        holder.name.setText(userModelList.get(position).getName());
//        holder.email.setText(userModelList.get(position).getEmail());
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        EditText name,email,address,phone;
//        public ViewHolder(@NonNull @NotNull View itemView) {
//            super(itemView);
//            name=itemView.findViewById(R.id.profile_name);
//            address=itemView.findViewById(R.id.profile_address);
//            email=itemView.findViewById(R.id.profile_email);
//            phone=itemView.findViewById(R.id.profile_phone);
//        }
//    }
//}
