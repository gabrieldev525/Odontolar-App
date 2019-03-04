package com.gvdev.odontolar;

import android.app.*;
import android.net.*;
import android.content.*;

public class Api 
{
	private Activity ctx;
	
	public Api(Activity ctx) {
		this.ctx = ctx;
	}
	
	//return the screen width
	public int getScreenWidth() {
		return ctx.getWindowManager().getDefaultDisplay().getWidth();
	}
	//return the screen height
	public int getScreenHeight() {
		return ctx.getWindowManager().getDefaultDisplay().getHeight();
	}
	
	//check internet connection
	public boolean isInternetConnected() {
		ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}
	
	public void dialogNoInternet() {
		AlertDialog.Builder dlg = new AlertDialog.Builder(ctx);
		dlg.setTitle("Sem conexão com a internet");
		dlg.setPositiveButton("atualizar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface p1, int p2) {
					if(isInternetConnected() == false) {
						dialogNoInternet();
					} else {
						ctx.startActivity(new Intent(ctx, MainActivity.class));
						ctx.finish();
					}
				}
			});
		dlg.show();
	}
}
