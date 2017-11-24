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

    public DataBaseRepository(Context context) {
        File mDatabaseFile = context.getDatabasePath("mydb.db").getAbsoluteFile();
        database = SQLiteDatabase.openOrCreateDatabase(mDatabaseFile, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Products(ProductName VARCHAR PRIMARY KEY, Quantity INTEGER, Price NUMERIC, Selected INTEGER);");
    }

    public ArrayList<Product> getAllItems() {
        Cursor cursor = database.rawQuery("select * from Products", null);
        ArrayList<Product> listResult = new ArrayList<>();

        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                String productName = cursor.getString(cursor.getColumnIndex("ProductName"));
                int quantity = cursor.getInt(cursor.getColumnIndex("Quantity"));
                double price = cursor.getDouble(cursor.getColumnIndex("Price"));
                boolean selected = cursor.getInt(cursor.getColumnIndex("Selected")) == 1 ? true : false;
                listResult.add(new Product(productName, quantity, price, selected));
                cursor.moveToNext();
            }
        }

        return listResult;
    }

    public void addItem(Product product) {
        ContentValues insertValues = new ContentValues();
        insertValues.put("ProductName", product.getName());
        insertValues.put("Quantity", product.getQuantity());
        insertValues.put("Price", product.getPrice());
        insertValues.put("Selected", product.isChecked());
        database.insert("Products", null, insertValues);
    }

    public void removeItem(Product product) {
        database.delete("Products", "ProductName = ?", new String[]{product.getName()});
    }

    public void updateItem(Product product, String productName) {
        ContentValues insertValues = new ContentValues();
        insertValues.put("ProductName", product.getName());
        insertValues.put("Quantity", product.getQuantity());
        insertValues.put("Price", product.getPrice());
        insertValues.put("Selected", product.isChecked());
        database.update("Products", insertValues, "ProductName = ?", new String[]{productName});
    }
}
