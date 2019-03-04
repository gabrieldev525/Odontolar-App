package com.gvdev.odontolar;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout.*;

public class LoginOrRegister extends Activity 
{
	private Api api;
	private Activity ctx = LoginOrRegister.this;
	private Button loginButton, registerButton;
	private LinearLayout layout, layout2;
	private ImageView imgLogo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_or_register);
		
		//instance the api
		api = new Api(ctx);
		
		//imgLogo = (ImageView) findViewById(R.id.login_or_registerImageView);
		//imgLogo.setLayoutParams(new LinearLayout.LayoutParams(api.getScreenWidth() - api.getScreenWidth() / 8, api.getScreenHeight() / 10));
		
		
		layout = (LinearLayout) findViewById(R.id.login_or_registerLinearLayout);
		layout2 = (LinearLayout) findViewById(R.id.login_or_registerLayout2);
		
		//login button
		loginButton = (Button) findViewById(R.id.login_btn);
		loginButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					Intent i = new Intent(LoginOrRegister.this, MyWebView.class);
					Bundle bundle = new Bundle(); 
					bundle.putString("url", "http://crashlanding.pe.hu/_login.php?app=1");
					i.putExtras(bundle);
					startActivity(i);
					finish();
				}
		});
		loginButton.setLayoutParams(new LinearLayout.LayoutParams(api.getScreenWidth() - api.getScreenWidth() / 10, LinearLayout.LayoutParams.WRAP_CONTENT));
		layout2.removeView(loginButton);
		layout2.addView(loginButton);
		
		//register button
		registerButton = (Button) findViewById(R.id.cadastrar_btn);
		registerButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					Intent i = new Intent(LoginOrRegister.this, MyWebView.class);
					Bundle bundle = new Bundle(); 
					bundle.putString("url", "http://crashlanding.pe.hu/_cadastrar.php?app=1");
					i.putExtras(bundle);
					startActivity(i);
					finish();
				}
		});
		LayoutParams params = new LinearLayout.LayoutParams(api.getScreenWidth() - api.getScreenWidth() / 10, LinearLayout.LayoutParams.WRAP_CONTENT);
		registerButton.setLayoutParams(params);
		params.setMargins(0, api.getScreenHeight() / 40, 0, 0);
		layout2.removeView(registerButton);
		layout2.addView(registerButton);
	}
}
