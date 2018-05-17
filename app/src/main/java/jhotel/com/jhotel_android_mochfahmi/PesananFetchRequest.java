package jhotel.com.jhotel_android_mochfahmi;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class ini merupakan class PesananFetchRequest, yaitu untuk mengatur koneksi server dan client pada bagian detail pesanan.
 * version 15/05/2018
 */
public class PesananFetchRequest extends StringRequest {
    private static final String PesananFetch_URL = "http://192.168.43.3:8080/pesanancustomer/";
    private Map<String, String> params;

    public PesananFetchRequest(Response.Listener<String> listener, String id_customer) {
        super(Method.GET, PesananFetch_URL + id_customer, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

