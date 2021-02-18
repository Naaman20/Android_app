package com.ui.otpverfication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class VerifyOTP extends AppCompatActivity {

    int randonnumber;
    String phonenumber;
    Button verifyotp,getVerifyotp;
    EditText otp_edittext;
    String otp_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        verifyotp=(Button)findViewById(R.id.verifyotp);
        getVerifyotp=(Button)findViewById(R.id.get_otp_no);
        otp_edittext=(EditText)findViewById(R.id.otpedittext);

        final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Intent intent=getIntent();
        phonenumber=intent.getStringExtra("phone");

        Toast.makeText(VerifyOTP.this, ""+phonenumber, Toast.LENGTH_SHORT).show();

    }

    void initialsendotp(){
        try {
            // Construct data
            String apiKey = "apikey=" + "ERJtdFbCOEk-5vvFn7mjg4SuywzHCcQkNxxxx(Replace with your API KEY)";

            Random random = new Random();
            randonnumber=random.nextInt(99999);
            String message = "&message=" + "Hey, Your OTP is " +randonnumber;
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" +phonenumber;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);

        }
    }

    public  void GetOTPNO(View view)
    {
        initialsendotp();
        Toast.makeText(VerifyOTP.this,"OTP SENT< Please Wait you may recieved in a movement",Toast.LENGTH_LONG).show();
        getVerifyotp.setVisibility(View.GONE);
    }

    public void verifyOTP(View view) {
        Toast.makeText(VerifyOTP.this,"Verify Button",Toast.LENGTH_LONG).show();
        otp_text = otp_edittext.getText().toString().trim();

        if(otp_text.equals(String.valueOf(randonnumber)))
        {

            Toast.makeText(VerifyOTP.this,"user login in successfully",Toast.LENGTH_LONG).show();
            finish();
            Intent mainactivity = new Intent(VerifyOTP.this,SuccessLoginIn.class);

            startActivity(mainactivity);
        }
        else{
            Toast.makeText(VerifyOTP.this,"Invalid OTP, Please Try Again",Toast.LENGTH_LONG).show();

        }
    }
}
