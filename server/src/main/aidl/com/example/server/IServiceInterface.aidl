// IServiceInterface.aidl
package com.example.server;

// Declare any non-default types here with import statements
import com.example.server.IServiceCallback;

interface IServiceInterface {
    boolean isAvailable();
    boolean registerCallback(IServiceCallback callback);
    boolean unregisterCallback(IServiceCallback callback);
    int ADD(int num1, int num2);
    int SUB(int num1, int num2);
    int MUL(int num1, int num2);
    float DIV(int num1, int num2);
}