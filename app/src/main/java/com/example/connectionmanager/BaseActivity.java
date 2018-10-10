package com.example.connectionmanager;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity implements ConnectivityChangeReceiver.OnConnectivityChangedListener {
    private ConnectivityChangeReceiver connectivityChangeReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectivityChangeReceiver = new ConnectivityChangeReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(connectivityChangeReceiver,filter);

    }

    @Override
    public void onConnectivityChanged(boolean isConnected) {
        // TODO handle connectivity change
        if (!isConnected){
            Toast.makeText(this, "Trying to connect with active internet connection....", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "You are connected to active internet connection....", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(connectivityChangeReceiver);
    }
}
