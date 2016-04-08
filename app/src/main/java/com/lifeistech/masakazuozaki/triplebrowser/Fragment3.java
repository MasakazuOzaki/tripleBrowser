package com.lifeistech.masakazuozaki.triplebrowser;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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



    }






}





