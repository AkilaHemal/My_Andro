package com.ahcreations.akila.myandro.DataBaseFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DataBaseName = "backup.db";
    public static String TableName = "Inbox";
    public static final String Col2 = "Number";
    public static final String Col3 = "Message";
    public static final String Col4 = "Date";


    public DataBaseHelper(Context context) {
        super(context, Environment.getExternalStorageDirectory().getPath() + "/Android/obb/My_Android" + DataBaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Number TEXT,Message TEXT,Date TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public boolean insertData(String no, String msg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(Col2, no);
        contentValue.put(Col3, msg);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        contentValue.put(Col4, dateFormat.format(date));

        long result = db.insert(TableName, null, contentValue);
        return result != -1;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TableName, null);
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TableName, "ID = ?", new String[]{id});
    }

    public Cursor getNumberList() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT Number, MAX(Date), count(Number) FROM " + TableName + " GROUP BY Number ORDER BY MAX(Date) DESC", null);
    }

    public Cursor getMsgList(String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT Message, Date FROM " + TableName + " WHERE Number LIKE '" + number + "' ORDER BY Date DESC", null);
    }

}
