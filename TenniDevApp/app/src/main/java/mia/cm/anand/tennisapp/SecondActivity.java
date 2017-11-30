package mia.cm.anand.tennisapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {


    private static TextView textViewObj;

    public static TextView getTextViewObj() {
        return textViewObj;
    }

    public static void setTextViewObj(TextView textViewObj) {
        SecondActivity.textViewObj = textViewObj;
    }

    TextView player1, player2;
    Button start;
    String TAG = "Tennis";
    String prefsName = "myPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        player1 = (TextView) findViewById(R.id.player1name);
        player2 = (TextView) findViewById(R.id.player2name);
        start = (Button) findViewById(R.id.startGame);

               start.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent i = new Intent(SecondActivity.this , ThirdActivity.class);
                                           i.putExtra("player1", player1.getText().toString());
                                           i.putExtra("player2", player2.getText().toString());
                                           startActivity(i);
                   }
               });

        // obtains the SharedPreferences object
        SharedPreferences prefs=getSharedPreferences(prefsName, MODE_PRIVATE);

        // reads from sharedPreferences
        // The second argument defines the return value if it does not exist
        String pl1 = prefs.getString("player1", null);
        String pl2 = prefs.getString("player2", null);

        if(pl1!=null){
            // writes to the left EditText
            player1.setText(pl1);
            player2.setText(pl2);
        }

    }

    public void onStart(){  // called just after onCreate (state = Started - transient state)
        super.onStart();
        Log.d(TAG, "onStart");
    }

    public void onResume(){  // called just after onStart (state = Resumed - activity visible and active )
        super.onResume();
        Log.d(TAG, "onResume");
    }

    public void onPause(){  // called when the activity is partially hidden (state = Paused)
        // Android guarantees that this method is called before the activity is killed
        super.onPause();
        Log.d(TAG, "onPause");

        // obtains the SharedPreferences object
        SharedPreferences prefs=getSharedPreferences(prefsName, MODE_PRIVATE);

        // obtains an editor to it - needed only for writing
        SharedPreferences.Editor editor = prefs.edit();

        EditText player1 = (EditText) findViewById(R.id.player1name);
        EditText player2 = (EditText) findViewById(R.id.player2name);

        // includes or changes the value of variables
        editor.putString("player1", player1.getText().toString());
        editor.putString("player2", player2.getText().toString());

        // we need to commit at the end
        editor.commit();
    }

    public void onStop(){ // called when the activity is completely hidden (state = Stop)
        super.onStop();
        Log.d(TAG, "onStop");
    }

    public void onRestart(){  // called when the activity is about to return (stated = Started - transient state)
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    public void onDestroy(){ // called when the activity is destroyed (state = Destroyed)
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void onSaveInstanceState(Bundle outState){ // called when the activity is going to be stopped
        // called before onStop (before or after onPause)
        // not called when the activity is closed by the user (back button)
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    public void onRestoreInstanceState(Bundle outState){ // called when the activity is recreated after being destroyed by the system
        super.onRestoreInstanceState(outState);          // called after onStart
        Log.d(TAG, "onRestoreInstanceState");
    }

    public void onUserLeaveHint(){  // called when the activity goes to background by user selection (e.g. home button)
        super.onUserLeaveHint();    // called before onPause
        Log.d(TAG, "onUserLeaveHint");
    }


    //---------------------- Thread (inner class)  ----------------------------
    class MyThread extends Thread{
        int counter;

        MyThread(int _counter){ counter = _counter;}

        public void run(){

            Log.d(TAG, "Thread started");

            final EditText eRight = (EditText) findViewById(R.id.text2);
            while (counter>0){
                try {
                    Thread.sleep(1000);
                    counter--;
                    // a thread can not access views from the UI
                    eRight.post(new Runnable(){ // pode usar qualquer view para fazer o post

                        @Override
                        public void run() {
                            eRight.setText(String.valueOf(counter));

                        }
                    });

                } catch (InterruptedException e) {
                    Log.e(TAG, e.toString());
                }
            }
            Log.d(TAG, "Thread ended");
        }
    }

}




