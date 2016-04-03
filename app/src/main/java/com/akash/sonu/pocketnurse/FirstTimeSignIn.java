/**Attention! Please modify changes here for this class. 
 * 
 * Description: This class handles Login activity. 
 * 
 *
 * RESOURCES: 
 * 
 * Remove function if not modified. Only insert modified function name.
 * FUNCTIONS:
 * 
 * 
 *  
 * Increment this no. if this class is modified.
 * VER 0.1
 */
package com.akash.sonu.pocketnurse;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;


public class FirstTimeSignIn extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener{
	
	private ImageView imgbtn;
	private TextView label,vertPlus;
	private GoogleApiClient mGoogleApiClient;
	private String lable1="Signing in...";
	private ConnectionResult conRes;
	GestureDetector gestureDetector;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		setContentView(R.layout.activity_signin);
		imgbtn=(ImageView)findViewById(R.id.gbutton);
		label=(TextView)findViewById(R.id.infolable1);
		
		
		imgbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				resolveConnection();

			}
		});
		label.setText(lable1);
		imgbtn.setVisibility(ImageView.INVISIBLE);
		
		vertPlus=(TextView)findViewById(R.id.verticalPlus);
		vertPlus.setVisibility(TextView.INVISIBLE);

		
        
        
		mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(Plus.API)
        .addScope(Plus.SCOPE_PLUS_LOGIN)
        .build();

		gestureDetector=new GestureDetector(this, new GestureListener() );



		
	}
	
	 @Override
	    protected void onStart() {
	        super.onStart();
	            	mGoogleApiClient.connect();
	    }

	    
	
	public void resolveConnection()
	{
		if (conRes.hasResolution()) {
            try {
                conRes.startResolutionForResult(this, conRes.getErrorCode());
                
            } catch (IntentSender.SendIntentException e) {
              mGoogleApiClient.connect();
            }
        }
		
	
	}
	
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		Toast.makeText(getBaseContext(), "Login Failed: "+connectionResult.toString(), Toast.LENGTH_LONG).show();
		label.setText(R.string.label1);
		vertPlus.setVisibility(TextView.VISIBLE);
		TranslateAnimation animation_right = new TranslateAnimation( 200.0f,0.0f,0.0f, 0.0f);
		animation_right.setDuration(2000);  // animation duration
		vertPlus.startAnimation(animation_right);
		imgbtn.setVisibility(ImageView.VISIBLE);
		conRes=connectionResult;
		if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, connectionResult.getErrorCode());
                
            } catch (IntentSender.SendIntentException e) {
               mGoogleApiClient.connect();
            }
        }
		
	}
	@Override
	public void onConnected(Bundle arg0) {
		if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {

            Intent i =new Intent(getBaseContext(),MainActivity.class);
            startActivity(i);
            finish();
        }
		
	}
	
	

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent e) {


		return gestureDetector.onTouchEvent(e);
	}


	private class GestureListener extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}
		// event when double tap occurs
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			float x = e.getX();
			float y = e.getY();
			Log.d("Double Tap", "Tapped at: (" + x + "," + y + ")");
			Toast.makeText(getBaseContext(),"Force entry to main activity!",Toast.LENGTH_SHORT).show();
			Intent i =new Intent(getBaseContext(),MainActivity.class);
			startActivity(i);
			finish();
			return true;
		}
	}


}
