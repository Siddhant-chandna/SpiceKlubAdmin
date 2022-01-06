package com.example.shopondooradmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private static final String[] paths = {"item 1", "item 2", "item 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Toast.makeText(ListActivity.this, ""+"Ord", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(ListActivity.this, ""+"skk", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(ListActivity.this, ""+"eee", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(ListActivity.this, ""+"vvv", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(ListActivity.this, ""+"badbad", Toast.LENGTH_SHORT).show();
    }
}