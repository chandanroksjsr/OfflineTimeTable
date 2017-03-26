package com.hashbird.learningvolley;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ranjan on 26-03-2017.
 */
public class SqliteDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mydb";
    public static final String TABLE_NAME = "time_table";
    public static final String COL_0 = "id";
    public static final String COL_1 = "day";
    public static final String COL_2 = "h1";
    public static final String COL_3 = "h2";
    public static final String COL_4 = "h3";
    public static final String COL_5 = "h4";
    public static final String COL_6 = "h5";
    public static final String COL_7 = "h6";
    public static final String COL_8 = "h7";




    public SqliteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user = "CREATE TABLE "+TABLE_NAME+" ("+COL_0+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_1+" TEXT, "+COL_2+" TEXT, "+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+" TEXT, "+COL_6+" TEXT, "+COL_7+" TEXT, "+COL_8+" TEXT)";
        db.execSQL(user);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }
    public boolean create_tt(String day,String h1,String h2,String h3,String h4,String h5,String h6,String h7){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1,day);
        cv.put(COL_2,h1);
        cv.put(COL_3,h2);
        cv.put(COL_4,h3);
        cv.put(COL_5,h4);
        cv.put(COL_6,h5);
        cv.put(COL_7,h6);
        cv.put(COL_8,h7);
long result  =  db.insert(TABLE_NAME,null,cv);

if(result==-1){
    return false;
}else {
    return true;
}


    }
    public Cursor gettt(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);

return res;
    }
    public void delete()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }
}
