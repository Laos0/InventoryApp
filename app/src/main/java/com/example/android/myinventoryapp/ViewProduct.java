package com.example.android.myinventoryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myinventoryapp.data.DatabaseHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewProduct extends Activity {

    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        // ListView
        TextView displayNoList = findViewById(R.id.emptyList);

        // Alert Dialog
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        final ListView inventoryList = findViewById(R.id.inventoryList);

        //display.setText(db.getInventory(2).getName());
        //display.setText(Integer.toString(db.getInventoryCount()));


        final ArrayList<String> myList = new ArrayList<>();

        int num = db.getTotalRows();
        //myList.add(Integer.toString(num));
          for (int i = 1; i <= db.getTotalRows(); i++) {
              myList.add("Name: " + db.getInventory(i).getName() + "  " + " Quantity: " + db.getInventory(i).getQuantity() + "  " +
                      " Price: " + db.getInventory(i).getPrice());
          }

//
//        myList.add("Name: " + db.getInventory(2).getName() + "  " + " Quantity: " + db.getInventory(2).getQuantity() + "  " +
//                " Price: " + db.getInventory(2).getPrice());

        if(myList.isEmpty()){
              displayNoList.setText("The inventory is empty. Go back and add a product to the inventory.");
        }else{
              displayNoList.setText("");
        }

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList);
        inventoryList.setAdapter(arrayAdapter);

        inventoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final int keyPosition = position + 1;
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("What do you want to do?");
                // Order button
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ORDER",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean n = false;
                                if(db.getInventory(position).getQuantity() != 0) {
                                    // There is no key that is 0
                                    // position starts from 0 then increments down the ListView
                                    n = db.updateData(Integer.toString(db.getInventory(keyPosition).getId()),
                                            db.getInventory(keyPosition).getName(),
                                            Integer.toString(db.getInventory(keyPosition).getQuantity() - 1),
                                            db.getInventory(keyPosition).getSupplier(),
                                            Double.toString(db.getInventory(keyPosition).getPrice()));
                                }else{
                                    Toast toast = Toast.makeText(getApplicationContext(), "Out of stock!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                                if(n == true){
                                    arrayAdapter.notifyDataSetChanged(); // update the list if anything was changed
                                    Toast toast = Toast.makeText(getApplicationContext(), "Data Updated!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                dialog.dismiss();
                            }
                        });
                // Don't order button
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Show Image",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder ImageDialog = new AlertDialog.Builder(ViewProduct.this);
                                ImageDialog.setTitle(db.getInventory(keyPosition).getName());
                                ImageView showImage = new ImageView(ViewProduct.this);
                                // ImageView
                                //ImageView img = findViewById(R.id.image);
                                //img.setImageResource(R.drawable.add);
                                showImage.setImageResource(R.drawable.hulk);
                                ImageDialog.setView(showImage);

                                ImageDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface arg0, int arg1)
                                    {
                                    }
                                });
                                ImageDialog.show();
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // CAll delete method here.
                                db.deleteInventory(db.getInventory(keyPosition));
                                // Update the keys
//                                for(int i = 1; i <= db.getTotalRows(); i++) {
//                                    if(db.getInventory(i) == null){
//                                        //Integer.toString(db.getInventory(keyPosition).getQuantity() - 1),
//                                        db.updateKeyId(Integer.toString(db.getInventory(i + 1).getId()));
//                                    }else{
//                                        db.up
//                                    }
//                                }

                                Toast toast = Toast.makeText(getApplicationContext(), "Product Deleted", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                alertDialog.show();
            }
        });


    }

}
