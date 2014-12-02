//package com.example.network;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//public class MainActivity extends Activity implements OnClickListener {
//
//    private ProgressDialog progressDialog;
//    private ListView countryList;
//    private Button btnFetchData;
//    private CountryAdapter countryAdapter;
//    private String string_url = "http://www.json-generator.com/api/json/get/cjxZNrFsky?indent=2";
//
//	/*
//	 * All below code would execute in the UI thread by default
//	 *
//	 * Never perform the network operations in UI thread
//	 */
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btnFetchData = (Button) findViewById(R.id.btnFetchData);
//        btnFetchData.setOnClickListener(this);
//
//        countryList = (ListView) findViewById(R.id.lstCountries);
//
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Fetching Countries");
//        progressDialog.setCancelable(false);
//    }
//
//    @Override
//    public void onClick(View v) {
//        new DownloadTask().execute(string_url);
//    }
//
//    class DownloadTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                return downloadData(params[0]);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            progressDialog.dismiss();
//
//            if (result == null) {
//                Toast.makeText(MainActivity.this, "Error fetching countries!",
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                try {
//                    processData(result);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private void processData(String result) throws Exception {
//
//        ArrayList<Country> countries = new ArrayList<Country>();
//
//        JSONArray jsonArray = new JSONArray(result);
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject countryObject = jsonArray.getJSONObject(i);
//            String countryName = countryObject.getString("name");
//            String countryCurrency = countryObject.getString("currency");
//            countries.add(new Country(countryName, countryCurrency));
//        }
//
//        countryAdapter = new CountryAdapter(this, countries);
//        countryList.setAdapter(countryAdapter);
//    }
//
//    private String downloadData(String url) throws Exception {
//        URL networkUrl = new URL(url);
//        HttpURLConnection connection = (HttpURLConnection) networkUrl
//                .openConnection();
//        Log.i("edureka", "Response Code : " + connection.getResponseCode());
//
//        connection.connect();
//
//        StringBuilder stringBuilder = new StringBuilder();
//        String tempString = "";
//
//        InputStream inputStream = connection.getInputStream();
//        InputStreamReader streamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferedReader = new BufferedReader(streamReader);
//
//        while ((tempString = bufferedReader.readLine()) != null) {
//            stringBuilder.append(tempString);
//            stringBuilder.append(System.getProperty("line.separator"));
//        }
//
//        inputStream.close();
//        streamReader.close();
//        bufferedReader.close();
//
//        return stringBuilder.toString();
//
//    }
//}
