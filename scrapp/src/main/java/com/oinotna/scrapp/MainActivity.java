package com.oinotna.scrapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.appcompat.app.AppCompatActivity;

import com.oinotna.scrapp.actions.UIActions;
import com.oinotna.scrapp.core.Node;
import com.oinotna.scrapp.service.MyAccessibilityService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    public static boolean run=false;
    public static SharedPreferences pref;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        startActivityForResult(intent, 0);

    }
    public void onEnableAccClick(View view) {
        /*i = new Intent(this, MyAccessibilityService.class);
        startService(i);*/
        run=true;
        MyAccessibilityService.running=false;
        leggiAmici();
        //Log.v("ciao", "***** porco dio button");
        //leggiAmici();
        //getOpenFacebookIntent("100008022731461");
        //PackageManager pm = getPackageManager();
        //Intent intent = pm.getLaunchIntentForPackage("com.instagram.android");
        //startActivity(intent);
        //startActivity(getOpenFacebookIntent("100008022731461"));
    }
    public static Intent getOpenFacebookIntent(String id) {

        /*try {
            Uri uri = Uri.parse("fb://facewebmodal/f?href=" + "https://m.facebook.com/mirko.cozza.79");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100015111702584"));
            startActivity(intent);
        } catch(Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/appetizerandroid")));
        }*/

        try {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/"+id)); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/arkverse")); //catches and opens a url to the desired page
        }

    }
    public void trovaInfo()
    {

        FindableNode node = new FindableNode().setClassName("android.view.ViewGroup");
        List<AccessibilityNodeInfo> o = null;
        boolean trovato = false;
        while (!trovato)
        {
            o = Node.waitForNode(node,1000);
            try {
                if(!trovato)
                {
                    for(int i=0; i<o.size();i++)
                    {
                        if(o.get(i).getText() != null && o.get(i).getText().toString().contains("About Info"))
                        {
                            trovato=true;
                            UIActions.click(o.get(i));
                        }
                    }
                }
                else
                    Log.v("ciao","è vuoto");
            }
            catch (Exception e)
            {
                Log.v("ciao","Errore:"+e.getMessage());
            }
            if(!trovato)
                UIActions.scrollDown();
            UIActions.wait(2000);
        }

    }

    public void leggiAmici() {
        //InputStream file = this.getResources().openRawResource(R.raw.amici);
        //String riga = "";
        //1549251065
        //ciao a tutti
        //100000498221063
        //da vedere perchè non prende instagram
        //startActivity(getOpenFacebookIntent("100000498221063"));
        while (true)
        {
            try {

                //String id = getHTML("http://192.168.178.27:4567/idf");
                ExecutorService pool = Executors.newFixedThreadPool(2);
                String id="";
                getHttpTask task = new getHttpTask("http://192.168.178.27:4567/idf");
                Future<String> res = pool.submit(task);
                id = res.get(1000L, TimeUnit.MILLISECONDS);
                Thread.sleep(100);
                startActivity(getOpenFacebookIntent(id));
                Thread.sleep(4000);
                trovaInfo();
                Thread.sleep(6000);
                cliccaIndietro();
                Thread.sleep(5000);
                cliccaIndietro();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void cliccaIndietro()
    {
        FindableNode node = new FindableNode().setClassName("android.widget.ImageView");
        List<AccessibilityNodeInfo> o = Node.waitForNode(node,1000);
        try {
            if(o!=null)
            {
                Log.v("ciao", "nodi trovati:"+o.size());
                for(int i=0; i<o.size();i++)
                {
                    Log.v("ciao",""+i+" "+o.get(i).getText()+" "+o.get(i).getContentDescription());
                    if(o.get(i).getContentDescription() != null && o.get(i).getContentDescription().toString().contains("Back"))
                    {
                        UIActions.click(o.get(i));
                    }
                }
            }
            else
                Log.v("ciao","è vuoto");
        }
        catch (Exception e)
        {
            Log.v("ciao","Errore:"+e.getMessage());
        }
    }

    public void setConfig()
    {
         // 0 - for private mode4724
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("grabfrom", "barengo uncommonskills"); // usern parse with (" ")
        editor.putInt("nfollowtograb", 40);
        editor.putInt("nlikeforaccount", 4);
        editor.putInt("maxlike/h", 30);
        editor.putInt("maxfollow/h", 30);
        editor.putBoolean("seestories",true);
        editor.putBoolean("followcelegram",false);
        editor.putBoolean("followprivate",false);
        editor.putInt("profilemaxfollow",9000);
        editor.putInt("profileminfollow",100);
        editor.putInt("profilemaxfollowing",9000);
        editor.putInt("profileminfollowing",90);
        editor.putInt("start_at_h",10);
        editor.putInt("end_at_h",22);
        editor.commit();
    }
    public String readConfig()
    {
        return pref.getString("botconfig",null);
    }

    public void stop(View view){
        MyAccessibilityService.Instance.onInterrupt();
    }


}
