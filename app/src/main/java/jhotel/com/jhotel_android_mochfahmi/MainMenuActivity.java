package jhotel.com.jhotel_android_mochfahmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class ini merupakan class MainMenuActiviy, yaitu class untuk mengatur sisi client bagian menu utama untuk mengatur perpindahan activity.
 * version 15/05/2018
 */
public class MainMenuActivity extends AppCompatActivity {
    @BindView(R.id.kontak)
    ImageButton contactBtn;
    @BindView(R.id.booking)
    ImageButton bookingBtn;
    @BindView(R.id.testimoni)
    ImageButton testiBtn;
    @BindView(R.id.listorder)
    ImageButton listorderBtn;


    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
        sharedPrefManager = new SharedPrefManager(this);


        bookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
                MainMenuActivity.this.startActivity(intent);
            }
        });


        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = "mochamad.fahmi@ui.ac.id";
                String subject = "Pertanyaan Tentang Aplikasi";
                String message = "Pertanyaannya adalah";

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Kirim Email Dengan..."));
            }
        });

        listorderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SelesaiPesananActivity.class);
                MainMenuActivity.this.startActivity(intent);
            }
        });

        testiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, PriviewHotelActivity.class);
                MainMenuActivity.this.startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_sign_out){
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
            startActivity(new Intent(MainMenuActivity.this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }else if(item.getItemId()==R.id.about){
            Intent intent = new Intent(MainMenuActivity.this, HelpActivity.class);
            MainMenuActivity.this.startActivity(intent);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Anda yakin ingin keluar ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog quit = builder.create();
        quit.show();
    }
}
