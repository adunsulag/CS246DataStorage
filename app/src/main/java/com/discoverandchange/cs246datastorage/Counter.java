package com.discoverandchange.cs246datastorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Controller class that shows a counter that can be saved and incremented.
 */
public class Counter extends AppCompatActivity {

  /**
   * The internal counter that the user increments.
   */
  private int counter = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_counter);

    counter = getDatabaseValue();
    setCounterText(counter);
  }

  /**
   * Handles the advance click button.
   * @param btn the button clicked.
   */
  public void handleAdvance(View btn) {
    counter += 1;
    setCounterText(counter);
  }

  /**
   * Handles the save button click by persisting the data.
   * @param btn the button clicked.
   */
  public void handleSave(View btn) {
    ContentValues values = new ContentValues();
    values.put(CounterDbOpenHelper.VALUE, counter);

    // get value
    Uri counterUri;
    if (getDatabaseValue() == 0) {
      getContentResolver().insert(CounterProvider.CONTENT_URI, values);
    } else {
      getContentResolver().update(CounterProvider.CONTENT_URI, values, null, null);
    }

  }

  /**
   * Returns the value stored in the database of the counter.
   */
  private int getDatabaseValue() {
    Cursor cursor = getContentResolver().query(CounterProvider.CONTENT_URI,
         CounterDbOpenHelper.ALL_COLUMNS, null, null, null, null);
    cursor.moveToFirst();

    if (cursor.getCount() < 1) {
      return 0;
    } else {
      int value = cursor.getInt(cursor.getColumnIndex(CounterDbOpenHelper.VALUE));
      return value;
    }
  }

  /**
   * Sets the text of the counter to be the value passed in.
   * @param value The value of the counter.
   */
  private void setCounterText(int value) {
    TextView counterText = (TextView) findViewById(R.id.txtValue);
    counterText.setText(Integer.toString(value));
  }
}
