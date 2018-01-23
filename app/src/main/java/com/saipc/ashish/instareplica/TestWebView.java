package com.saipc.ashish.instareplica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.ashish.instareplica.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by gaurk on 8/22/2017.
 */

public class TestWebView extends AppCompatActivity {

    public static String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    //    Used for Authentication.
    public static String TOKENURL = "https://api.instagram.com/oauth/access_token";
    //    Used for getting token and User details.
    public static String APIURL = "https://api.instagram.com/v1";
    //    Used to specify the API version which we are going to use.
    public static String CALLBACKURL = "http://localhost:3000/";
    //test URl for local
//    public static String CALLBACKURL = "http://com.saipc.ashish/";
    //example redirect
//    public static String CALLBACKURL = "http://www.example.com";
    //    The callback url that we have used while registering the application.
//    public static String client_id = "c45197d692c744e4899f5351a05b2c30";
    //example.com
    public static String client_id = "138461bb1d2646f9941d2713f67c79d4";

    public static String client_secret = "be05fe07c6524b518871d38f35762459";

    String authURLString = AUTHURL + "?client_id=" + client_id + "&redirect_uri=" + CALLBACKURL + "&response_type=token&scope=likes+comments+relationships";

    String tokenURLString = TOKENURL + "?client_id=" + client_id + "&client_secret=" + client_secret + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code";

    WebView loginWebView;
    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);

        loginWebView = (WebView) findViewById(R.id.login_webview);
//        loginWebView.loadUrl(authURLString);
        loginWebView.getSettings().setJavaScriptEnabled(true); // enable javascript
        manager = new SessionManager(getApplicationContext());

//        WebView webView = new WebView(getApplicationContext());
//        webView.setVerticalScrollBarEnabled(false);
//        webView.setHorizontalScrollBarEnabled(false);
////        webView.setWebViewClient(new AuthWebViewClient());
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl(authURLString);

        loginWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                System.out.println("errorCode " + errorCode);
                System.out.println("description " + description);
                System.out.println(" failingUrl " + failingUrl);

                manager.setSpecificBabyDetail(SessionManager.KEY_CLIENT_ID, client_id);
                manager.setSpecificBabyDetail(SessionManager.KEY_CLIENT_SECRET, client_secret);
                manager.setSpecificBabyDetail(SessionManager.KEY_ACCESS_TOKEN, failingUrl.substring(failingUrl.indexOf("=") + 1));

                manager.createSignInSession(client_id, client_secret, failingUrl.substring(failingUrl.indexOf("=") + 1));

                finish();

                Intent mainactivity = new Intent(TestWebView.this, HomeActivity.class);
                startActivity(mainactivity);

            }
        });

        loginWebView.loadUrl(authURLString);
//        setContentView(loginWebView );

    }

}
