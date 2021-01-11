package com.example.phptest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView testshow;
    Button buttonclick;
    ListView list;
    String url = "http://192.168.2.117/android/test.php";
    List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //buttonclick=(Button)findViewById(R.id.button);
      //  list=(ListView) findViewById(R.id.lst);
       // testshow=(TextView) findViewById(R.id.textView3);
        productList = new ArrayList<>();



//        buttonclick.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Loginsss();
//            }
//        });
    }


    public void Loginsss() {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");
            progressDialog.show();

            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject product = array.getJSONObject(i);
                            String id = product.getString("id");
                            String first_name = product.getString("name");

                            productList.add(new Product(
                                    product.getInt("productid"),
                                    product.getString("producttitle"),
                                    product.getString("productdescription"),
                                    product.getDouble("productrating"),
                                    product.getString("productcategory"),
                                    product.getDouble("productprice"),
                                    product.getString("productimage")
                            ));
//

                         //   testshow.setText("id "+id+"name "+first_name);
                           // testshow.setText("name "+first_name);

                       //     settergetter ss=new settergetter();
                        //    ss.setAdd(first_name);
                         //   Toast.makeText(getApplicationContext(),"Response :" + ss.getAdd().toString(), Toast.LENGTH_LONG).show();
                        }






                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                //   testshow.setText("Chack " + response.toString());
                //    Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }



            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);
        }
}
