package com.example.przemek.myshoppinglist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.przemek.myshoppinglist.model.Product;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Przemek on 24.11.2017.
 */

public class DataBaseRepository {
    private final SQLiteDatabase database;

    public DataBaseRepository(Context context){
        File mDatabaseFile = context.getDatabasePath("smb.db").getAbsoluteFile();
        database = SQLiteDatabase.openOrCreateDatabase(mDatabaseFile, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS CartItems(ItemName VARCHAR PRIMARY KEY, Quantity INTEGER, Price NUMERIC, Selected INTEGER);");
    }

    public ArrayList<Product> GetAllItems(){
        Cursor cursor = database.rawQuery("select * from CartItems", null);
        ArrayList<Product> listResult = new ArrayList<>();

        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                String itemName = cursor.getString(cursor.getColumnIndex("ItemName"));
                int quantity = cursor.getInt(cursor.getColumnIndex("Quantity"));
                double price = cursor.getDouble(cursor.getColumnIndex("Price"));
                boolean selected = cursor.getInt(cursor.getColumnIndex("Selected")) == 1 ? true : false;
                listResult.add(new Product(itemName, quantity, price, selected));
                cursor.moveToNext();
            }
        }

        return listResult;
    }

    public void AddItem(Product product){
        ContentValues insertValues = new ContentValues();
        insertValues.put("ItemName", product.getName());
        insertValues.put("Quantity", product.getQuantity());
        insertValues.put("Price", product.getPrice());
        insertValues.put("Selected", product.isChecked());
        database.insert("CartItems", null, insertValues);
    }

    public void RemoveItem(Product product){
        database.delete("CartItems", "ItemName = ?", new String[]{product.getName()});
    }

    public void UpdateItem(Product product, String itemName) {
        ContentValues insertValues = new ContentValues();
        insertValues.put("ItemName", product.getName());
        insertValues.put("Quantity", product.getQuantity());
        insertValues.put("Price", product.getPrice());
        insertValues.put("Selected", product.isChecked());
        database.update("CartItems", insertValues, "ItemName = ?", new String[]{itemName});
    }
}
