package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.server.IServiceCallback;
import com.example.server.IServiceInterface;

public class MainActivity extends AppCompatActivity {

    float ans;
    String TAG = "Client_MainActivity";
    IServiceInterface myService;
    boolean isService = false;

    final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isService = true;
            myService = IServiceInterface.Stub.asInterface(service);
            try {
                myService.registerCallback(mCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "onServiceConnected: " + isService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
            isService = false;
        }
    };

    IServiceCallback mCallback = new IServiceCallback.Stub() {
        @Override
        public boolean isService() throws RemoteException {
            return isService;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);
        TextView textView_result = findViewById(R.id.textView_result);
        TextView textView_sym = findViewById((R.id.textView_symb));

        Button bbind = findViewById(R.id.button_bind);
        bbind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent("com.example.server.MY_SERVICE");
                intent.setPackage("com.example.server");
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                Toast.makeText(getApplicationContext(),
                        "Service start", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: ");
            }
        });

        Button bubind = findViewById(R.id.button_unbind);
        bubind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unbindService(mConnection);
                isService = false;
                textView_sym.setText("");
                textView_result.setText("");
                editText1.setText("");
                editText2.setText("");
                Toast.makeText(getApplicationContext(),
                        "Service closing", Toast.LENGTH_LONG).show();
            }
        });

        Button badd = findViewById(R.id.button_add);
        badd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                } else {
                    textView_result.setText("");
                    textView_sym.setText("+");
                    Log.d(TAG, "onClick: " + myService);
                    try {
                        int num1 = Integer.parseInt(editText1.getText().toString());
                        int num2 = Integer.parseInt(editText2.getText().toString());
                        ans = myService.ADD(num1, num2);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button bsub = findViewById(R.id.button_sub);
        bsub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                } else {
                    textView_result.setText("");
                    textView_sym.setText("-");
                    try {
                        int num1 = Integer.parseInt(editText1.getText().toString());
                        int num2 = Integer.parseInt(editText2.getText().toString());
                        ans = myService.SUB(num1, num2);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button bmul = findViewById(R.id.button_mul);
        bmul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                } else {
                    textView_result.setText("");
                    textView_sym.setText("X");
                    try {
                        int num1 = Integer.parseInt(editText1.getText().toString());
                        int num2 = Integer.parseInt(editText2.getText().toString());
                        ans = myService.MUL(num1, num2);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button bdiv = findViewById(R.id.button_div);
        bdiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                } else {
                    textView_result.setText("");
                    textView_sym.setText("/");
                    try {
                        int num1 = Integer.parseInt(editText1.getText().toString());
                        int num2 = Integer.parseInt(editText2.getText().toString());
                        ans = myService.DIV(num1, num2);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button bcalc = findViewById(R.id.button_calc);
        bcalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "Not in service", Toast.LENGTH_LONG).show();
                    textView_result.setText(" ");
                } else {
                    try {
                        textView_result.setText(Float.toString(ans));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}