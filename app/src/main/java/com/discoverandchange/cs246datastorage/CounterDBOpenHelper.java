package com.discoverandchange.cs246datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jandar on 6/13/2016.
 */
public class CounterDBOpenHelper extends SQLiteOpenHelper {

  // name and version
  private static final String DATABASE_NAME = "counterActivity";
  private static final int DATABASE_VERSION = 2;

  // table
  public static final String COUNTER = "counter";

  // column
  public static final String PKEY = "_id";
  public static final String VALUE = "countValue";

  public static final String[] ALL_COLUMNS =
      {VALUE};

  // sql to create
  private static final String TBL_CREATE =
      "CREATE TABLE " + COUNTER + " (" +
          PKEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
          VALUE + " INTEGER" +
          ")";

  // constructor
  public CounterDBOpenHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(TBL_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS " + COUNTER);
    onCreate(db);
  }
}
