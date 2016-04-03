/**Attention! Please modify changes here for this class. 
 * 
 * Description: This class handles about fragment. Shows this (;-D} :].
 * 
 * Remove resources if not modified. Only insert modified resources name.
 * RESOURCES: layout/aboutfragment.xml
 * 
 * Remove function if not modified. Only insert modified function name.
 * FUNCTIONS:
 * onCreateView()
 * onStart()
 * 
 *  
 * Increment this no. if this class is modified.
 * VER 0.1
 */
package com.akash.sonu.pocketnurse;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment {
	
	public AboutFragment() {
		
	}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.aboutfragment, container, false);
		
		
		return rootView;
		
	}
	@Override
	public void onStart() {
		super.onStart();
		
		
	}
	
}

