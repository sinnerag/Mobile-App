package mia.cm.anand.tennisapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseTable extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "SQLiteExample.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "table";
    public static final String PLAYER_ONE_NAME = "p1name";
    public static final String PLAYER_TWO_NAME = "p2name";
    public static final String PLAYER_ONE_SET_ONE = "p1setone";
    public static final String PLAYER_ONE_SET_TWO = "p1settwo";
    public static final String PLAYER_ONE_SET_THREE = "p1setthree";
    public static final String PLAYER_TWO_SET_ONE = "p2setone";
    public static final String PLAYER_TWO_SET_TWO = "p2settwo";
    public static final String PLAYER_TWO_SET_THREE = "p2setthree";




    public DatabaseTable(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( id INTEGER PRIMARY KEY, " +
                PLAYER_ONE_NAME + " TEXT, " +
                PLAYER_TWO_NAME + " TEXT, " +
                PLAYER_ONE_SET_ONE + "INTEGER, " +
                PLAYER_ONE_SET_TWO + " INTEGER, " +
                PLAYER_ONE_SET_THREE + " INTEGER, " +
                PLAYER_TWO_SET_ONE + " INTEGER, " +
                PLAYER_TWO_SET_TWO + "INTEGER, " +
                PLAYER_TWO_SET_THREE + "INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertGame(String namep1, String namep2, int p1setone, int p1settwo,int p1setthree, int p2setone, int p2settwo, int p2setthree){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLAYER_ONE_NAME, namep1);
        contentValues.put(PLAYER_ONE_SET_ONE, p1setone);
        contentValues.put(PLAYER_ONE_SET_TWO, p1settwo);
        contentValues.put(PLAYER_ONE_SET_THREE, p1setthree);
        contentValues.put(PLAYER_TWO_NAME, namep2);
        contentValues.put(PLAYER_TWO_SET_ONE, p2setone);
        contentValues.put(PLAYER_TWO_SET_TWO, p2settwo);
        contentValues.put(PLAYER_TWO_SET_THREE, p2setthree);
        return true;
    }





}
