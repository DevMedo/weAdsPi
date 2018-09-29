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
    TextView textView1 ;
    TextView textView;
    WebView webView;
    int count;
    String uidValue;
    String inte;

    VideoView videoView;
    WebView mWebView;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // button1 = (Button) findViewById(R.id.button);
        textView1 = (TextView) findViewById(R.id.textView2);
        textView = (TextView) findViewById(R.id.textView);
        webView = findViewById(R.id.webview);
        count = 0 ;

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef1 = database.getReference("Screens/Screen1/UID");
        final DatabaseReference myRef2 = database.getReference("users");

        //setupWebView();

        myRef1.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                uidValue = String.valueOf(dataSnapshot.getValue());


                String value = String.valueOf(dataSnapshot.getValue());
                System.out.println("THIS IS MY INTEREST 7 :"+value);
                textView1.setText(value);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        // Read from the database
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                     if(user.getId().equals(uidValue)){
                         // Just for testing
                    textView.setText("ID : "+user.getId()+" NAME : "+user.getName()+" INT :"+user.getIntreset());
                    inte=user.getIntreset();
                         Log.d("HERE IT IS",inte+" $ "+user.getIntreset());
                     }
                }


                String Hackthon="hackathon";
                String carString="cars";
                String sports="sports";

                    //hackathon();
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    //System.out.println(" inte "+inte);
                    System.out.println(Hackthon+" # "+inte);
                    if(Hackthon.equals(inte)){
                        // Just for testing
                        hackathon();
                    }else if(carString.equals(inte)){
                        cars();

                    }
                }





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
