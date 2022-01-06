package com.example.shopondooradmin.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shopondooradmin.R;
import com.example.shopondooradmin.databinding.FragmentHomeBinding;
import com.google.common.base.MoreObjects;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {
    ToggleButton toggleButton;
    TextView open,closed;
    FirebaseAuth auth;
    FirebaseDatabase database;
    String openStatus;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_home,container,false);

        toggleButton= root.findViewById(R.id.toggle);
        open= root.findViewById(R.id.open);
        closed=root.findViewById(R.id.closed);
        open.setVisibility(View.GONE);
        closed.setVisibility(View.VISIBLE);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("save"
        , Context.MODE_PRIVATE);
        toggleButton.setChecked(sharedPreferences.getBoolean("value",true));

        if(toggleButton.isChecked()){
            open.setVisibility(View.VISIBLE);
            closed.setVisibility(View.GONE);
        }
        else{
            open.setVisibility(View.GONE);
            closed.setVisibility(View.VISIBLE);
        }

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked()){
                    open.setVisibility(View.VISIBLE);
                    closed.setVisibility(View.GONE);
                    database.getReference().child("Admin").child(auth.getCurrentUser().getUid()).child("openStatus").setValue("Open");
                SharedPreferences.Editor editor=getActivity().getSharedPreferences("save"
                        , Context.MODE_PRIVATE).edit();
                editor.putBoolean("value",true);
                editor.apply();
                toggleButton.setChecked(true);
                }
                else {
                    open.setVisibility(View.GONE);
                    closed.setVisibility(View.VISIBLE);
                    database.getReference().child("Admin").child(auth.getCurrentUser().getUid()).child("openStatus").setValue("Closed");
                    SharedPreferences.Editor editor=getActivity().getSharedPreferences("save"
                            , Context.MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    toggleButton.setChecked(false);
                }
            }
        });
        return root;
    }
}