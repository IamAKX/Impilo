package com.akash.sonu.pocketnurse;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;


public class BloodRequestForm extends ActionBarActivity {
    Spinner bGroups;
    TextView txtDate;
    Button sendReq;
    int day,month,year;
    static final int DIALOG_ID = 0;
    EditText name,address,hospital,contact,message;
    Spinner bbank;
    String bankname[];
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request_form);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#F44336"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Send Blood Request");
        name = (EditText)findViewById(R.id.name);
        address = (EditText)findViewById(R.id.address);
        hospital = (EditText)findViewById(R.id.hospital);
        contact = (EditText)findViewById(R.id.contact);
        bbank = (Spinner)findViewById(R.id.bloodBank1);
        message = (EditText)findViewById(R.id.message);
        sendReq = (Button)findViewById(R.id.send);
        bGroups=(Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.blood_groups,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bGroups.setAdapter(adapter);
        bGroups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
                if(!cd.isConnectingToInternet()) {
                    Snackbar.make(v, "Check your Internet Connection and try again!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
                else{
                sendBloodRequest();
                }
            }
        });
        final Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
        if(!cd.isConnectingToInternet()) {
            Snackbar.make(getWindow().getDecorView(), "Check your Internet Connection and try again!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }
        else {
            new BloodbankList().BankList(getBaseContext());
        }
        bankname = new BloodbankList().getBankList(getBaseContext());
        ArrayAdapter<String> adBank=new ArrayAdapter<String>(BloodRequestForm.this,
                android.R.layout.simple_list_item_1,bankname);
        adBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bbank.setAdapter(adBank);
        showDialogOnButtonClick();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blood_request_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDialogOnButtonClick()
    {
        txtDate=(TextView)findViewById(R.id.txtdate);
        txtDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }
    protected Dialog onCreateDialog(int id)
    {
        if(id==DIALOG_ID)
        {
            return new DatePickerDialog(this,dpickerListener,year,month,day);
        }

            return null;
    }
    private DatePickerDialog.OnDateSetListener dpickerListener=new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int years, int monthOfYear, int dayOfMonth) {
            year=years;
            month=monthOfYear+1;
            day=dayOfMonth;

            txtDate.setText(day+"/"+month+"/"+year);
        }
    };



    void sendBloodRequest() {



//        if (new MainActivity().userName.getText().toString().equalsIgnoreCase("USER NAME")) {
//            AlertDialog.Builder builder =
//                    new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
//
//            builder.setMessage("You have to Log In with your Google Account for sending a blood request.");
//            builder.setPositiveButton("OK", null);
//
//            builder.show();
//        } else {

            String param = "";
            try {

                param = URLEncoder.encode("type", "UTF-8")
                        + "=" + URLEncoder.encode("new", "UTF-8");

                param += "&" + URLEncoder.encode("name", "UTF-8")
                        + "=" + URLEncoder.encode(name.getText().toString(), "UTF-8");
                param += "&" + URLEncoder.encode("hospital", "UTF-8")
                        + "=" + URLEncoder.encode(hospital.getText().toString(), "UTF-8");
                param += "&" + URLEncoder.encode("bbank", "UTF-8")
                        + "=" + URLEncoder.encode(bbank.getSelectedItem().toString().trim(), "UTF-8");

                param += "&" + URLEncoder.encode("address", "UTF-8")
                        + "=" + URLEncoder.encode(address.getText().toString(), "UTF-8");
                param += "&" + URLEncoder.encode("bgroups", "UTF-8")
                        + "=" + URLEncoder.encode(bGroups.getSelectedItem().toString(), "UTF-8");
                param += "&" + URLEncoder.encode("contact", "UTF-8")
                        + "=" + URLEncoder.encode(contact.getText().toString(), "UTF-8");
                param += "&" + URLEncoder.encode("message", "UTF-8")
                        + "=" + URLEncoder.encode(message.getText().toString(), "UTF-8");
                param += "&" + URLEncoder.encode("date", "UTF-8")
                        + "=" + URLEncoder.encode(txtDate.getText().toString(), "UTF-8");
                param += "&" + URLEncoder.encode("custEmail", "UTF-8")
                        + "=" + URLEncoder.encode(MainActivity.userEmail.getText().toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            //CONNECTING TO SERVER.
            HttpConnection http = new HttpConnection(ServerPath.SERVER_URL+"bloodrequest.php");
            http.sendPost(param);
            while(http.serverReply().trim().equals(null))
            {

            }
            Toast.makeText(getBaseContext(),"Blood request sent. Thank you!",Toast.LENGTH_LONG).show();
            name.setText("");
            hospital.setText("");
            address.setText("");
            contact.setText("");
            message.setText("");
            finish();
        //}
    }


}
