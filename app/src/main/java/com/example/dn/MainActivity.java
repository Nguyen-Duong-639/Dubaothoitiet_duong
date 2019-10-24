package com.example.dn;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import json.OpenWeatherJSon;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView theoGio;
    RecyclerView theongay;

    ImageView  findcity,here,maps;
    TextView txtName, txtTemp, txtStatus, txtHumidity, txtCloud, txtWind, txtDay ;
    ImageView imageIcon;
    EditText etfindcity;
    LinearLayout all;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findcity=findViewById(R.id.findcity);
        theongay=findViewById(R.id.theongay);
        txtName=findViewById(R.id.cityShow);
        here=findViewById(R.id.here);
        etfindcity=findViewById(R.id.etfindcity);
        maps=findViewById(R.id.maps);

        txtTemp=findViewById(R.id.celsius);
        txtStatus=findViewById(R.id.weather);
        txtHumidity=findViewById(R.id.doAm);
        txtCloud=findViewById(R.id.pcloud);
        txtWind=findViewById(R.id.tocdogio);
        txtDay=findViewById(R.id.now);
        imageIcon=findViewById(R.id.imagewerther);

        all=findViewById(R.id.all);

//        recyclerView = ArrayList<>

        setFindcity();
        setViewGio();
        setViewNgay();


//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String city= edtSearch.getText().toString();
//                GetWeatherData(city);
//            }
//        });

    }
    public class WeatherAsyncTask extends AsyncTask<Void,Void,OpenWeatherJSon> {

        @Override
        protected OpenWeatherJSon doInBackground(Void... voids) {
            return null;
        }
    }


    public static OpenWeatherJSon prediction(String q) {
        try {
            String location = URLEncoder.encode(q, "UTF-8");//

            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location);
            InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
            OpenWeatherJSon results = new Gson().fromJson(reader, OpenWeatherJSon.class);

            String idIcon = results.getWeather().get(0).getIcon().toString();
            String urlIcon = "http://openweathermap.org/img/w/" + idIcon + ".png";
            URL urlImage = new URL(urlIcon);

            return results;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public void setViewNgay(){
        theongay=findViewById(R.id.theongay);
        theongay.setHasFixedSize(true);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        theongay.setLayoutManager(layoutManager);
        ArrayList<Custom_ngay> arrayList=new ArrayList<>();

        arrayList.add(new Custom_ngay("1:00","78%","24 oC","21"));
        arrayList.add(new Custom_ngay("4:00","79%","25do C","22"));
        arrayList.add(new Custom_ngay("7:00","80%","26do C","23"));
        arrayList.add(new Custom_ngay("10:00","81%","27do C","24"));
        arrayList.add(new Custom_ngay("13:00","82%","28do C","25"));
        arrayList.add(new Custom_ngay("1:00","78%","24do C","21"));
        arrayList.add(new Custom_ngay("4:00","79%","25do C","22"));
        arrayList.add(new Custom_ngay("7:00","80%","26do C","23"));
        arrayList.add(new Custom_ngay("10:00","81%","27do C","24"));
        arrayList.add(new Custom_ngay("13:00","82%","28do C","25"));

        Adapter_ngay adapterNgay=new Adapter_ngay(arrayList,getApplicationContext());
        theongay.setAdapter(adapterNgay);
    }
    public  void setViewGio()
    {
        theoGio=findViewById(R.id.theogio);
        theoGio.setHasFixedSize(true);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        theoGio.setLayoutManager(layoutManager);
        ArrayList<custom_gio> arrayList=new ArrayList<>();

        arrayList.add(new custom_gio("1:00","78%","24do C"));
        arrayList.add(new custom_gio("4:00","79%","25do C"));
        arrayList.add(new custom_gio("7:00","80%","26do C"));
        arrayList.add(new custom_gio("10:00","81%","27do C"));
        arrayList.add(new custom_gio("13:00","82%","28do C"));

        adapter_gio  adapterGio=new adapter_gio(arrayList,getApplicationContext());
        theoGio.setAdapter(adapterGio);
    }

    public  void setFindcity(){
        findcity.setOnClickListener(new View.OnClickListener() {
            int d=0;

            @Override
            public void onClick(View v) {
                d++;
                if (d%2!=0){
                    etfindcity.setVisibility(EditText.VISIBLE);
                }
                else {
                    etfindcity.setVisibility(EditText.GONE);
                }
                all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etfindcity.setVisibility(EditText.GONE);
                    }
                });


            }
        });
    }


    public void GetWeatherData(String data){

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        String url="https://samples.openweathermap.org/data/2.5/weather?q=+ data +,uk&appid=b15c6285f8ad4ea604deee730d0fde7f";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ket qua", response);

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
//    public void getFragment(Fragment fragment) {
//        try {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, fragment)
//                    .commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.d(TAG, "getFragment: " + e.getMessage());
//        }
//    }

}
