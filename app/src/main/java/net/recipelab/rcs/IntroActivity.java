package net.recipelab.rcs;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class IntroActivity extends AppCompatActivity {
    Handler mHandler;
    Runnable mRunnable;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(mRunnable);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_intro);

        Button btA = (Button)findViewById(R.id.bt_a);
        Button btB = (Button)findViewById(R.id.bt_b);
        Button btC = (Button)findViewById(R.id.bt_c);

        btA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File file = new File("ip.txt");

                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

                    String text = "test file write";
                    bufferedWriter.write(text);
                    bufferedWriter.newLine();

                    bufferedWriter.close();

                    Toast.makeText(getApplicationContext(), "file write", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File file = new File("ip.txt");

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                    String text = bufferedReader.readLine();

                    bufferedReader.close();

                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }
        });

//        mRunnable = new Runnable() {
//            @Override
//            public void run() {
//                mHandler.removeCallbacks(mRunnable);
//                startActivity(new Intent(IntroActivity.this, MainActivity.class));
//                finish();
//
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        };
//
//        mHandler = new Handler();
//        mHandler.postDelayed(mRunnable, 2000);
    }
}
