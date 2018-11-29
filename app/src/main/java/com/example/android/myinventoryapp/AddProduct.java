package com.example.android.myinventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.myinventoryapp.data.DatabaseHelper;

import org.w3c.dom.Text;

public class AddProduct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // All Views on AddProduct xml
        final Button addProductBtn = findViewById(R.id.add);
        final EditText prodName = findViewById(R.id.edit_product_name);
        final EditText quantityOfProd = findViewById(R.id.edit_quantity);
        final EditText supplier = findViewById(R.id.supplier);
        final EditText priceOfPRod = findViewById(R.id.price);

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }

}
