package com.example.avinashgarg.customlistview;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import Adapters.RatingsAdapter;


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
        return "";
    }

    public void processData(String result) throws Exception
    {

    }



}
