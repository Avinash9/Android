package com.example.avinashgarg.firstapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;




public class TextActivity extends Activity {

    private  ArrayList<String> spinnerList;
    private ArrayAdapter<String> spinnerListAdapter;
    private Spinner spinner;


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    android.text.format.DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        Log.i("test","started text activity!!!!!!!!!");

        spinnerList=getSpinnerList();

        spinner=(Spinner)findViewById(R.id.planets_spinner);

        spinnerListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spinnerList);

        spinner.setAdapter(spinnerListAdapter);
    }


    public void showTimePickerDialog(View v)
    {
        DialogFragment newFragment=new TimePickerFragment();
        newFragment.show(getFragmentManager(),"timePicker");

    }



    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment=new DatePickerFragment();
        newFragment.show(getFragmentManager(),"datePicker");

    }


   public ArrayList<String> getSpinnerList()
   {
       spinnerList=new ArrayList<String>();
       spinnerList.add("spinner1");
       spinnerList.add("spinner2");
       spinnerList.add("spinner3");
       spinnerList.add("spinner4");
       spinnerList.add("spinner5");
       return spinnerList;

   }

   public void onCheckboxClicked(View view)
   {
       boolean checked=((CheckBox) view).isChecked();

       if (checked)
       {
           Toast.makeText(this,"checked",Toast.LENGTH_SHORT).show();
       }
       else
       {
           Toast.makeText(this,"unchecked",Toast.LENGTH_SHORT).show();
       }


   }


}



