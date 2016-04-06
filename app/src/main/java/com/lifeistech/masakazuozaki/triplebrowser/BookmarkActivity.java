package com.lifeistech.masakazuozaki.triplebrowser;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lifeistech.masakazuozaki.triplebrowser.R;
import com.lifeistech.masakazuozaki.triplebrowser.entity.Bookmark;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    List<Bookmark> bookmarks;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listView = (ListView)findViewById(R.id.listView);

        //prepare data
        bookmarks = loadData();
        if(bookmarks == null){
            bookmarks = new ArrayList<>();
            Bookmark bookmark = new Bookmark("empty title data", "empty url data");
            bookmarks.add(bookmark);
        }

        BookmarkAdapter adapter = new BookmarkAdapter(
                this,
                R.layout.bookmark_list_item,
                bookmarks
        );
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private ArrayList<Bookmark> loadData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("key", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        ArrayList<Bookmark> loadDataList = gson.fromJson(preferences.getString("topic",""), new TypeToken<List<Bookmark>>(){}.getType());
        return loadDataList;
    }
}
