package jhotel.com.jhotel_android_mochfahmi;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Class ini merupakan class HelpActivity, yaitu untuk mennampilkan menu bantuan aplikasi.
 * version 15/05/2018
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_help);
    }
}
