package jhotel.com.jhotel_android_mochfahmi;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Class ini merupakan class PriviewHotelActivity, yaitu untuk mengatur tampilan list hotel yang ada diaplikasi.
 * version 15/05/2018
 */
public class PriviewHotelActivity extends AppCompatActivity {
    private ListView lvItem;
    private String[] bahasapemrograman = new String[]{
            "HOTEL ALEXIS", "HOTEL BOROBUDUR", "HOTEL CAPITOL", "HOTEL DIAS", "HOTEL EMERALDA"};
    AlertDialog MyDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_priview_hotel);
        lvItem = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PriviewHotelActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, bahasapemrograman);
        lvItem.setAdapter(adapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), HotelAActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(), HotelBActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 2) {
                    Intent myIntent = new Intent(view.getContext(), HotelAActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 3) {
                    Intent myIntent = new Intent(view.getContext(), HotelAActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 4) {
                    Intent myIntent = new Intent(view.getContext(), HotelAActivity.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        });
    }

}