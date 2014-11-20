package com.example.avinashgarg.firstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("test","!!!!!!!!!!!!!!!!!!!!!!!!");
        setContentView(R.layout.activity_main);

        final EditText userName=(EditText)findViewById(R.id.username_edittext );
        final EditText password=(EditText)findViewById(R.id.password_edittext);

        final Button loginButton=(Button)findViewById(R.id.login_button);

        userName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i== EditorInfo.IME_ACTION_SEND) {
                    Log.i("test","inside textbox listner");
                    handled = true;
                }
//                Context context = getApplicationContext();
//                Log.i("test","inside textbox listner");
//                Toast.makeText(context,"hello",Toast.LENGTH_LONG).show();
                return true;


            }
        });




//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("test","clicked!!!!!!!!!!!!!!!!!");
//                Intent textActivityIntent=new Intent(MainActivity.this,TextActivity.class);
//                startActivity(textActivityIntent);
//
//            }
//        });
    }

    public void sendMessage(View view){
        Log.i("test","clicked by second method!!!!!!!!!!!!!!!!!");
        Intent textActivityIntent=new Intent(MainActivity.this,TextActivity.class);
        startActivity(textActivityIntent);
    }

}
