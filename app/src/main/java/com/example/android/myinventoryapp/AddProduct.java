package com.example.android.myinventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myinventoryapp.data.DatabaseHelper;

import org.w3c.dom.Text;

public class AddProduct extends Activity {

    public int idNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

       final DatabaseHelper db = new DatabaseHelper(this);

        // All Views on AddProduct xml
        final Button addProductBtn = findViewById(R.id.add);
        final EditText prodName = findViewById(R.id.edit_product_name);
        final EditText quantityOfProd = findViewById(R.id.edit_quantity);
        final EditText supplier = findViewById(R.id.supplier);
        final EditText priceOfPRod = findViewById(R.id.price);

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //db.addInventory(new Inventory(0, "Hulk Toy", 3, "R Toy", 5.99));
                if(prodName.getText().toString().isEmpty() || quantityOfProd.getText().toString().isEmpty() || supplier.getText().toString().isEmpty()
                        || priceOfPRod.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "PLEASE FILL OUT THE EMPTY FIELDS!", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    String quValue = quantityOfProd.getText().toString();
                    String doValue = priceOfPRod.getText().toString();
                    int quantity = Integer.parseInt(quValue);
                    double price = Double.parseDouble(doValue);
                    db.addInventory(new Inventory(idNum, prodName.getText().toString(), quantity, supplier.getText().toString(),price));
                    idNum++;
                    prodName.setText("");
                    quantityOfProd.setText("");
                    supplier.setText("");
                    priceOfPRod.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "Product Added!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }



}
