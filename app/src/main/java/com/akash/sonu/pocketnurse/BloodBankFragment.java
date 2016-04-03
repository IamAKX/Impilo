/**Attention! Please modify changes here for this class. 
 * 
 * Description: This class handles blood bank fragment. 
 * 
 * Remove resources if not modified. Only insert modified resources name.
 * RESOURCES: layout/bloodbankfragment.xml
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

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Vector;

public class BloodBankFragment extends Fragment {
	ListView listView;
	private View rootView;
	Vector v=new Vector();
	private ArrayList<Item> m_parts = new ArrayList<Item>();
	private Runnable viewParts;
	private ItemAdapter m_adapter;
	static String text="default";
	private PopupWindow pwindo;
	static String text1="0";
	private Button btnCall;
	TextView search,Ap,Am,Bp,Bm,Op,Om,ABp,ABm,bank_name;
	private Button btnClosePopup;
    String bankname[];
	public BloodBankFragment() {
		
	}
	ListView list;
	AutoCompleteTextView tv;
	ImageView iv;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.bloodbankfragment, container, false);


		return rootView;
		
	}
	ProgressDialog pd;
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		m_parts.clear();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		ConnectionDetector cd = new ConnectionDetector(getActivity());
		if(!cd.isConnectingToInternet()) {
			Snackbar.make(getActivity().getWindow().getDecorView(), "Check your Internet Connection and try again!", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();

		}
		else {
			new BloodbankList().BankList(getActivity());
		}
        bankname = new BloodbankList().getBankList(getActivity());
		final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;
		tv	= (AutoCompleteTextView)getView().findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String> ac_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,bankname);
        tv.setThreshold(1);
        tv.setAdapter(ac_adapter);

		iv = (ImageView)getView().findViewById(R.id.ImageViewsearch);
		listView = (ListView) getActivity().findViewById(R.id.bbflistBank);
		m_adapter = new ItemAdapter(getActivity(), R.layout.banklist, m_parts);
		iv.setOnClickListener(new View.OnClickListener() {
								  @Override
								  public void onClick(View v) {

                                      ConnectionDetector cd = new ConnectionDetector(getActivity().getApplicationContext());
                                      if (!cd.isConnectingToInternet())
                                          Snackbar.make(getActivity().getWindow().getDecorView(), "Oppsss..Please check your internet connection..", Snackbar.LENGTH_LONG)
                                                  .setAction("Action", null).show();
                                      else
                                          if(sendreq()) {

                                              listView.setAdapter(m_adapter);
                                              onActivityCreated(Bundle.EMPTY);
                                          }


									  // ListView Item Click Listener
									  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                                          @Override
                                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                              int itemPosition = position;


                                              Toast.makeText(getActivity().getApplicationContext(),
                                                      "Position :" + itemPosition + "  ListItem : ", Toast.LENGTH_LONG)
                                                      .show();


                                          }

                                      });


									  Toast.makeText(getActivity().getBaseContext(), "Searching...", Toast.LENGTH_LONG).show();


								  }
							  }

		);



		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(),"Fetching Details ...",Toast.LENGTH_LONG).show();
				ConnectionDetector cd = new ConnectionDetector(getActivity().getApplicationContext());
				if (!cd.isConnectingToInternet())
                    Snackbar.make(view, "Check your Internet Connection and try again!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                // ListView Clicked item index
				int itemPosition = position;


				try {
// We need to get the instance of the LayoutInflater
					String bn = v.get(itemPosition).toString().substring(0, v.get(itemPosition).toString().indexOf("#"));
					final String bp = v.get(itemPosition).toString().substring(1 + v.get(itemPosition).toString().indexOf("#"));
					Thread.sleep(1000);
					String data = setBloodValue(bn, bp);
					System.out.print(data);
					//Toast.makeText(getActivity().getBaseContext(), bn + "\n" + bp, Toast.LENGTH_SHORT).show();
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

					pwindo = new PopupWindow(layout, (int) (0.8 * screenWidth), (int)(0.8*screenHeight), true);
					pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

					btnClosePopup = (Button) layout.findViewById(R.id.button);
					btnClosePopup.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							Ap.setText("0");
							Am.setText("0");
							Bp.setText("0");
							Bm.setText("0");
							ABp.setText("0");
							ABm.setText("0");
							Op.setText("0");
							Om.setText("0");
							pwindo.dismiss();

						}
					});
					btnCall = (Button) layout.findViewById(R.id.button2);
					btnCall.setOnClickListener(new View.OnClickListener() {
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



		viewParts = new Runnable(){
			public void run(){

				handler.sendEmptyMessage(0);
			}
		};

		Thread thread =  new Thread(null, viewParts, "MagentoBackground");
		thread.start();


	}
	private boolean sendreq() {

		String param="";
		try {
			param= URLEncoder.encode("type", "UTF-8")
					+ "=" + URLEncoder.encode("new", "UTF-8");
			param+="&"+ URLEncoder.encode("bbname", "UTF-8")
					+ "=" + URLEncoder.encode(tv.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		//CONNECTING TO SERVER.
		final HttpConnection http=new HttpConnection("http://pocketnurse.16mb.com/pn/search.php");


		http.sendPost(param);


        int i=0;
        text=null;
        text=http.serverReply().trim();
        while(text.equals(null)||i==30000) {
            text = http.serverReply().trim();
            i++;
        }
        Log.i("value of i", i + " ");

		//int i;
		System.out.print("bbbbbbbbbbbbbbbbbbb" + text);
        if(text.equals(null))
            return false;
        else
            return true;
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


		Thread thrd=new Thread(new Runnable() {
			@Override
			public void run() {
				String s="";
				s=http.serverReply();
				text1=s;
			}
		});
		thrd.start();

		//int i;
		return text1;

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
			listView.setAdapter(m_adapter);
		}
	};



}
