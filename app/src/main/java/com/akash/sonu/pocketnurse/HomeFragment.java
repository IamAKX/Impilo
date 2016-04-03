/**Attention! Please modify changes here for this class. 
 * 
 * Description: This class handles home fragment. Accessed location based services.
 * 
 * Remove resources if not modified. Only insert modified resources name.
 * RESOURCES: layout/homefragment.xml
 * 
 * Remove function if not modified. Only insert modified function name.
 * FUNCTIONS:
 * onCreateView()
 * onActivityCreated()
 * 
 *  
 * Increment this no. if this class is modified.
 * VER 0.1
 */
package com.akash.sonu.pocketnurse;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Vector;

public class HomeFragment extends Fragment  {
	
	private final static String TAG = "HomeFragment";
	public static String loc;
	private ImageButton fab;
	 ListView listView;
	private View rootView;
	TextView search,Ap,Am,Bp,Bm,Op,Om,ABp,ABm,bank_name;
	Vector v=new Vector();
	private ArrayList<Item> m_parts = new ArrayList<Item>();
	private Runnable viewParts;
	private ItemAdapter m_adapter;
	static String text="default";
	private Button btnClosePopup;
	private PopupWindow pwindo;
	static String text1="0";
	private Button btnCall;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private String data="";

	public HomeFragment() {
		
	}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.homefragment, container, false);

        return rootView;
		
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;

		fab=(ImageButton) getActivity().findViewById(R.id.fab);
		fab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//Toast.makeText(getActivity().getApplicationContext(),"fab!", Toast.LENGTH_LONG).show();
				ConnectionDetector cd = new ConnectionDetector(getActivity().getApplicationContext());
				if (!cd.isConnectingToInternet()) {
					Snackbar.make(v, "Check your Internet Connection and try again!", Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();

				} else {
					Intent i = new Intent(getActivity().getBaseContext(), MapsActivity.class);
					startActivity(i);
				}
			}
		});

		search=(TextView)getActivity().findViewById(R.id.search);
		 mSwipeRefreshLayout = (SwipeRefreshLayout)getActivity().findViewById(R.id.swipe_container);
		mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_dark, android.R.color.holo_red_light);

		listView = (ListView) getActivity().findViewById(R.id.listBank);

		m_adapter = new ItemAdapter(this.getActivity(), R.layout.banklist, m_parts);

		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				refreshContent();

			}
		});
				// ListView Item Click Listener
				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

						Toast.makeText(getActivity(),"Fetching Details ...",Toast.LENGTH_LONG).show();
						// ListView Clicked item index
						int itemPosition = position;


						ConnectionDetector cd = new ConnectionDetector(getActivity().getApplicationContext());
						if (!cd.isConnectingToInternet())
							Snackbar.make(view, "Check your Internet Connection and try again!", Snackbar.LENGTH_LONG)
									.setAction("Action", null).show();

						try {
// We need to get the instance of the LayoutInflater
							String bn = v.get(itemPosition).toString().substring(0, v.get(itemPosition).toString().indexOf("#"));
							final String bp = v.get(itemPosition).toString().substring(1 + v.get(itemPosition).toString().indexOf("#"));
							Thread.sleep(1000);
							 data = setBloodValue(bn, bp);


								Toast.makeText(getActivity().getBaseContext(), "Wait!! Fetching details...", Toast.LENGTH_SHORT).show();

							LayoutInflater inflater = (LayoutInflater) getActivity().getBaseContext()
									.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
							View layout = inflater.inflate(R.layout.popup,
									(ViewGroup) getActivity().findViewById(R.id.popup_element));

							bank_name = (TextView) layout.findViewById(R.id.textView9);
							 Ap = (TextView) layout.findViewById(R.id.textView18);
							 Am = (TextView) layout.findViewById(R.id.textView19);
							 Bp = (TextView) layout.findViewById(R.id.textView20);
							 Bm = (TextView) layout.findViewById(R.id.textView23);
							Op = (TextView) layout.findViewById(R.id.textView21);
							 Om = (TextView) layout.findViewById(R.id.textView22);
							 ABp = (TextView) layout.findViewById(R.id.textView24);
							  ABm = (TextView) layout.findViewById(R.id.textView25);

							bank_name.setText(bn);
							Ap.setText("A+  :  "+data.substring(0, data.indexOf("A")));
							Am.setText("A-  :  "+data.substring(1 + data.indexOf("A"), data.indexOf("B")));
							Bp.setText("B+  :  "+data.substring(1 + data.indexOf("B"), data.indexOf("C")));
							Bm.setText("B-  :  "+data.substring(1 + data.indexOf("C"), data.indexOf("D")));
							ABp.setText("AB+ :  "+data.substring(1 + data.indexOf("D"), data.indexOf("E")));
							ABm.setText("AB- :  "+data.substring(1 + data.indexOf("E"), data.indexOf("F")));
							Op.setText("O+  :  "+data.substring(1 + data.indexOf("F"), data.indexOf("G")));
							Om.setText("O-  :  "+data.substring(1 + data.indexOf("G")));
							//Toast.makeText(getActivity().getBaseContext(), (int) (0.8 * screenWidth) + "\n" + (int)(0.9*screenHeight),Toast.LENGTH_LONG).show();
							pwindo = new PopupWindow(layout, (int) (0.8 * screenWidth), (int)(0.8*screenHeight), true);
							pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

							btnClosePopup = (Button) layout.findViewById(R.id.button);
							btnClosePopup.setOnClickListener(new OnClickListener() {
								public void onClick(View v) {

									Ap.setText("0");
									Am.setText("0");
									Bp.setText("0");
									Bm.setText("0");
									ABp.setText("0");
									ABm.setText("0");
									Op.setText("0");
									Om.setText("0");
									bank_name.setText("**********");
									data="";
									pwindo.dismiss();

								}
							});
							btnCall = (Button) layout.findViewById(R.id.button2);
							btnCall.setOnClickListener(new OnClickListener() {
								public void onClick(View v) {


									Intent callIntent = new Intent(Intent.ACTION_CALL);
									callIntent.setData(Uri.parse("tel:" + bp));
									startActivity(callIntent);

								}
							});

						} catch (Exception e) {
							e.printStackTrace();
						}


					}

				});




		LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
         public void onLocationChanged(Location location) {
           //makeUseOfNewLocation(location);
        	 locationChangedListener(location);
        	 //loc="long:"+location.getLongitude()+";lat:"+location.getLatitude()+";";
        	 //Make sure that loc is not NULL
        	 //Toast.makeText(getActivity().getApplicationContext(),loc, Toast.LENGTH_LONG).show();
         }
         public void onStatusChanged(String provider, int status, Bundle extras) {}
         public void onProviderEnabled(String provider) {}
         public void onProviderDisabled(String provider) {}
       };
     locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 1, locationListener);
     locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, locationListener);

        ConnectionDetector cd = new ConnectionDetector(getActivity().getApplicationContext());
        if (!cd.isConnectingToInternet())
            Snackbar.make(getActivity().getWindow().getDecorView(), "Oppsss..Please check your internet connection..", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        else
            sendreq();
		if(m_parts==null)
		{
			Toast.makeText(getActivity(), "Your network connection is bit slow!! Please wait while we fetch blood bank list for you", Toast.LENGTH_LONG).show();
			Thread t=new Thread(){

				@Override
				public void run() {
					try {
						sleep(3000);
						onActivityCreated(Bundle.EMPTY);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};

			t.start();
		}

		viewParts = new Runnable(){
			public void run(){

					handler.sendEmptyMessage(0);
			}
		};

		Thread thread =  new Thread(null, viewParts, "MagentoBackground");
		thread.start();
		listView.setAdapter(m_adapter);

	}




	private void refreshContent() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				m_parts.clear();
				search.setText("");

				onActivityCreated(Bundle.EMPTY);

				mSwipeRefreshLayout.setRefreshing(false);}
			},2000);
	}
	
	private void sendreq() {

		String param="";
		try {
			param= URLEncoder.encode("type", "UTF-8")
					+ "=" + URLEncoder.encode("new", "UTF-8");
			param+="&"+ URLEncoder.encode("uname", "UTF-8")
					+ "=" + URLEncoder.encode("uname", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		//CONNECTING TO SERVER.
		final HttpConnection http=new HttpConnection("http://pocketnurse.16mb.com/pn/getBanks.php");


		http.sendPost(param);

        int i=0;
		text=null;
		text=http.serverReply().trim();
		while(text.equals(null)||i==30000) {
			text = http.serverReply().trim();
			i++;
		}
            Log.i("value of i", i + " ");

}




	private String setBloodValue(String bn,String bp) {

		String param="";
		try {
			param= URLEncoder.encode("type", "UTF-8")
					+ "=" + URLEncoder.encode("new", "UTF-8");
			param+="&"+ URLEncoder.encode("bname", "UTF-8")
					+ "=" + URLEncoder.encode(bn, "UTF-8");
			param+="&"+ URLEncoder.encode("bphone", "UTF-8")
					+ "=" + URLEncoder.encode(bp, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		//CONNECTING TO SERVER.
		final HttpConnection http=new HttpConnection("http://pocketnurse.16mb.com/pn/setBloodValue.php");


		http.sendPost(param);

		text1=http.serverReply();
		while(text1.equals(null))
			text1=http.serverReply();

//		Thread thrd=new Thread(new Runnable() {
//			@Override
//			public void run() {
//				String s="";
//				s=http.serverReply();
//				text1=s;
//			}
//		});
//		thrd.start();

		//int i;
		return text1;

	}




	private void locationChangedListener(Location location)
	{
		
		loc="long:"+location.getLongitude()+";lat:"+location.getLatitude();
		//Toast.makeText(getActivity().getApplicationContext(),loc, Toast.LENGTH_LONG).show();
	}
	
	public float getMyLat()
	{
		float f=Float.parseFloat(loc.substring(loc.indexOf("lat:")+4,loc.length()));
		//Toast.makeText(getActivity().getBaseContext(),"hello", Toast.LENGTH_LONG).show();
		if(loc==null)
			f=0;
		return f;
	}
	public float getMyLong()
	{
		float f=Float.parseFloat(loc.substring(5,loc.indexOf(";")));
		if(loc==null)
			f=0;
		return f;
	}
	


	@Override
	public void onStart() {
		super.onStart();
		
		
	}



	private Handler handler = new Handler()
	{

		public void handleMessage(Message msg)
		{

			int c=0;
			String w="";
			while(c<text.length())
			{
				if(text.charAt(c)=='#')
				{

					String n=w.substring(0,w.indexOf('!'));
					String p=w.substring(1+w.indexOf('!'),w.indexOf('*'));
					String a=w.substring(1+w.indexOf('*'));
					v.add(n+"#"+a);
					m_parts.add(new Item(n, p,a));
					w="";
				}
				else
				{
					w+=text.charAt(c);
				}
				c++;
			}
			m_adapter = new ItemAdapter(getActivity(), R.layout.card, m_parts);

			// display the list.
			listView.setAdapter(m_adapter);
		}
	};

}
