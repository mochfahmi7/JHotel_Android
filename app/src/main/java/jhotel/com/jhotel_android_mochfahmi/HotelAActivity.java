package jhotel.com.jhotel_android_mochfahmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * Class ini merupakan class HotelAActivity, yaitu untuk menampilkan detail hotel A.
 * version 15/05/2018
 */
public class HotelAActivity extends AppCompatActivity {
    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.bintang)
    TextView bintang;
    @BindView(R.id.lokasi)
    TextView lokasi;
    int hotel_id=1;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.hotel_a);
        ButterKnife.bind(this);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        fetchHotel();
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    public void fetchHotel() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (response.equals("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(HotelAActivity.this);
                        builder.setMessage("Hotel Tidak Ditemukan!")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(HotelAActivity.this, PriviewHotelActivity.class);
                                        HotelAActivity.this.startActivity(intent);

                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse != null) {
                            setUpView(jsonResponse);
                        }
                    }


                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };
        HotelAFetchRequest hotelAFetchRequest = new HotelAFetchRequest(responseListener, hotel_id + "");
        RequestQueue queue = newRequestQueue(HotelAActivity.this);
        queue.add(hotelAFetchRequest);
    }

    public void setUpView(JSONObject object) {
        try {
            JSONObject lokasijson=(JSONObject) object.get("lokasi");
            Object lok=(String) lokasijson.get("deskripsi");
            nama.setText(object.getString("nama"));
            bintang.setText(object.getString("bintang"));
            lokasi.setText(""+lok);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
