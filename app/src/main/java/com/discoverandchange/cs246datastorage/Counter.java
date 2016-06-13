package com.discoverandchange.cs246datastorage;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class Counter extends AppCompatActivity {

  public static final String COUNTER_KEY = "counter";
  public static final int MODE = 0;
  public static final int DEF_VALUE = 0;
  public static final int INCREMENT_VALUE = 1;
  private int counter = 0;
  private CursorAdapter cursorAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_counter);

    counter = getInititalValue();
    setCounterText(counter);
  }

  public void handleAdvance(View btn) {
    counter += INCREMENT_VALUE;
    setCounterText(counter);
  }

  public void handleSave(View btn) {
    // SharedPreferences.Editor editor
    //SharedPreferences preferences = this.getPreferences(MODE);
    //SharedPreferences.Editor editor = preferences.edit();
    //editor.putInt(COUNTER_KEY, counter);
    //editor.commit();
    ContentValues values = new ContentValues();
    values.put(CounterDBOpenHelper.VALUE, counter);

    // get value
    Uri counterUri;
    if (getInititalValue() == 0) {
      counterUri = getContentResolver().insert(CounterProvider.CONTENT_URI, values);
      Log.d("Counter", "Inserted?" + counterUri.getLastPathSegment());
    }
    else {
      int cUri = getContentResolver().update(CounterProvider.CONTENT_URI, values, null, null);
    }

  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  private int getInititalValue() {
    //SharedPreferences preferences = this.getPreferences(MODE);
    //int countValue = preferences.getInt(COUNTER_KEY, DEF_VALUE);

    //String[] from = {CounterDBOpenHelper.COUNTER};
    //int[] to = {android.R.id.text1};
    //cursorAdapter = new SimpleCursorAdapter(this,
    //    android.R.layout.simple_list_item_1, null, from, to, 0);
    Cursor mCursor = getContentResolver().query(CounterProvider.CONTENT_URI, CounterDBOpenHelper.ALL_COLUMNS, null, null, null, null);
    mCursor.moveToFirst();

    if (mCursor.getCount() < 1)
      return 0;
    else
    {
      int value = mCursor.getInt(mCursor.getColumnIndex(CounterDBOpenHelper.VALUE));
      return value;
    }
  }

  private void setCounterText(int value) {
    TextView counterText = (TextView)findViewById(R.id.txtValue);
    counterText.setText(Integer.toString(value));
  }
}
