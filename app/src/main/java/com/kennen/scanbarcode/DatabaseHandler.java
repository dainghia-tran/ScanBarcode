package com.kennen.scanbarcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final String DB_NAME = "PRICE_MANAGER";
    private static final int DB_VER = 1;
    private static final String TABLE_NAME = "PRICE";

    //private static final String KEY_ID = "id";
    private static final String CODE = "barcode";
    private static final String PRICE = "price";

    public DatabaseHandler(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create Table if not exists " + TABLE_NAME + " (" + CODE + " Text, " + PRICE + " Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("Drop Table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public long Insert(Product product)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CODE, product.getBarCode());
        values.put(PRICE, product.getPrice());

        return db.insert(TABLE_NAME, null, values);
    }

    public long Update(Product product)
    {
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CODE, product.getBarCode());
        values.put(PRICE, product.getPrice());

        String selection = CODE + " = ?";
        String[] selectionArgs = {product.getBarCode()};

        return db.update(TABLE_NAME, values, selection, selectionArgs);
    }

    public long Delete(Product product)
    {
        SQLiteDatabase db = getWritableDatabase();

        String selection = CODE + " = ?";
        String[] selectionArgs = {product.getBarCode()};

        return db.delete(TABLE_NAME, selection, selectionArgs);
    }

    public Product getProduct(String barCode)
    {
        SQLiteDatabase db = getReadableDatabase();
        Product product = null;
        Cursor cursor = db.rawQuery("Select " + CODE + ", " + PRICE + " from " + TABLE_NAME + " where " + CODE + " = ?", new String[]{barCode});
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            product = new Product(cursor.getString(0), cursor.getString(1));
        }
        cursor.close();
        return product;
    }
}
