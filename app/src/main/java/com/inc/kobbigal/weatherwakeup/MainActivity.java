package com.inc.kobbigal.weatherwakeup;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends android.support.v4.app.FragmentActivity {

    TextView greeting;
    FragmentManager fragmentManager;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        fragmentManager = getSupportFragmentManager();

        greeting = findViewById(R.id.greeting);
        showGreeting();

        Log.i("sp", sharedPreferences.getString("time",""));

    }

    public void showTimePicket(){

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(fragmentManager, "timepicker");
    }

    public void showGreeting(){

        greeting.setClickable(false);
        greeting.setAlpha(0f);
        greeting.animate().alpha(1f).scaleX(1.5f).scaleY(1.5f).setDuration(1500).setStartDelay(1000);

        new Handler().postDelayed(new Runnable()
        {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run()
            {
                greeting.animate()
                        .alpha(0f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(1500)
                        .setStartDelay(1500)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                showTimePicket();
                            }
                        });
            }
        }, 2500);
    }
}
