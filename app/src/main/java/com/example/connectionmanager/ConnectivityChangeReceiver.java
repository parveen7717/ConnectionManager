package com.example.connectionmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityChangeReceiver extends BroadcastReceiver {
    private OnConnectivityChangedListener listener;

    public ConnectivityChangeReceiver(OnConnectivityChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = null;
        if (connectivityManager != null) {
            activeNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        }
        boolean isConnected = activeNetInfo !=null && activeNetInfo.isConnectedOrConnecting();
        listener.onConnectivityChanged(isConnected);
    }

    public interface OnConnectivityChangedListener {
        void onConnectivityChanged(boolean isConnected);
    }
}
