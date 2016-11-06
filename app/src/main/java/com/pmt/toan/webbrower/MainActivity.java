package com.pmt.toan.webbrower;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton btnBack, btnNext, btnReload, btnPlay;
    Button btnOk;
    EditText edtLink;
    WebView web;
   // ProgressBar proBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        references();


        web.setWebViewClient(new WebViewClient());

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = edtLink.getText().toString().trim();
                web.loadUrl("http://" + link);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newScreen = new Intent(MainActivity.this, MyActivity.class);
                newScreen.putExtra("Link", web.getUrl());
                startActivity(newScreen);
            }
        });

        edtLink.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    String link = edtLink.getText().toString().trim();
                    web.loadUrl("http://" + link);
                    edtLink.setText(link);
                }
               // edtLink.setText(edtLink.getText().toString().trim());
                return false;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(web.canGoBack())
                {
                    web.goBack();
                    edtLink.setText(web.getUrl());
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Can't go to back",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(web.canGoForward())
                {
                    web.goForward();
                    edtLink.setText(web.getUrl());
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Can't go to next",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.reload();
            }
        });

        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            web.getSettings().setOffscreenPreRaster(true);
        }
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            web.getSettings().setAllowFileAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            web.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setPluginState(WebSettings.PluginState.ON);
        edtLink.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                edtLink.setText("");
                return false;
            }
        });

    }

    public void references()
    {
        btnBack = (ImageButton)findViewById(R.id.btnBack);
        btnNext = (ImageButton)findViewById(R.id.btnNext);
        btnReload = (ImageButton)findViewById(R.id.btnReload);
        btnOk = (Button)findViewById(R.id.btnOk);
        web = (WebView)findViewById(R.id.web);
        edtLink = (EditText)findViewById(R.id.edtLink);
        btnPlay = (ImageButton)findViewById(R.id.btnPlay);
     //   proBar = (ProgressBar)findViewById(R.id.progressBar);
    }

}
