package com.example.android.myinventoryapp;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.example.android.myinventoryapp.data.DatabaseHelper;

public class ViewProduct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
    }

}
