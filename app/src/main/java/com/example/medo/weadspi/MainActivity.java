package com.example.medo.weadspi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.HashMap;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    Button button1 ;
    //TextView textView1 ;
    //TextView textView;
    WebView webView;
    int count;
    String uidValue;
    String inte;
    String Hackthon="hackathon";
    String carString="cars";
    String sports="sports";
    VideoView videoView;
    WebView mWebView;
    String TEMP;

    //YouTubePlayerView pw;

    private final String YOUTUBE_PREFIX = "https://www.youtube.com/embed/";
    private void hackathon(){
        webView.getSettings().setJavaScriptEnabled(true);
        String code = "A0K5KFKKhYY";
        webView.loadUrl(YOUTUBE_PREFIX+code);
    }

    private void cars(){
        webView.getSettings().setJavaScriptEnabled(true);
        String code = "DQG_QMpHJd4";
        webView.loadUrl(YOUTUBE_PREFIX+code);
    }


    private void sports(){
        webView.getSettings().setJavaScriptEnabled(true);
              String code = "MEoDYm0v4j0";
        webView.loadUrl(YOUTUBE_PREFIX+code);
    }

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef1 = database.getReference("Screens/Screen1/UID");
    final DatabaseReference myRef2 = database.getReference("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView1 = (TextView) findViewById(R.id.textView2);
//        textView = (TextView) findViewById(R.id.textView);
        webView = findViewById(R.id.webview);
        count = 0 ;





        //setupWebView();

        myRef1.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                TEMP = uidValue;
                uidValue = String.valueOf(dataSnapshot.getValue());


            //    String value = String.valueOf(dataSnapshot.getValue());
              //  System.out.println("THIS IS MY INTEREST 7 :"+value);
//                textView1.setText(value);

                if (TEMP != uidValue){
                    CheckUserProfile();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        // Read from the database

    }
    public void CheckUserProfile(){
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if(user.getId().equals(uidValue)){
                        // Just for testing
                        //textView.setText("ID : "+user.getId()+" NAME : "+user.getName()+" INT :"+user.getIntreset());


                        inte=user.getIntreset();
                        Log.d("HERE IT IS",inte+" $ "+user.getIntreset());
                    }

                    /////////////////////////
                    //user = userSnapshot.getValue(User.class);
                    //System.out.println(" inte "+inte);
                    System.out.println(Hackthon+" # "+inte);
                    if(Hackthon.equals(inte)){
                        // Just for testing

                        hackathon();
                        inte="";

                    }else if(carString.equals(inte)){

                        cars();
                        inte="";


                    }
                    else if(sports.equals(inte)){

                        sports();
                        inte="";


                    }

                    //////////////////////
                }




//                    //hackathon();
//                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
//                    User user = userSnapshot.getValue(User.class);
//                    //System.out.println(" inte "+inte);
//                    System.out.println(Hackthon+" # "+inte);
//                    if(Hackthon.equals(inte)){
//                        // Just for testing
//                        hackathon();
//                    }else if(carString.equals(inte)){
//                        cars();
//
//                    }
//                }





                // videoView.setVideoPath();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
