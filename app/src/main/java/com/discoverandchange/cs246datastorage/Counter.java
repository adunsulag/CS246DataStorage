package com.discoverandchange.cs246datastorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Counter extends AppCompatActivity {

  public static final String COUNTER_KEY = "counter";
  public static final int MODE = 0;
  public static final int DEF_VALUE = 0;
  public static final int INCREMENT_VALUE = 1;
  private int counter = 0;

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
    SharedPreferences preferences = this.getPreferences(MODE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putInt(COUNTER_KEY, counter);
    editor.commit();
  }

  private int getInititalValue() {
    SharedPreferences preferences = this.getPreferences(MODE);
    int countValue = preferences.getInt(COUNTER_KEY, DEF_VALUE);
    return countValue;
  }

  private void setCounterText(int value) {
    TextView counterText = (TextView)findViewById(R.id.txtValue);
    counterText.setText(Integer.toString(value));
  }
}
