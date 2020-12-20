package com.example.firstgroupassigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.firstgroupassigment.module.Cars;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycler = (RecyclerView)findViewById(R.id.cars_recycler);
        String caption [] = new String[Cars.cars.length];
        int [] ids = new int [Cars.cars.length];
        for (int i=0 ;i<caption.length ; i++){
            caption[i]=Cars.cars[i].getName();
            ids[i]=Cars.cars[i].getImage_id();
        }
        recycler.setLayoutManager(new LinearLayoutManager(this));
        Adappter adappter = new Adappter(ids,caption);
        recycler.setAdapter(adappter);


    }
    public void cardOnclick(View view){
        System.out.println("hiiiii");
        String Des="";
        String name="";
        int imgid =0;
        int id = view.getId();
        for (int i=0 ; i<Cars.cars.length;i++){
            if (id==Cars.cars[i].getImage_id()){
               Des =Cars.cars[i].getDescreption();
               imgid=Cars.cars[i].getImage_id();
               name=Cars.cars[i].getName();
            }
        }
        Intent intent = new Intent(getApplicationContext(),CarsDetails1.class);
        intent.putExtra("desc",Des);
        intent.putExtra("id",imgid);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}