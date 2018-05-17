package jhotel.com.jhotel_android_mochfahmi;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class ini merupakan class HotelAFetchRequest, yaitu untuk mengatur koneksi server dan client pada bagian detail hotel A.
 * version 15/05/2018
 */
public class HotelBFetchRequest extends StringRequest {
    private static final String PesananFetch_URL = "http://192.168.43.3:8080/hotel/";
    private Map<String, String> params;

    public HotelBFetchRequest(Response.Listener<String> listener, String id_hotel) {
        super(Method.GET, PesananFetch_URL + id_hotel, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

