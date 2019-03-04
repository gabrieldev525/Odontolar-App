package com.gvdev.odontolar;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.webkit.*;
import android.widget.*;

public class MyWebView extends Activity 
{
	WebView webview;
	ProgressBar pb;
	LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.webview);
		super.onCreate(savedInstanceState);
		
		layout = (LinearLayout) findViewById(R.id.webviewLinearLayout);
		pb = (ProgressBar) findViewById(R.id.webviewProgressBar);
		layout.removeView(pb);
		
		Intent intent = getIntent();
		
		webview = new WebView(this);//(WebView) findViewById(R.id.mainWebView);
		String cookieString = "app=1; path=/";
		CookieManager.getInstance().setCookie("http://crashlanding.pe.hu", cookieString);
		
		webview.setWebViewClient(new WebViewClient() {
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				
				layout.removeAllViews();
				layout.addView(pb);
				
				if(url.equals("http://crashlanding.pe.hu/logout.php")) {
					startActivity(new Intent(MyWebView.this, LoginOrRegister.class));
					finish();
				}
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				
				layout.removeAllViews();
				layout.addView(webview);
				
				if(url.equals("http://crashlanding.pe.hu/logout.php")) {
					startActivity(new Intent(MyWebView.this, LoginOrRegister.class));
					finish();
				}
				
				Log.i("cookie", CookieManager.getInstance().getCookie(url));
			}
		});
		webview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

		//settings
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setSupportZoom(false);
		

		webview.loadUrl(intent.getStringExtra("url"));
		
		layout.addView(webview);
	}
	
}
