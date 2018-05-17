package jhotel.com.jhotel_android_mochfahmi;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class ini merupakan class BuatPesananRequest, yaitu untuk mengatur koneksi server dan client pada bagian pembuatan pesanan.
 * version 15/05/2018
 */
public class BuatPesananRequest extends StringRequest {
    private static final String Pesanan_URL = "http://192.168.43.3:8080/bookpesanan";
    private Map<String, String> params;

    public BuatPesananRequest(String jumlah_hari, String id_customer, String id_hotel, String nomor_kamar, Response.Listener<String> listener) {
        super(Method.POST, Pesanan_URL, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("error buat pesanan", volleyError + "");
            }
        });
        params = new HashMap<>();
        params.put("jumlah_hari", jumlah_hari);
        params.put("id_customer", id_customer);
        params.put("id_hotel", id_hotel);
        params.put("nomor_kamar", nomor_kamar);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
