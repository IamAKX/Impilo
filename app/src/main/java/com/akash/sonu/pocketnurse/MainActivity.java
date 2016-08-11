package com.akash.sonu.pocketnurse;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
//import android.support.v4.app.ActionBarDrawerToggle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class MainActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {


    private ImageButton FAB ;
    private boolean mIsResolving = false;
        private boolean mShouldResolve = false;
        private DrawerLayout drawerLayout;
        private ListView listView;
        private String itemname[]={"Home","Find Blood Bank","Blood Request","About"};
        Integer[] imgid={
            R.drawable.home,
            R.drawable.bloodbank,
            R.drawable.bloodreq,
            R.drawable.about};
        private ActionBarDrawerToggle drawerListner;
        public static GoogleApiClient mGoogleApiClient;
       public static TextView userName,userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {   super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#F44336"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        userName=(TextView)findViewById(R.id.userName);
        userEmail=(TextView)findViewById(R.id.userEmail);
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.sign_in_button) {
                    ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
                    if(!cd.isConnectingToInternet())
                        Snackbar.make(v, "Check your Internet Connection and try again!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    onSignInClicked();
                }

            }
        });
        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        listView = (ListView)findViewById(R.id.drawerList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new DrawerItemClickListener());

        drawerListner = new ActionBarDrawerToggle(this, drawerLayout,R.string.drawer_open, R.string.drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        selectItem(0);
        drawerLayout.setDrawerListener(drawerListner);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();


        userName.setText("GUEST USER");

        FAB = (ImageButton) findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
                if(!cd.isConnectingToInternet()) {
                    Snackbar.make(v, "Check your Internet Connection and try again!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                Intent i = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);}

            }
        });
           }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        drawerListner.syncState();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerListner.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerListner.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConnected(Bundle bundle) {

        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            String personName = currentPerson.getDisplayName();
            String personEmail = Plus.AccountApi.getAccountName(mGoogleApiClient);
            String personGooglePlusProfile = currentPerson.getUrl();
            userName.setText(personName);
            userEmail.setText(personEmail);

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);

            int g=currentPerson.getGender();
            String gender="";
            if(g==0)
                gender="M";
            else if(g==1)
                gender="F";
            else if(g==2)
                gender="O";
            //SETUP FOR SERVER CONNECTION. METHOD 'POST'.
            String param="";
            try {
                param= URLEncoder.encode("type", "UTF-8")
                        + "=" + URLEncoder.encode("new", "UTF-8");

                param+="&"+ URLEncoder.encode("uname", "UTF-8")
                        + "=" + URLEncoder.encode(personName, "UTF-8");
                param+="&"+ URLEncoder.encode("gender", "UTF-8")
                        + "=" + URLEncoder.encode(gender, "UTF-8");
                param+="&"+ URLEncoder.encode("gurl", "UTF-8")
                        + "=" + URLEncoder.encode(personGooglePlusProfile, "UTF-8");
                param+="&"+ URLEncoder.encode("email", "UTF-8")
                        + "=" + URLEncoder.encode(personEmail, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            //CONNECTING TO SERVER.
            HttpConnection http=new HttpConnection(ServerPath.SERVER_URL+"login.php");
            http.sendPost(param);
            Toast.makeText(getBaseContext(),"name = "+personName+"\nprofile="+personGooglePlusProfile,Toast.LENGTH_LONG).show();


        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }



    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, connectionResult.getErrorCode());
                mIsResolving = true;
            } catch (IntentSender.SendIntentException e) {

                mIsResolving = false;
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }
    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();

        // Show a message to the user that we are signing in.

    }
    private class DrawerItemClickListener implements  ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    //Initialization
    public static String currentSelection="Home";

    private void selectItem(int position) {
        listView.setItemChecked(position, true);

        Log.i("select",itemname[position] );
        Log.i("select2",""+listView.getCheckedItemPosition() );
        //setTitle(listArray[position]);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;
        switch(position)
        {
            case 0:
                fragment=new HomeFragment();
                fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit();
                setTitle("Nearby Blood Banks");
                break;
            case 1:
                fragment=new BloodBankFragment();
                fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit();
                setTitle("Search Blood Bank");
                break;


            case 2:
                Intent i=new Intent(this,BloodRequestForm.class);
                startActivity(i);
                break;
            case 3:
                fragment=new AboutFragment();
                fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit();
                setTitle("About Us");
                break;

        }


        drawerLayout.closeDrawers();


    }



}
