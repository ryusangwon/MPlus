package com.example.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.widget.Toast;


public class MyService extends Service {

    IServiceInterface mServiceInterface;
    IServiceCallback mCallback;

    String TAG = "MyServiceTag";

    public Binder mBinder = new IServiceInterface.Stub() {
        @Override
        public boolean isAvailable() throws RemoteException {
            boolean isService = mCallback.isService();
            return isService;
        }

        @Override
        public boolean registerCallback(IServiceCallback callback) throws RemoteException {
            mCallback = callback;
            return true;
        }

        @Override
        public boolean unregisterCallback(IServiceCallback callback) throws RemoteException {
            mCallback = null;
            return false;
        }

        @Override
        public int ADD(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }

        @Override
        public int SUB(int num1, int num2) throws RemoteException {
            return num1 - num2;
        }

        @Override
        public int MUL(int num1, int num2) throws RemoteException {
            return num1 * num2;
        }

        @Override
        public float DIV(int num1, int num2) throws RemoteException {
            float ans = 0;
            try {
                ans = (float)num1 / num2;
            }catch (ArithmeticException e){
                e.printStackTrace();
            }
            return ans;
        }
    };

    public MyService() throws RemoteException {
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}