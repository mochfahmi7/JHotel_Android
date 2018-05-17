package jhotel.com.jhotel_android_mochfahmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * Class ini merupakan class SelesaiPesananActivity, yaitu untuk mengatur sisi client pada bagian detail pesanan/pesanan selesai.
 * version 15/05/2018
 */
public class SelesaiPesananActivity extends AppCompatActivity {
    int customer_id;
    TextView id_pesanan;
    TextView biaya;
    TextView jmlHari;
    TextView tanggalPesan;
    Button batalkan;
    Button selesaikan;
    public static String idPesanan = "";
    public static LinearLayout linearLayout;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_selesai_pesanan);
        id_pesanan = (TextView) findViewById(R.id.id_pesanan);
        biaya = (TextView) findViewById(R.id.biaya);
        jmlHari = (TextView) findViewById(R.id.jmlHari);
        tanggalPesan = (TextView) findViewById(R.id.tanggalpesan);
        batalkan = (Button) findViewById(R.id.batal);
        selesaikan = (Button) findViewById(R.id.selesai);
        linearLayout = (LinearLayout) findViewById(R.id.layoutdetailpesanan);
        Intent test = getIntent();
        customer_id = test.getIntExtra("customer_id", 0);
        linearLayout.setVisibility(View.GONE);

        fetchPesanan();


        selesaikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Pesanan Berhasil Diselesaikan")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent intent = new Intent(SelesaiPesananActivity.this, MainMenuActivity.class);
                                                SelesaiPesananActivity.this.startActivity(intent);
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();

                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Pesanan Gagal Diselesaikan.").create().show();
                        }
                    }
                };
                PesananSelesaiRequest pesananSelesaiRequest = new PesananSelesaiRequest(SelesaiPesananActivity.idPesanan, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(pesananSelesaiRequest);
            }
        });

        batalkan.setOnClickListener(new View.OnClickListener() {
            final String id_pesanan = SelesaiPesananActivity.idPesanan;

            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Pesanan Berhasil Dibatalkan")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent intent = new Intent(SelesaiPesananActivity.this, MainMenuActivity.class);
                                                SelesaiPesananActivity.this.startActivity(intent);
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Pesanan Gagal Dibatalkan.").create().show();
                        }
                    }
                };
                PesananBatalRequest pesananBatalRequest = new PesananBatalRequest(SelesaiPesananActivity.idPesanan, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(pesananBatalRequest);
            }
        });
    }


    public void fetchPesanan() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (response.equals("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                        builder.setMessage("Pesanan Tidak Ditemukan!")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(SelesaiPesananActivity.this, MainMenuActivity.class);
                                        intent.putExtra("id", customer_id);
                                        SelesaiPesananActivity.this.startActivity(intent);

                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse != null) {
                            linearLayout.setVisibility(View.VISIBLE);
                            setUpView(jsonResponse);
                        }
                    }


                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };
        PesananFetchRequest pesananFetchRequest = new PesananFetchRequest(responseListener, customer_id + "");
        RequestQueue queue = newRequestQueue(SelesaiPesananActivity.this);
        queue.add(pesananFetchRequest);
    }


    public void setUpView(JSONObject object) {
        try {
            id_pesanan.setText(object.getString("id"));
            idPesanan = object.getString("id");
            biaya.setText(object.getString("biaya"));
            jmlHari.setText(object.getString("jumlahHari"));
            tanggalPesan.setText(object.getString("tanggalPesan"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
