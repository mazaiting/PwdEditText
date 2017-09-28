package com.mazaiting.pwdedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.mazaiting.PwdEditText;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final PwdEditText pwdEditText = (PwdEditText) findViewById(R.id.pet_password_welcome);
    Button button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Log.e(TAG, pwdEditText.getText().toString());
      }
    });
  }
}
