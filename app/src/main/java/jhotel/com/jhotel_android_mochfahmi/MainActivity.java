package jhotel.com.jhotel_android_mochfahmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class MainActivity extends AppCompatActivity {

    int currentUserId,roomNumber;
    private ArrayList<Hotel> listHotel = new ArrayList<>();
    private ArrayList<Room> listRoom = new ArrayList<>();
    private HashMap<Hotel, ArrayList<Room>> childMapping = new HashMap<>();
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    HashMap<String, Hotel> hotelHashMap = new HashMap<>();
    HashMap<String, ArrayList<Room>> roomsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent test=getIntent();
        currentUserId=test.getIntExtra("id",0);
        expListView = (ExpandableListView) findViewById(R.id.expanded_menu);
        Button pesananButton=(Button) findViewById(R.id.pesanan);

        refreshList();

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Room Rselected = childMapping.get(listHotel.get(groupPosition)).get(childPosition);
                Hotel Hselected= listHotel.get(groupPosition);
                Intent intent=new Intent(MainActivity.this, BuatPesananActivity.class);
                intent.putExtra("customer_id", currentUserId);
                intent.putExtra("roomNumber",Rselected.getRoomNumber());
                intent.putExtra("idhotel",Hselected.getID());
                intent.putExtra("tarif",Rselected.getDailyTariff());
                MainActivity.this.startActivity(intent);
                return false;
            }
        });

        pesananButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SelesaiPesananActivity.class);
                intent.putExtra("customer_id", currentUserId);
                MainActivity.this.startActivity(intent);

            }
        });


    }


    public void refreshList() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i = 0; i < jsonResponse.length(); i++) {
                        JSONObject e = jsonResponse.getJSONObject(i).getJSONObject("hotel");
                        JSONObject lokasi = e.getJSONObject("lokasi");
                        JSONObject room = jsonResponse.getJSONObject(i);
                        Hotel h = new Hotel(e.getString("nama"), new Lokasi(lokasi.getInt("x"), lokasi.getInt("y"), lokasi.getString("deskripsi")),
                                e.getInt("bintang"), e.getInt("id"));
                        hotelHashMap.put(h.getNama(), h);
                        Room room1 = new Room(room.getString("nomorKamar"), room.getString("statusKamar"), room.getDouble("dailyTariff"), room.getString("tipeKamar"));


                        if (!roomsMap.containsKey(h.getNama())) {
                            ArrayList<Room> rooms = new ArrayList<>();
                            rooms.add(room1);
                            roomsMap.put(h.getNama(), rooms);
                        } else {
                            ArrayList<Room> rooms = roomsMap.get(h.getNama());
                            rooms.add(room1);
                            roomsMap.put(h.getNama(), rooms);
                        }
                    }

                    for (String key : hotelHashMap.keySet()) {
                        listHotel.add(hotelHashMap.get(key));

                        childMapping.put(hotelHashMap.get(key), roomsMap.get(key));
                    }
                    listAdapter = new MenuListAdapter(MainActivity.this, listHotel, childMapping);
                    expListView.setAdapter(listAdapter);

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }


}