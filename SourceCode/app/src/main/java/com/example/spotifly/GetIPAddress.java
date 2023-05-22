package com.example.spotifly;

import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIPAddress {



    public GetIPAddress() throws UnknownHostException {
        InetAddress IP = InetAddress.getLocalHost();
        Log.d("ip", IP.getHostAddress());
    }
}
