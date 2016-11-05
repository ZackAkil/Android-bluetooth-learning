package com.example.zackakil.bluetoothtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.zackakil.bluetoothtest.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//       Button testBtn = (Button) findViewById(R.id.button2);
//        testBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                Toast.makeText(getApplicationContext(),"Test yo",Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    public void toastYo(View view){
        Switch op =  (Switch) findViewById(R.id.switch1);
        if (op.isChecked()) {
            Toast.makeText(getApplicationContext(), "Im on!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "I'm off", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, DisplayMessageActivity.class);

        EditText editText = (EditText) findViewById(R.id.editText);


        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
