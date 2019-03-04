package com.gvdev.odontolar;

import android.app.*;
import android.content.*;
import android.os.*;
import android.webkit.*;
import android.widget.*;

public class MainActivity extends Activity 
{
	private Api api;
	private ImageView imgLogo;
	private Activity ctx = MainActivity.this;
	
	String cookie;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//instance the api
		api = new Api(this);
		
		//logo
		imgLogo = (ImageView) findViewById(R.id.logo);
		imgLogo.setLayoutParams(new LinearLayout.LayoutParams(api.getScreenWidth() - api.getScreenWidth() / 10, api.getScreenHeight() / 9));
		
		new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					if(api.isInternetConnected()) {
						WebView webview = new WebView(MainActivity.this);//(WebView) findViewById(R.id.mainWebView);
						webview.setWebViewClient(new WebViewClient() {

								@Override
								public void onPageFinished(WebView view, String url) {
									super.onPageFinished(view, url);
									cookie = CookieManager.getInstance().getCookie(url);
									
									if(cookie.indexOf("logged=") == -1) {
										startActivity(new Intent(ctx, LoginOrRegister.class));
										finish();
									} else {
										Intent i = new Intent(MainActivity.this, MyWebView.class);
										Bundle bundle = new Bundle(); 
										bundle.putString("url", "http://crashlanding.pe.hu/client-perfil.php?app=1");
										i.putExtras(bundle);
										startActivity(i);
									}
								}
							});
							webview.loadUrl("http://crashlanding.pe.hu");
						
					} else {
						api.dialogNoInternet();
					}
				}
		}, 4000);
    }
}
