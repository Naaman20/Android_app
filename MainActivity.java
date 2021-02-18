package com.ui.otpverfication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button getotp;
    EditText userphoneno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getotp=(Button)findViewById(R.id.getotp);
        userphoneno=(EditText)findViewById(R.id.userphoneno);

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userphoneno.getText().toString().trim().isEmpty())
                {
                    userphoneno.setError("Enter Phone No");
                }else{
                    Intent i = new Intent(MainActivity.this, VerifyOTP.class);
                    i.putExtra("phone",userphoneno.getText().toString().trim());
                    startActivity(i);
                    finish();
                }

            }
        });
    }
}
