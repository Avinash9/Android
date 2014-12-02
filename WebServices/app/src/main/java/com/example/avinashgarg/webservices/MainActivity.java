package com.example.avinashgarg.webservices;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Adapters.CountryAdapter;
//import serviceCalls.TestJson;


public class MainActivity extends Activity {


    private String json_url="http://beta.json-generator.com/api/json/get/DKblYqs";
    public ListView countryList;
    public CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TestJson jsonData= new TestJson();
        jsonData.execute(json_url);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryList=(ListView) findViewById(R.id.lstCountries);
    }


//    public void testClick(View v)
//    {
//        Toast.makeText(this,"hu",Toast.LENGTH_SHORT).show();
//    }



    private void processData(String result) throws Exception {
        ArrayList<Country> countries = new ArrayList<Country>();
        JSONArray jsonArray = new JSONArray(result);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject countryObject = jsonArray.getJSONObject(i);
            String stateName = countryObject.getString("capital");
            String stateCapital = countryObject.getString("state");
            android.util.Log.i("service", "country list is" + stateName+stateCapital);
            countries.add(new Country(stateName, stateCapital));
        }

        android.util.Log.i("service", "country list is" + countries);


        countryAdapter =new CountryAdapter(this,countries);
        countryList.setAdapter(countryAdapter);

    }


    public class TestJson extends AsyncTask<String , Void , String> {


        @Override
        protected void onPreExecute() {
            android.util.Log.i("service", "executing pre execute!!!!!!!!!!!!!!!!!!");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                android.util.Log.i("service", "executing do in background");
                String jsonUrl = params[0];
                android.util.Log.i("service", "json url is!!!!!!!!!!>>>>>>>>>  " + jsonUrl);
                String jsonData = getJsonData(jsonUrl);

                android.util.Log.i("service", "after do in background!!!!!!!!!!-----------!" + jsonData);
                return jsonData;

            } catch (Exception ex) {
                android.util.Log.i("service", "in exception!!!!!!!!!!!!!!!!!!00000");
                android.util.Log.e("service", "exception", ex);
                ex.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            android.util.Log.i("service", "executing post execute");
            android.util.Log.i("service", "result is" + result);

            try {
                processData(result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }




        private String getJsonData(String jsonUrl) throws Exception {

            URL url = new URL(jsonUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            android.util.Log.i("service", "response code is *******-" + connection.getResponseCode());

            StringBuilder stringBuilder = new StringBuilder();
            String tmpString = "";


            InputStream inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(streamReader);
            while ((tmpString = buffer.readLine()) != null) {
                stringBuilder.append(tmpString);
                stringBuilder.append(System.getProperty("line.separator"));
            }
            inputStream.close();
            streamReader.close();
            buffer.close();

            String result = stringBuilder.toString();


            return result;

        }


    }



    }
