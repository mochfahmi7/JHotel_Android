package jhotel.com.jhotel_android_mochfahmi;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class ini merupakan class BuatPesananRequest, yaitu untuk mengatur koneksi server dan client pada bagian detail kamar pesanan.
 * version 15/05/2018
 */
public class MenuRequest extends StringRequest {
    private static final String Menu_URL = "http://192.168.43.3:8080/vacantrooms";
    private Map<String, String> params;

    public MenuRequest(Response.Listener<String> listener) {
        super(Request.Method.GET, Menu_URL, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
