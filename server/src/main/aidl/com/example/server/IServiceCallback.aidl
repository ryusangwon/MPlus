// IServiceCallback.aidl
package com.example.server;

// Declare any non-default types here with import statements

interface IServiceCallback {
    void onServiceStateChanged(int status);
}