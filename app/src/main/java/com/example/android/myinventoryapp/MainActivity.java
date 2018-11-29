package com.example.android.myinventoryapp;

import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.android.myinventoryapp.data.DatabaseHelper;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiating DatabaseHelper
        DatabaseHelper db = new DatabaseHelper(this);

        // Declarations of views
        Button addBtn = findViewById(R.id.addProduct);
        Button viewBtn = findViewById(R.id.viewProduct);
        final LinearLayout layout = findViewById(R.id.main);

        //Inventory(int id, String name, int quantity, String supplier, double price)
        // Inserting Inventory/Row
        Log.d("Insert", "Inserting...");
        db.addInventory(new Inventory(0, "Hulk Toy", 3, "R Toy", 5.99));
        db.addInventory(new Inventory(1, "Spider Toy", 3, "R Toy", 3.99));
        db.addInventory(new Inventory(2, "Cat Toy", 3, "R Toy", 2.99));
        db.addInventory(new Inventory(3, "Dog Toy", 3, "R Toy", 1.99));

        // Reading all inventory
        Log.d("Reading: ", "Reading all inventories...");
        List<Inventory> inventory = db.getAllInventory();

        for(Inventory inventories : inventory){
            String log = "id: " + inventories.getId() + " ,Name: " + inventories.getName() + " ,Quantity: " + inventories.getQuantity()
                    + " ,Supplier: " + inventories.getSupplier() + " ,Price: " + inventories.getPrice();
            // writing inventories to log
            Log.d("Inventory::", log);
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddProduct.class));
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewProduct.class));
            }
        });
    }
}
