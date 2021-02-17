package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.server.IServiceCallback;
import com.example.server.IServiceInterface;

public class MainActivity extends AppCompatActivity {


    IServiceInterface myService;
    boolean isService = false;

    final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isService = true;
            myService = IServiceInterface.Stub.asInterface(service);

            try {
                myService.registerCallback(mCallback);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isService = false;
        }
    };

    IServiceCallback mCallback = new IServiceCallback.Stub() {
        @Override
        public void onServiceStateChanged(int status) throws RemoteException {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bmul = findViewById(R.id.button_mul);
        Button badd = findViewById(R.id.button_add);
        Button bsub = findViewById(R.id.button_sub);
        Button bdiv = findViewById(R.id.button_div);
        Button bcalc = findViewById(R.id.button_calc);
        Button bbind = findViewById(R.id.button_bind);
        Button bubind = findViewById(R.id.button_unbind);

        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);
        TextView textView_result = findViewById(R.id.textView_result);
        TextView textView_sym = findViewById((R.id.textView_symb));


        bbind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IServiceInterface.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                Toast.makeText(getApplicationContext(),
                        "Service start", Toast.LENGTH_LONG).show();
            }
        });

        bubind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unbindService(mConnection);
                isService = false;
                Toast.makeText(getApplicationContext(),
                        "Service closing", Toast.LENGTH_LONG).show();
            }
        });

        badd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                } else {
                    int num1 = Integer.parseInt(editText1.getText().toString());
                    int num2 = Integer.parseInt(editText2.getText().toString());
                    textView_result.setText("");
                    textView_sym.setText("+");
                    //ans = myService.ADD(num1, num2);
                }
            }
        });

        bsub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                } else {
                    int num1 = Integer.parseInt(editText1.getText().toString());
                    int num2 = Integer.parseInt(editText2.getText().toString());
                    textView_result.setText("");
                    textView_sym.setText("-");
                    //ans = myService.SUB(num1, num2);
                }
            }
        });

        bmul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                } else {
                    int num1 = Integer.parseInt(editText1.getText().toString());
                    int num2 = Integer.parseInt(editText2.getText().toString());
                    textView_result.setText("");
                    textView_sym.setText("X");
                    //ans = myService.MUL(num1, num2);
                }
            }
        });

        bdiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                } else {
                    int num1 = Integer.parseInt(editText1.getText().toString());
                    int num2 = Integer.parseInt(editText2.getText().toString());
                    textView_result.setText("");
                    textView_sym.setText("/");
                    //ans = myService.DIV(num1, num2);
                }
            }
        });

        bcalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                    textView_result.setText(" ");
                } else {
                    //textView_result.setText(Float.toString(ans));
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}