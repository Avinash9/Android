package Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.avinashgarg.customlistview.MainActivity;
import com.example.avinashgarg.customlistview.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;



import Resources.Ratings;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;




public class RatingsAdapter extends BaseAdapter {

    private MainActivity mainActivity;
    private ArrayList<Ratings> ratings;
    Bitmap bitmap;
    ImageView image;

   public RatingsAdapter(MainActivity mainActivity,ArrayList<Ratings> ratings)
   {
       this.mainActivity=mainActivity;
       this.ratings=ratings;
   }

    @Override
    public int getCount() {
        return ratings.size();
    }

    @Override
    public Object getItem(int position) {
            return ratings.get(position);
    }

    @Override
    public long getItemId(int position) {
            return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewParent) {
        Log.i("service","!!!!!!!!!!!!!!!!!!!!!!!!:::::::::::::::::::::::::::::::");
        View view = LayoutInflater.from(mainActivity).
                inflate(R.layout.list_row,
                        null);
        image=(ImageView) view.findViewById(R.id.image);





        TextView title=(TextView) view.findViewById( R.id.title);
        TextView rating=(TextView) view.findViewById( R.id.rating);
        TextView genre=(TextView) view.findViewById( R.id.genre);
        TextView releaseYear=(TextView) view.findViewById( R.id.releaseYear);

        title.setText(ratings.get(position).title);
        rating.setText(ratings.get(position).rating);
        releaseYear.setText(ratings.get(position).releaseYear);

        String genreString="";

        for(String str:ratings.get(position).genre)
        {
            genreString=str+ ", ";
        }

        Log.i("service","in adaptr:::::::::::::::::::::::::::::::"+genreString);

        genre.setText(genreString);


        Log.i("service","in adaptr:::::::::::::::::::::::::::::::"+ratings.get(position).image);





        String img_url= ratings.get(position).image;
        new LoadImage().execute(img_url);
        return view;




    }


    class  LoadImage extends AsyncTask<String,String,Bitmap>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap result) {
            if(result != null){
                image.setImageBitmap(result);

            }else{
                Toast.makeText(mainActivity, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }

    }








}
