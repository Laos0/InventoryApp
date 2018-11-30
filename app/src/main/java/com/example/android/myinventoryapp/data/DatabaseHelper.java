package com.example.android.myinventoryapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.android.myinventoryapp.Inventory;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
        // Database version
        private static final int DATABASE_VERSION = 3;
        // Database name
        private static final String DATABASE_NAME = "InventoryInfo";
        // Contact table name
        private static final String TABLE_INVENTORY = "inventory";
        // Inventory table column names
        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";
        private static final String KEY_QUANTITY = "quantity";
        private static final String KEY_SUPPLIER = "supplier";
        private static final String KEY_PRICE = "price";

    public DatabaseHelper(Context context) {
        super(context, TABLE_INVENTORY, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_INVENTORY + "("
//                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
//                + KEY_QUANTITY + " INTEGER," + KEY_SUPPLIER + " TEXT,"
//                + KEY_PRICE + " DOUBLE" + ")";

        String CREATE_INVENTORY_TABLE = "CREATE TABLE " + TABLE_INVENTORY + " ("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_QUANTITY + " INTEGER," +
                KEY_SUPPLIER + " TEXT," + KEY_PRICE + " DOUBLE" + ")";

        db.execSQL(CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        // Creating tables again
        onCreate(db);
    }

    // Adding new inventory
    public void addInventory(Inventory inventory){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, inventory.getName()); // Inventory name
        values.put(KEY_QUANTITY, inventory.getQuantity()); // Inventory quantity
        values.put(KEY_SUPPLIER, inventory.getSupplier()); // Inventory supplier
        values.put(KEY_PRICE, inventory.getPrice()); // Inventory price

        // inserting row
        db.insert(TABLE_INVENTORY, null, values);
        // Closing database connection
        db.close();
    }

    // Getting one inventory
    public Inventory getInventory(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INVENTORY, new String[]{KEY_ID, KEY_NAME, KEY_QUANTITY, KEY_SUPPLIER, KEY_PRICE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        // Inventory(int id, String name, int quantity, String supplier, double price)
        Inventory product = new Inventory(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Integer.parseInt(cursor.getString(2)),
                cursor.getString(3), Double.parseDouble(cursor.getString(4)));

        return product;
    }

    // Getting All Inventory
    public List<Inventory> getAllInventory(){
        List<Inventory> inventoryList = new ArrayList<Inventory>();
        // Selecting ALL Query
        String selectQuery = "SELECT * FROM " + TABLE_INVENTORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Inventory inventory = new Inventory();
                inventory.setId(Integer.parseInt(cursor.getString(0)));
                inventory.setName(cursor.getString(1));
                inventory.setQuantity(Integer.parseInt(cursor.getString(2)));
                inventory.setSupplier(cursor.getString(3));
                inventory.setPrice((Double.parseDouble(cursor.getString(4))));
                // Adding products to list
                inventoryList.add(inventory);
            }while(cursor.moveToNext());
        }
        // return product list
        return inventoryList;
    }

    // getting inventory count
    public int getInventoryCount(){
        String countQuery = "SELECT * FROM " + TABLE_INVENTORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    public int getTotalRows(){
       SQLiteDatabase db = this.getReadableDatabase();
       long count = DatabaseUtils.queryNumEntries(db, TABLE_INVENTORY);
       int counter = (int) count;
       db.close();
       return counter;
    }

    // Updating a inventory
    public int updateInventory(Inventory inventory){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, inventory.getName()); // Inventory name
        values.put(KEY_QUANTITY, inventory.getQuantity()); // Inventory quantity
        values.put(KEY_SUPPLIER, inventory.getSupplier()); // Inventory supplier
        values.put(KEY_PRICE, inventory.getPrice()); // Inventory price

        // updating row
        return db.update(TABLE_INVENTORY, values, KEY_ID + "=?", new String[]{String.valueOf(inventory.getId())});
    }

    // deleting an inventory
    public void deleteInventory(Inventory inventory){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INVENTORY, KEY_ID + "=?",
                new String[]{String.valueOf(inventory.getId())});
        db.close();
    }

    // Update Quantity
    public boolean updateData(String id, String name, String quantity, String supplier, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_QUANTITY, quantity);
        contentValues.put(KEY_SUPPLIER, supplier);
        contentValues.put(KEY_PRICE, price);
        db.update(TABLE_INVENTORY, contentValues, "id = ?", new String[] { id });
        return true;
    }

    public void updateKeyId(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, id);
        db.update(TABLE_INVENTORY, contentValues, "id = ?", new String[] { id });
    }

}
