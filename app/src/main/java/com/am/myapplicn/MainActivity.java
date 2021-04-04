package com.am.myapplicn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button b; // declaration abv oncre
    EditText e;
    String s;
    String name;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.button);
        e=findViewById(R.id.editTextTextPersonName);
        t=findViewById(R.id.textView);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animaldetail nn = new Animaldetail();
                nn.execute();
            }
        });
    }

    private class Animaldetail extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            Dog br = new Dog();
            String res = br.makeServiceCall("https://api.androidhive.info/contacts/");

            try {

                JSONObject jsonObject = new JSONObject(res);

                JSONArray jArray =jsonObject.getJSONArray("contacts");

                JSONObject jo = jArray.getJSONObject(7);

                name = jo.getString("name");

            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            t.setText(name);
        }
    }
}