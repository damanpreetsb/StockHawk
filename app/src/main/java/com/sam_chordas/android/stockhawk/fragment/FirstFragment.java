package com.sam_chordas.android.stockhawk.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sam_chordas.android.stockhawk.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private TextView name, lastprice, change, changepercent, high, low, open;
    private String stock;


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        Bundle symbol = getArguments();
        stock = symbol.getString(getResources().getString(R.string.symbol_bundle));

        name = (TextView) rootView.findViewById(R.id.name);
        high = (TextView) rootView.findViewById(R.id.high);
        low = (TextView) rootView.findViewById(R.id.low);
        open = (TextView) rootView.findViewById(R.id.open);
        lastprice = (TextView) rootView.findViewById(R.id.lastprice);
        change = (TextView) rootView.findViewById(R.id.change_value);
        changepercent = (TextView) rootView.findViewById(R.id.change_percent);
        data();
        return rootView;
    }

    public void data() {
        try {
            final String BASE_URL = "http://empyrean-aurora-455.appspot.com/service.php?quote=yes&symbol=";

            String url = BASE_URL + stock;
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                name.setText(obj.getString("Name"));
                                lastprice.setText(obj.getString("LastPrice"));
                                change.setText(obj.getString("Change"));
                                String s = obj.getString("ChangePercent")+"%" ;
                                changepercent.setText(s);
                                high.setText(obj.getString("High"));
                                low.setText(obj.getString("Low"));
                                open.setText(obj.getString("Open"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof NoConnectionError) {
                        Toast.makeText(getContext(), "No internet connections!", Toast.LENGTH_SHORT).show();
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
