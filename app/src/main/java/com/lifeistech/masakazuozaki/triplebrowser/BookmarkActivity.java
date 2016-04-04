package com.lifeistech.masakazuozaki.triplebrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        ListView listView = (ListView)findViewById(R.id.listView);

        //prepare data
        ArrayList<String> items = new ArrayList<>();
        for(int i= 0; i<30; i++) {
            items.add("items-"+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.favorite_list_item,
                items
        );
        listView.setAdapter(adapter);
    }
}
