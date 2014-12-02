package Adapters;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.avinashgarg.webservices.Country;
import com.example.avinashgarg.webservices.MainActivity;
import com.example.avinashgarg.webservices.R;

public class CountryAdapter  extends  BaseAdapter{

    private MainActivity activity;
    private ArrayList<Country> countries;

    public CountryAdapter(MainActivity activity, ArrayList<Country> countries) {
        this.activity = activity;
        this.countries = countries;
    }

    @Override
    public int getCount() {
        return countries.size(); // 4
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).
                inflate(R.layout.list_row,
                        null);
        TextView stateName = (TextView) view
                .findViewById(R.id.txtStateName);
        TextView stateCapital = (TextView) view
                .findViewById(R.id.txtCapitalName);

        stateName.setText(countries.get(position).stateName);
        stateCapital.setText(countries.get(position).stateCapital);

        return view;
    }


}
