package com.guy.class23b_and_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Activity_List extends AppCompatActivity {

    private ListView list_LST_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        list_LST_names = findViewById(R.id.list_LST_names);


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.list_country, DataManager.COUNTRIES);
        list_LST_names.setAdapter(arrayAdapter);

        list_LST_names.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Activity_List.this, position + " - " + DataManager.COUNTRIES[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
