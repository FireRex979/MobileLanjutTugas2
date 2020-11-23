package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //inisiasi
    EditText nama_ukm_et;
    Button btn_add, btn_reset;
    RecyclerView nama_ukm_rv;

    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nama_ukm_et = findViewById(R.id.nama_ukm_et);
        btn_add = findViewById(R.id.btn_add);
        btn_reset = findViewById(R.id.btn_reset);
        nama_ukm_rv = findViewById(R.id.recycle_view_ukm);

        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();

        linearLayoutManager =  new LinearLayoutManager(this);

        nama_ukm_rv.setLayoutManager(linearLayoutManager);
        adapter =  new MainAdapter(MainActivity.this, dataList);
        nama_ukm_rv.setAdapter(adapter);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName = nama_ukm_et.getText().toString().trim();
                if(!sName.equals("")){
                    MainData data = new MainData();
                    data.setName(sName);
                    database.mainDao().insert(data);
                    nama_ukm_et.setText("");
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.mainDao().reset(dataList);
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}