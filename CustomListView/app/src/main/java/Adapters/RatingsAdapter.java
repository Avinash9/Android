package Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.avinashgarg.customlistview.MainActivity;

import java.util.ArrayList;

import Resources.Ratings;


public class RatingsAdapter extends BaseAdapter {

    private MainActivity mainActivity;
    private ArrayList<Ratings> ratings;

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
        return null;
    }


}
