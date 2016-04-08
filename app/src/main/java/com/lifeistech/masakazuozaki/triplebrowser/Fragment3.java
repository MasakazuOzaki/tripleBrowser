package com.lifeistech.masakazuozaki.triplebrowser;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lifeistech.masakazuozaki.triplebrowser.entity.Bookmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MasakazuOzaki on 2016/01/31.
 */
public class Fragment3 extends Fragment {
    WebView myWebView;
    EditText editText3;
    int position;
    String text3;
    int count;
    String http3;
    String www3;
    List<Bookmark> bookmarks;


    public static Fragment3 newInstance(int position) {
        Fragment3 f = new Fragment3();
        Bundle b = new Bundle();
        b.putInt("position", position);
        f.setArguments(b);

        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");

        bookmarks = new ArrayList<>();
        //prepare data
        bookmarks = loadData();
        if(bookmarks == null){
            bookmarks = new ArrayList<>();
        }
        http3 ="https://";
        www3 = "www.";
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        myWebView = (WebView) view.findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://www.google.co.jp/");
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        setRetainInstance(true);
        createButtons(view);
        return view;
    }


    private void saveList(List<Bookmark> list) {
        SharedPreferences preferences = getActivity().getSharedPreferences("key", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        preferences.edit().putString("topic", gson.toJson(list)).commit();
        Log.d("save", "saved");
    }

    private void createButtons(View view) {
        final Context context = view.getContext();
        Button button = (Button) view.findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myWebView.canGoBack()) {
                    myWebView.goBack();
                } else {
                    Toast.makeText(context, "There is no web site", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button button2 = (Button) view.findViewById(R.id.next);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myWebView.canGoForward()) {
                    myWebView.goForward();
                } else {
                    Toast.makeText(context, "There is no web site", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button button3 = (Button) view.findViewById(R.id.home);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT);
                myWebView.loadUrl("https://www.google.co.jp/");
            }
        });

        Button button4 =(Button) view.findViewById(R.id.go);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText3 = (EditText) getActivity().findViewById(R.id.editText3);
                editText3.selectAll();
                text3 = editText3.getText().toString();

                if(text3.startsWith(http3)){
                    myWebView.loadUrl(text3);
                    editText3.setText("");
                    
                }
                else if(text3.startsWith(www3)){
                    String url3 ="https://"+text3;
                    myWebView.loadUrl(url3);
                    Toast.makeText(context, "Loading..."+text3, Toast.LENGTH_SHORT).show();
                    editText3.setText("");
                }
                else {
                    String search3 = "https://www.google.com/search?q="+text3;
                    myWebView.loadUrl(search3);
                    Toast.makeText(context, "Loading...Searched: ", Toast.LENGTH_SHORT).show();
                    editText3.setText("");
                }
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Button button5 = (Button)view.findViewById(R.id.star);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = myWebView.getUrl();
                String title = myWebView.getTitle();

                Bookmark bookmark = new Bookmark(title, url);

                bookmarks.add(bookmark);

                saveList(bookmarks);

                Toast.makeText(getActivity(), "Saved Bookmark: " + title, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Bookmark> loadData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("key", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        ArrayList<Bookmark> loadDataList = gson.fromJson(preferences.getString("topic", ""), new TypeToken<List<Bookmark>>() {
        }.getType());
        return loadDataList;
    }
}





