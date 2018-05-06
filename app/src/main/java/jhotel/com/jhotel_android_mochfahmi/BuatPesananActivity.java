package jhotel.com.jhotel_android_mochfahmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Formatter;

public class BuatPesananActivity extends AppCompatActivity {

    private int banyakHari;
    private int idHotel;
    private int customer_id;
    private double tarif;
    private String roomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);

        Intent test=getIntent();
        customer_id= test.getIntExtra("customer_id",0);
        idHotel=test.getIntExtra("idhotel",0);
        roomNumber=test.getStringExtra("roomNumber");
        tarif=test.getDoubleExtra("tarif",0);

        final Button hitung=(Button) findViewById(R.id.hitung);
        final Button pesan=(Button) findViewById(R.id.pesan);
        final TextView room_Number=(TextView) findViewById(R.id.room_number);
        final TextView tariff=(TextView) findViewById(R.id.tariff);
        final TextView total_biaya=(TextView) findViewById(R.id.total_biaya);
        final EditText durasi_hari=(EditText) findViewById(R.id.durasi_hari);

        pesan.setVisibility(View.GONE);
        hitung.setVisibility(View.VISIBLE);
        room_Number.setText(roomNumber);
        tariff.setText(getRupiahFormat(tarif));
        total_biaya.setText("0");

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banyakHari=Integer.parseInt(durasi_hari.getText().toString());
                double hitungtotal = tarif*banyakHari;
                total_biaya.setText(getRupiahFormat(hitungtotal));
                hitung.setVisibility(View.GONE);
                pesan.setVisibility(View.VISIBLE);
            }
        });

        pesan.setOnClickListener(new View.OnClickListener() {

            final String  jumlah_hari= banyakHari+"";
            final String nomor_kamar = room_Number.getText().toString();
            final String id_customer= customer_id+"";
            final String id_hotel= idHotel+"";

            @Override
            public void onClick(View v) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(BuatPesananActivity.this);
                                builder.setMessage("Pesanan Sukses")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent intent = new Intent(BuatPesananActivity.this, MainActivity.class);
                                                BuatPesananActivity.this.startActivity(intent);
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();

                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(BuatPesananActivity.this);
                            builder.setMessage("Pesanan Gagal").create().show();
                        }
                    }
                };

                BuatPesananRequest buatPesananRequest= new BuatPesananRequest(banyakHari+"",id_customer,id_hotel,nomor_kamar,responseListener);
                RequestQueue queue= Volley.newRequestQueue(BuatPesananActivity.this);
                queue.add(buatPesananRequest);
//
            }
        });
    }


    public String getRupiahFormat(Double input){
        float epsilon = 0.004f; // 4 tenths of a cent
        String rupiah = "";
        if (Math.abs(Math.round(input) - input) < epsilon) {
            rupiah = String.format("%10.0f", input); // sdb
        } else {
            rupiah =  String.format("%10.2f", input); // dj_segfault
        }
        return "Rp "+rupiah;

    }

}
