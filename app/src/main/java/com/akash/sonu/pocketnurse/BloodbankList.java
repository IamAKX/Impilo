package com.akash.sonu.pocketnurse;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Akash on 4/3/2016.
 */

public class BloodbankList {
    void BankList(Context ct)
    {


        SharedPreferences sp = ct.getSharedPreferences("BankList", Activity.MODE_PRIVATE);
        SharedPreferences.Editor sEdit = sp.edit();
        int c=0;
        String param = "";
        HttpConnection http1 = new HttpConnection("http://pocketnurse.16mb.com/pn/getBankList.php");
        http1.text="";
        try {
            param = URLEncoder.encode("type", "UTF-8")
                    + "=" + URLEncoder.encode("new", "UTF-8");
            http1.sendPost(param);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String rply=http1.text;

        int alpha=0;
        while(rply.equals(null)||rply.length()==0){
            rply=http1.serverReply();
            Log.i("###", "" + (alpha++));
        }

        rply=rply.trim();

        char ch;
        int noOfRows = sp.getInt("NoOfBank",0);
        int fetchedNoOfRows = Integer.parseInt(rply.substring(0,rply.indexOf("#")));
        if(fetchedNoOfRows<noOfRows)
            return;
        int i=rply.indexOf("#")+1;
        String word="";
        while(i<rply.length())
        {
            ch=rply.charAt(i);
            if(ch!='#')
            {
                word+=ch;
            }
            else
            {
                sEdit.putString("Bank"+c,word);
                word="";
                c++;
            }
            i++;
        }
        sEdit.putInt("NoOfBank", c);
        sEdit.commit();
        http1.text="";
    }


    String[] getBankList(Context c)
    {
        SharedPreferences sp = c.getSharedPreferences("BankList", Activity.MODE_PRIVATE);
        SharedPreferences.Editor sEdit = sp.edit();

        int l = sp.getInt("NoOfBank",0);
        String[] ar = new String[l];
        for(int i = 0;i<l;i++)
            ar[i] = sp.getString("Bank"+i,"No Bank");
        return ar;
    }
}


