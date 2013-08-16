package com.example.way2go;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;


import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.IntentSender;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks, 
												GooglePlayServicesClient.OnConnectionFailedListener
												{
	public final static String EXTRA_MESSAGE = "com.example.way2go.MESSAGE";
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    
    private LocationClient mLocationClient;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mLocationClient = new LocationClient(this, this, this);
      //  System.out.println("Connected to Location Services");
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
*/
    
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) 
    {
        // Do something in response to button
 /*   	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText)findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    	*/
    	
    	
    	Location currentLocation;
    	if( mLocationClient.isConnected())
    	{
    	//currentLocation = mLocationClient.getLastLocation();
    	
    //	System.out.println(currentLocation.toString());
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText)findViewById(R.id.edit_message);
    	String message="BLAH";// = currentLocation.toString();
       	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    	}
    	else
    	{
    		Intent intent = new Intent(this, DisplayMessageActivity.class);
        	EditText editText = (EditText)findViewById(R.id.edit_message);
        	String message = "YAHOO";
           	intent.putExtra(EXTRA_MESSAGE, message);
        	startActivity(intent);
    	
    	}
    }
    
    
    public static class ErrorDialogFragment extends DialogFragment 
    {
    	@SuppressLint("NewApi")
		private Dialog mDialog;
    	
    	@TargetApi(11)
		public ErrorDialogFragment()
    	{
    		super();
    		mDialog = null;
    	}
    	
    	public void setDialog(Dialog dialog)
    	{
    		mDialog = dialog;
    	}
    	
    	public Dialog onCreateDialog(Bundle savedInstanceState)
    	{
    		return mDialog;
    	}
    }
    
    protected void onActivityResult( int requestCode, int resultCode, Intent data)
    {
    	switch(requestCode)
    	{
    	case CONNECTION_FAILURE_RESOLUTION_REQUEST:
    			switch ( resultCode )
    			{
    			case Activity.RESULT_OK:
    				break;
    			}
    	break;
    	}
    }
    
 /*   private boolean servicesConnected()
    {
    	int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    	
    	if (ConnectionResult.SUCCESS == resultCode )
    	{
    		Log.d("Location Updates", "Google Play services is available.");
    		return true;
    	}
    	else
    	{
    		int errorCode = resultCode;
    		
    		Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(errorCode,  
    																this,
    																CONNECTION_FAILURE_RESOLUTION_REQUEST);
 
    		if( errorDialog != null)
    		{
    			ErrorDialogFragment errorFragment = new ErrorDialogFragment();
    			errorFragment.setDialog(errorDialog);
    			
    			errorFragment.show(getFragmentManager(), null);     //(getSupportFragmentManager(), "Location Updates");
    		
    		}
    	}
    	return true;
    }*/
    
    public void onConnected(Bundle dataBundle) {
        // Display the connection status
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

    }
    
    public void onDisconnected() {
        // Display the connection status
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
    }
    
    public void onConnectionFailed(ConnectionResult connectionResult) 
    {
        if (connectionResult.hasResolution()) 
        {
            try
            {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } 
            catch (IntentSender.SendIntentException e) 
            {
                // Log the error
                e.printStackTrace();
            }
        } 
        else
        {
       //     showErrorDialog(connectionResult.getErrorCode());
        }
    }
    
    protected void onStart()
    {
    	super.onStart();
    	mLocationClient.connect();
    	
    }
    
    protected void onStop()
   {
    	mLocationClient.disconnect();
    	super.onStop();
    }
	
	
	
												}
	