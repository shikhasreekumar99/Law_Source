package com.example.myfirsttest.law_source;


import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Splashactivity extends Activity {
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		StartAnimations();
		/** Creates a count down timer, which will be expired after 5000 milliseconds */
		new CountDownTimer(5000,5000) {

			/** This method will be invoked on finishing or expiring the timer */
			@Override
			public void onFinish() {
				/** Creates an intent to start new activity */
				Intent intent = new Intent(Splashactivity.this, Login.class);

				/** Creates a new activity, on finishing this timer */
				startActivity(intent);

				/** Close this activity screen */
				finish();

			}

			/** This method will be invoked in every 1000 milli seconds until
			 * this timer is expired.Because we specified 1000 as tick time
			 * while creating this CountDownTimer
			 */
			@Override
			public void onTick(long millisUntilFinished) {

			}
		}.start();
	}
	private void StartAnimations() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
		anim.reset();
		LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
		l.clearAnimation();
		l.startAnimation(anim);
		anim = AnimationUtils.loadAnimation(this, R.anim.translate);
		anim.reset();
		ImageView iv = (ImageView) findViewById(R.id.logo);
		iv.clearAnimation();
		iv.startAnimation(anim);

	}
}