package com.example.zackakil.bluetoothtest;

import android.app.LauncherActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class DisplayMessageActivity extends AppCompatActivity {

    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView  display = (TextView) findViewById(R.id.textView2);
        display.setText(msg);

        doBluetooth();

    }

    private void doBluetooth(){

        final BluetoothDevice[] arduino = new BluetoothDevice[1];

        final ArrayList<BluetoothDevice> ds = new ArrayList<>();

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }

        ArrayList mArrayAdapter = new ArrayList();
        final ArrayList<String> deviceAddresses = new ArrayList();

        final Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                deviceAddresses.add(device.getAddress());
                ds.add(device);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mArrayAdapter);

        final ListView listView = (ListView) findViewById(R.id.deviceList);
        listView.setAdapter(adapter);
        // test commit

        // Create a message handling object as an anonymous class.
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
                arduino[0] = ds.get(position);
                String textToShow = deviceAddresses.get(position);
                Toast.makeText(getApplicationContext(),textToShow,Toast.LENGTH_SHORT).show();
                try {
                    sendAddressToCom(arduino[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        listView.setOnItemClickListener(mMessageClickedHandler);
    }

    void sendAddressToCom(BluetoothDevice ard) throws IOException {

        BluetoothSocket socket = ard.createRfcommSocketToServiceRecord( UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
        socket.connect();
        outputStream = socket.getOutputStream();

    }

    public void sendStuff() throws IOException {

        String msg  = "hello world";
        outputStream.write(1);
    }

    public void testStuff(View v) throws IOException {

        String msg  = ((EditText)findViewById(R.id.editText3)).getText().toString();
        outputStream.write(msg.getBytes());
    }


}
