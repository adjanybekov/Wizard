package ru.startandroid.develop.myosm;

/**
 * Created by e211104 on 09.09.2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class streetListDouble extends Activity {



    //I wanna try to add double values as well

    ListView coordListView;
    public   ArrayList<Double> cordList = new ArrayList<Double>();
    ArrayAdapter<Double> doubleArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //I have to turn off one of theh adaters when I want to use one
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streetlist_double);


        //trying to add an double values in the list

        coordListView = (ListView) findViewById(R.id.stList);
        doubleArrayAdapter = new ArrayAdapter<Double>(this,android.R.layout.simple_list_item_1,cordList);
        coordListView.setAdapter(doubleArrayAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_streets, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
