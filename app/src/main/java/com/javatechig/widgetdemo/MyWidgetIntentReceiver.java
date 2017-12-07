package com.javatechig.widgetdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

public class MyWidgetIntentReceiver extends BroadcastReceiver {

	private static boolean isClick = false;
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(WidgetUtils.WIDGET_UPDATE_ACTION)) {
			updateWidgetPictureAndButtonListener(context);
		}
	}

	private void updateWidgetPictureAndButtonListener(final Context context) {
		if(isClick) return;
		isClick = true;
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);


		Random rand = new Random();
		int a = rand.nextInt(2);
		remoteViews.setTextViewText(R.id.widget_button, a == 0 ? "YES" : "NO");

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				isClick = false;
				RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
						R.layout.widget_layout);

				remoteViews.setTextViewText(R.id.widget_button, "?");

				// re-registering for click listener
				remoteViews.setOnClickPendingIntent(R.id.widget_button,
						MyWidgetProvider.buildButtonPendingIntent(context));

				MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(),
						remoteViews);
			}
		},2000);

		// re-registering for click listener
		remoteViews.setOnClickPendingIntent(R.id.widget_button,
				MyWidgetProvider.buildButtonPendingIntent(context));

		MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(),
				remoteViews);
	}
}
