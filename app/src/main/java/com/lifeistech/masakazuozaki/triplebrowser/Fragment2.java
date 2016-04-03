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
public class Fragment2 extends Fragment {
    WebView myWebView;
    EditText editText2;
    int position;
    String text2;
    int count;
    String http2;
    String www2;


    public static Fragment2 newInstance(int position) {
        Fragment2 f = new Fragment2();
        Bundle b = new Bundle();
        b.putInt("position", position);
        f.setArguments(b);

        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");

        http2 ="https://";
        www2 = "www.";
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        myWebView = (WebView) view.findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://www.google.co.jp/");
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);




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
                editText2 = (EditText) getActivity().findViewById(R.id.editText2);
                editText2.selectAll();
                text2 = editText2.getText().toString();

                if(text2.startsWith(http2)){
                    myWebView.loadUrl(text2);
                    Toast.makeText(context, "Loading..."+text2, Toast.LENGTH_SHORT).show();
                    editText2.setText("");
                }
                else if(text2.startsWith(www2)){
                    String url2 ="https://"+text2;
                    myWebView.loadUrl(url2);
                    Toast.makeText(context, "Loading..."+text2, Toast.LENGTH_SHORT).show();
                    editText2.setText("");
                }
                else {
                    String search2 = "https://www.google.com/search?q="+text2;
                    myWebView.loadUrl(search2);
                    Toast.makeText(context, "Loading...Searched: "+text2, Toast.LENGTH_SHORT).show();
                    editText2.setText("");
                }
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });



    }
}





