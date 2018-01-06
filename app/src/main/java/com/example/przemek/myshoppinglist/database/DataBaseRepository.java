package com.example.przemek.myshoppinglist.database;

import com.example.przemek.myshoppinglist.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Przemek on 24.11.2017.
 */

public class DataBaseRepository {

    FirebaseDatabase databaseFireBase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = databaseFireBase.getReference();

    public ArrayList<Product> getAllItems(){

        final ArrayList<Product> listResult = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                listResult.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Product product = postSnapshot.getValue(Product.class);
                    listResult.add(product);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return listResult;
    }

    public void addItem(Product product) {
        myRef.child(product.getName()).setValue(product);
    }

    public void removeItem(Product product) {
        myRef.child(product.getName()).removeValue();
    }

    public void updateItem(Product product, String productName) {
        myRef.child(productName).removeValue();
        myRef.child(product.getName()).setValue(product);
    }
}
