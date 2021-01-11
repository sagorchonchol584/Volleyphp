package com.example.phptest;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RegistrationActivity extends AppCompatActivity implements ProductsAdapter.ItemListener {
    EditText ed_username,ed_email,ed_password;
    String str_name,str_email,str_password;
    String url = "http://192.168.2.117/android/test.php";

    ArrayList arrayList;
    List<Product> productList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);



      //  GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.HORIZONTAL, false);
     //   recyclerView.setLayoutManager(manager);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product = array.getJSONObject(i);
                                productList.add(new Product(
                                        product.getInt("productid"),
                                        product.getString("producttitle"),
                                        product.getString("productdescription"),
                                        product.getDouble("productrating"),
                                        product.getString("productcategory"),
                                        product.getDouble("productprice"),
                                        product.getString("productimage")
                                ));
                            }


                       //     ProductsAdapter adapter = new ProductsAdapter(RegistrationActivity.this, productList,this);
                      //     recyclerView.setAdapter(adapter);



                            ProductsAdapter adapter = new ProductsAdapter(RegistrationActivity.this, productList, RegistrationActivity.this);
                            recyclerView.setAdapter(adapter);
                            AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(RegistrationActivity.this, 500);
                            recyclerView.setLayoutManager(layoutManager);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }


    @Override
    public void onItemClick(int item) {

        Toast.makeText(getApplicationContext(),  " is clicked"+item, Toast.LENGTH_SHORT).show();

    }
}
