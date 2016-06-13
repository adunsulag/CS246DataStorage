package com.discoverandchange.cs246datastorage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Data access object for working with the counter.
 * @author Stephen Nielson
 * @author Keith Higbee
 * @author John Okleberry
 */
public class CounterProvider extends ContentProvider {

  private static final String AUTHORITY = "com.discoverandchange.cs246datastorage.CounterProvider";
  private static final String BASE_PATH = "counterActivity";
  public static final Uri CONTENT_URI =
      Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

  // Constant to identify the requested operation
  private static final int COUNTER = 1;     // get the data

  private static final UriMatcher uriMatcher =
      new UriMatcher(UriMatcher.NO_MATCH);

  static {
    uriMatcher.addURI(AUTHORITY, BASE_PATH, COUNTER);
  }

  private SQLiteDatabase database;

  @Override
  public boolean onCreate() {
    CounterDbOpenHelper helper = new CounterDbOpenHelper(getContext());
    database = helper.getWritableDatabase();
    return true;
  }

  @Nullable
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                      String sortOrder) {
    return database.query(CounterDbOpenHelper.COUNTER, CounterDbOpenHelper.ALL_COLUMNS,
        selection, null, null, null, null);
  }

  @Nullable
  @Override
  public String getType(Uri uri) {
    return null;
  }

  @Nullable
  @Override
  public Uri insert(Uri uri, ContentValues values) {
    long id = database.insert(CounterDbOpenHelper.COUNTER,
        null, values);
    return Uri.parse(BASE_PATH + "/" + id);
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    return database.delete(CounterDbOpenHelper.COUNTER, selection, selectionArgs);
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    return database.update(CounterDbOpenHelper.COUNTER,
        values, selection, selectionArgs);
  }
}
