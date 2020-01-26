package com.mrgreenapps.otpviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mrgreenapps.otpview.OTPView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final OTPView otpView = findViewById(R.id.otpView);

        otpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, otpView.getOTPPin(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
