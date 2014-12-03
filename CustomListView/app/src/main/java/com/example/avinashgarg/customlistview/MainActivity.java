package com.example.avinashgarg.customlistview;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Adapters.RatingsAdapter;
import Resources.Ratings;


public class MainActivity extends Activity {
    private String jsonUrl="http://api.androidhive.info/json/movies.json";
    private ListView moviesRatingList;
    private RatingsAdapter ratingsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadResult downloadResult=new DownloadResult();
        downloadResult.execute(jsonUrl);
        moviesRatingList=(ListView)findViewById(R.id.lstRatingList);
    }

    public class DownloadResult extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {
            String url=params[0];
            try{
                    return getJsonData(url);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try{
                processData(result);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }
    }

    public String getJsonData(String url) throws Exception
    {
        URL jsonUrl=new URL(url);
        HttpURLConnection httpURLConnection=(HttpURLConnection) jsonUrl.openConnection();
//        Log.i("service","status is"+httpURLConnection.getResponseCode());
        if(httpURLConnection.getResponseCode()==200)
        {
            return fetchData(httpURLConnection);
        }
        else
        {
            throw  new Exception() ;
        }
    }

    public void processData(String result) throws Exception
    {
//        Log.i("service","result data is:::::::");
        ArrayList<Ratings> ratings = new ArrayList<Ratings>();
        JSONArray jsonArray = new JSONArray(result);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject ratingObject = jsonArray.getJSONObject(i);
            String title = ratingObject.getString("title");
            String  rating= ratingObject.getString("rating");

            JSONArray genreArry = ratingObject.getJSONArray("genre");
            ArrayList<String> genre = new ArrayList<String>();
            for (int j = 0; j < genreArry.length(); j++) {
                genre.add((String) genreArry.get(j));
            }
            String image=ratingObject.getString("image");
            String yearOfRelease=ratingObject.getString("releaseYear");
            ratings.add(new Ratings(title,image,rating,yearOfRelease, genre));
        }
        ratingsAdapter =new RatingsAdapter(this,ratings);
        moviesRatingList.setAdapter(ratingsAdapter);


    }

    public String fetchData(HttpURLConnection connection) throws Exception
    {

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
