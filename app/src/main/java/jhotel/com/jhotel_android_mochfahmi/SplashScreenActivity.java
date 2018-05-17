package jhotel.com.jhotel_android_mochfahmi;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

/**
 * Class ini merupakan class SplashScreen, yaitu tampilan pertama saat apilkasi dibuka.
 * version 15/05/2018
 */
public class SplashScreenActivity extends AppCompatActivity {
    public static LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        linearLayout = (LinearLayout) findViewById(R.id.linlay);
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.mytransation);
        final Intent i = new Intent(this, LoginActivity.class);
        linearLayout.startAnimation(myAnim);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
