package jhotel.com.jhotel_android_mochfahmi;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PesananBatalRequest extends StringRequest {

    private static final String Pesanan_URL = "http://192.168.43.3:8080/cancelpesanan";
    private Map<String, String> params;

    public PesananBatalRequest(String id_pesanan, Response.Listener<String> listener) {
        super(Method.POST, Pesanan_URL, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("error buat pesanan",volleyError+"");
            }
        });
        params = new HashMap<>();
        params.put("id_pesanan", id_pesanan);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
