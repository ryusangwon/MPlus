// IServiceInterface.aidl
package com.example.server;

// Declare any non-default types here with import statements
import com.example.server.IServiceCallback;

interface IServiceInterface {
    boolean isAvailable();
    boolean registerCallback(IServiceCallback callback);
    boolean unregisterCallback(IServiceCallback callback);
}