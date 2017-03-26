package com.hashbird.learningvolley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    SqliteDatabaseHelper mydb;
Button button;
    TextView textview;
    String server  = "https://hashbird.com/gogrit.in/workspace/srm-api/get-ptt.php";
   // String image = "image";
    ProgressDialog pg;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
mydb = new SqliteDatabaseHelper(this);

        button = (Button)findViewById(R.id.bt);
        textview = (TextView)findViewById(R.id.textView);
        Cache cache = new DiskBasedCache(getCacheDir(),1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache,network);
        requestQueue.start();

pg = new ProgressDialog(this);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
getData();
    }
});




    }




    private void getData()
    {

        pg.setMessage("Please Wait");
        pg.show();
        //final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Log.d(TAG, "Login Response: " + response.toString());
                        //hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");

                            // Check for error node in json
                            if (!error) {

                                mydb.delete();


                                JSONArray monday = jObj.getJSONArray("monday");
                                Integer i;
                            
                            
                                ArrayList<String> monlist = new ArrayList<String>();
                                //headersArrayList.add("MONDAY");

                                for(i=0;i<monday.length();i++) {
                                    String name = monday.getString(i);


                                   monlist.add(name);
                                  //  Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();

                                }

                                mydb.create_tt("MONDAY",monlist.get(0),monlist.get(1),monlist.get(2),monlist.get(3),monlist.get(4),monlist.get(5),monlist.get(6));
                          

                                JSONArray tuesday = jObj.getJSONArray("tuesday");
                                ArrayList<String> tuelist = new ArrayList<String>();
                                for(i=0;i<tuesday.length();i++) {
                                    String name = tuesday.getString(i);
                                  tuelist.add(name);
                                }
                                mydb.create_tt("TUESDAY",tuelist.get(0),tuelist.get(1),tuelist.get(2),tuelist.get(3),tuelist.get(4),tuelist.get(5),tuelist.get(6));




                                JSONArray wednesday = jObj.getJSONArray("wednesday");
                                ArrayList<String> wedlist = new ArrayList<String>();
                                for(i=0;i<wednesday.length();i++) {
                                    String name = wednesday.getString(i);
                                    wedlist.add(name);

                                }
                                mydb.create_tt("WEDNESDAY",wedlist.get(0),wedlist.get(1),wedlist.get(2),wedlist.get(3),wedlist.get(4),wedlist.get(5),wedlist.get(6));


                                JSONArray thrusday = jObj.getJSONArray("thursday");
                                ArrayList<String> thrlist = new ArrayList<String>();
                                for(i=0;i<thrusday.length();i++) {
                                    String name = thrusday.getString(i);
                                    thrlist.add(name);

                                }
                                mydb.create_tt("THRUSDAY",thrlist.get(0),thrlist.get(1),thrlist.get(2),thrlist.get(3),thrlist.get(4),thrlist.get(5),thrlist.get(6));


                                JSONArray friday = jObj.getJSONArray("friday");
                                ArrayList<String> frilist = new ArrayList<String>();
                                for(i=0;i<friday.length();i++) {
                                    String name = friday.getString(i);

                                    frilist.add(name);
                                }
                                mydb.create_tt("FRIDAY",frilist.get(0),frilist.get(1),frilist.get(2),frilist.get(3),frilist.get(4),frilist.get(5),frilist.get(6));



                              
                            

                            
                      

                          


                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                //hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
               // SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                String pass = "chandan111";
                String unm = "ra1411008020062";

                params.put("regno", unm);
                params.put("pass", pass);

                return params;
            }

        };
        
        
        Mysingleton.getInstance(getApplicationContext()).addtoRequestque(stringRequest);
        Intent intent = new Intent(getApplicationContext(), Show_timetable.class);
        startActivity(intent);
    }
}
