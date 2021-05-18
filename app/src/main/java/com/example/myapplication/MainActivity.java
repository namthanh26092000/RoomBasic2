package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private Button btnSave, btnCancel;
    public static EditText editText;
    private List<Place> list;
    private PlaceDatabase db;
    private PlaceDao placeDao;

    public MainActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInfo();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().isEmpty())
                    editText.setError("Vui lòng nhập địa điểm!");
                else
                    savePlace();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

    }

    public void initInfo(){
        recyclerView = findViewById(R.id.rcv_view);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        editText = findViewById(R.id.editValue);

        db = Room.databaseBuilder(getApplicationContext(), PlaceDatabase.class, "place_db")
                .allowMainThreadQueries().build();
        placeDao = db.placeDao();
        list = placeDao.getAll();

        customAdapter = new CustomAdapter(this);
        customAdapter.setData(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);
    }

    public void savePlace(){
        String value = editText.getText().toString().trim();
        Place place = new Place(value);
        placeDao.insertPlace(place);
        list.add(place);
        customAdapter.setData(list);
        Toast.makeText(this, "Add Successfull!", Toast.LENGTH_SHORT).show();
        editText.setText("");
    }

    public void deletePlace(){
        String value = editText.getText().toString().trim();
        Place place = placeDao.findByName(value);
        placeDao.delete(place);
        list.remove(place);
        customAdapter.setData(list);
        Toast.makeText(this, "Add Successfull!", Toast.LENGTH_SHORT).show();
        editText.setText("");
    }

    public void notif(){
        Toast.makeText(this, "Delete Successfull!", Toast.LENGTH_SHORT).show();
    }

    public void editPlace(int id){
        String value = editText.getText().toString().trim();
        placeDao.updatePlace(value, id);
        customAdapter.setData(list);
        Toast.makeText(this, "update Successfull!", Toast.LENGTH_SHORT).show();
    }
}