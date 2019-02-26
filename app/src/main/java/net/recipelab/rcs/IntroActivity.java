package net.recipelab.rcs;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                mHandler.removeCallbacks(mRunnable);
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 2500);
    }
}
