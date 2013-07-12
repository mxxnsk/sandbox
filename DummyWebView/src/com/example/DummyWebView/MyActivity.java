package com.example.DummyWebView;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MyActivity extends Activity {


    private boolean sizeDecreased = false;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final WebView webView = (WebView)findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                //Log.d("Console", consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);    //To change body of overridden methods use File | Settings | File Templates.
            }
        });


        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int width = 0;
                if(webView.getLayoutParams().width <= 0) {
                    width = webView.getMeasuredWidth();
                } else {
                    width = webView.getLayoutParams().width;
                }
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(width + (sizeDecreased ? 1 : -1), webView.getMeasuredHeight() );
                p.addRule(RelativeLayout.BELOW, R.id.header);
                p.addRule(RelativeLayout.ABOVE, R.id.footer);


                webView.setLayoutParams(p);
                sizeDecreased = !sizeDecreased;
            }
        });
        String orient = getDeviceDefaultOrientation() == Configuration.ORIENTATION_PORTRAIT ? "0p" : "0l";
      // webView.loadUrl("http://ipadv2-test.washingtonpost.com/__ipad2/fragment/stage/article?device-mode=android7#article/blogs/style-blog/wp/2013/06/12/j-courtney-sullivan-leaps-from-the-engagements-to-her-wedding/?_blog");
        webView.loadUrl("http://ipadv2-test.washingtonpost.com/__ipad2/fragment/stage/section-front?device-mode=android7&do=" +orient+"#sections/all");
       //  webView.loadUrl("http://10.90.5.15:8080/__ipad2/fragment/stage/section-front/?debug=true&device-mode=android7#sections/all");
        //webView.loadUrl("http://google.com");

        //webView.loadUrl("http://ipadv2-test.washingtonpost.com/__ipad2/fragment/stage/article?device-mode=android7#article/blogs/worldviews/wp/2013/06/27/indian-tv-reporter-fired-after-filing-flood-report-from-the-shoulders-of-a-local-victim/?_blog");
    }


    public int getDeviceDefaultOrientation() {

        WindowManager windowManager =  (WindowManager) getSystemService(WINDOW_SERVICE);

        Configuration config = getResources().getConfiguration();

        int rotation = windowManager.getDefaultDisplay().getRotation();

        if ( ((rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) &&
                config.orientation == Configuration.ORIENTATION_LANDSCAPE)
                || ((rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) &&
                config.orientation == Configuration.ORIENTATION_PORTRAIT)) {
            return Configuration.ORIENTATION_LANDSCAPE;
        }
        else
        return Configuration.ORIENTATION_PORTRAIT;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        Log.d("Console", "Woooo! " + ((newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) ? "PORT" : "LAND"));
        super.onConfigurationChanged(newConfig);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
//http://ipadv2-test.washingtonpost.com/__ipad2/fragment/stage/section-front?device-mode=android10#...