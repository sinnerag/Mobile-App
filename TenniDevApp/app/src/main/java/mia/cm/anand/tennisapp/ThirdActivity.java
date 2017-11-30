package mia.cm.anand.tennisapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;


public class ThirdActivity extends AppCompatActivity {


    private int seconds = 0;      //variable
    //private boolean running;      //variable
    public int ACTUAL_POINTS_P1 = 0;
    public int ACTUAL_POINTS_P2 = 0;
    public int ACTUAL_POINTS_P1_SET_ONE = 0;
    public int ACTUAL_POINTS_P2_SET_ONE = 0;
    public int ACTUAL_POINTS_P1_SET_TWO = 0;
    public int ACTUAL_POINTS_P2_SET_TWO = 0;
    public int ACTUAL_POINTS_P1_SET_THREE = 0;
    public int ACTUAL_POINTS_P2_SET_THREE = 0;
    public int TOTAL_POINTS_P1 = 0;
    public int TOTAL_POINTS_P2 = 0;
    boolean setTwoActive, setThreeActive;
    private DatabaseTable DatabaseTable;

    Button btnP1, btnP2;
    TextView p1s1, p1s2, p1s3, p2s1, p2s2, p2s3;
    TextView nameP1, nameP2;
    /***************************/
    Button butnstart, butnreset, player1button, player2button,result;
    TextView time;
    long starttime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedtime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 0;
    int hrs = 0;
    int milliseconds = 0;
    Handler handler = new Handler();
    /***************************/


    int counter, counter1, counter2;

    String player1name, player2name;
    TextView player1;
    TextView player2;
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        runTimer();
        player1button = (Button) findViewById(R.id.player1button);
        player2button = (Button) findViewById(R.id.player2button);


        player1name = getIntent().getStringExtra("player1");
        player2name = getIntent().getStringExtra("player2");

        player1 = (TextView) findViewById(R.id.player1);
        player2 = (TextView) findViewById(R.id.player2name);

        player1.setText(player1name);
        player2.setText(player2name);

        btnP1 = (Button) findViewById(R.id.player1button);
        btnP2 = (Button) findViewById(R.id.player2button);
        p1s1 = (TextView) findViewById(R.id.p1s1);
        p1s2 = (TextView) findViewById(R.id.p1s2);
        p1s3 = (TextView) findViewById(R.id.p1s3);
        p2s1 = (TextView) findViewById(R.id.p2s1);
        p2s2 = (TextView) findViewById(R.id.p2s2);
        p2s3 = (TextView) findViewById(R.id.p2s3);
        result=(Button) findViewById(R.id.bres);
        /*************************************************/
        nameP1 = (TextView) findViewById(R.id.player1);
        nameP2 = (TextView) findViewById(R.id.player2name);
        butnstart = (Button) findViewById(R.id.start);
        butnreset = (Button) findViewById(R.id.reset);
        time = (TextView) findViewById(R.id.textViewTime);
        /*SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("Player1", "") != null){
            player1.setText(sharedPreferences.getString("player1", ""));
        }
        if(sharedPreferences.getString("Player2", "") != null){
            player2.setText(sharedPreferences.getString("player2", ""));
        }*/


        butnstart.setOnClickListener(new View.OnClickListener() {







            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

                if (t == 1) {
                    butnstart.setText("Pause");
                    starttime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimer, 0);
                    t = 0;
                } else {
                    butnstart.setText("Start");
                    time.setTextColor(Color.BLUE);
                    timeSwapBuff += timeInMilliseconds;
                    handler.removeCallbacks(updateTimer);
                    t = 1;
                }
            }
        });

        butnreset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                starttime = 0L;
                timeInMilliseconds = 0L;
                timeSwapBuff = 0L;
                updatedtime = 0L;
                t = 1;
                secs = 0;
                mins = 0;
                milliseconds = 0;
                butnstart.setText("Start");
                handler.removeCallbacks(updateTimer);
                time.setText("00:00:00");
                resetPoints();
                resetSets();
            }
        });
        /***********************************/


        btnP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointsforP1();
            }
        });

        btnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointsforP2();
            }
        });
    }


    /********/
    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
            updatedtime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            if(mins == 59){ mins = 0; hrs++; }
            milliseconds = (int) (updatedtime % 1000);
            time.setText(hrs + ":" + mins + ":" + String.format("%02d", secs));
            //+ ":" + String.format("%03d", milliseconds));
            time.setTextColor(Color.RED);
            handler.postDelayed(this, 0);
        }};
    /********/


    public static TextView getTextViewObj() {
        return null;
    }





         public void onclickStart(View view)
         {
            running=true;
         }
         public void onClickStop(View view)
         {
            running=false;
         }
          public void onClickReset(View view)
                 {
            running=false;
              seconds=0;
          }

        private void runTimer() {
            final TextView timeView = (TextView) findViewById(R.id.textViewTime);

            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    int hours = seconds / 3600;
                    int minutes = (seconds % 3600) / 60;
                    int secs = seconds * 60;

                    //String time = String.format("%d:%02:%02d", hours, minutes, secs);
                    //timeView.setText(time);
                    if (running) {
                        seconds++;
                    }

                    handler.postDelayed(this, 1000);
                }
            });
        }
    public void pointsforP1(){ ACTUAL_POINTS_P1 = ACTUAL_POINTS_P1 + 10; refreshbuttonP1();  }
    public void pointsforP2(){ ACTUAL_POINTS_P2 = ACTUAL_POINTS_P2 + 10; refreshbuttonP2();  }
    public void restforP1()  { ACTUAL_POINTS_P1 = ACTUAL_POINTS_P1 - 10; btnP1.setText("40"); }

    public void restforP2()  { ACTUAL_POINTS_P2 = ACTUAL_POINTS_P2 - 10; btnP2.setText("40"); }
    public void refreshbuttonP1(){if(btnP2.getText() == "adv"){restforP2(); btnP1.setText("adv"); }
    else if(ACTUAL_POINTS_P2 == 40 && ACTUAL_POINTS_P1 == 50){
            btnP1.setText("adv");
            restforP2();
        }else if(ACTUAL_POINTS_P1 == 50 && ACTUAL_POINTS_P2 < 40){
            setForP1();
            resetPoints();
        }
        else if(ACTUAL_POINTS_P1 == 60) {
            setForP1();
            resetPoints();
        }
        else {
            if(ACTUAL_POINTS_P1 == 10) {
                btnP1.setText("15");
                ACTUAL_POINTS_P1 = ACTUAL_POINTS_P1 + 5;
            }else if(ACTUAL_POINTS_P1 == 25){
                btnP1.setText("30");
                ACTUAL_POINTS_P1 = ACTUAL_POINTS_P1 + 5;
            }
            btnP1.setText(""+ACTUAL_POINTS_P1);
        }
    }

    public void refreshbuttonP2(){
        if(btnP1.getText() == "adv"){
            restforP1();
            btnP2.setText("adv");
        }else if(ACTUAL_POINTS_P1 == 40 && ACTUAL_POINTS_P2 == 50){
            btnP2.setText("adv");
            //restforP2();
        }else {
            if (ACTUAL_POINTS_P2 == 50 && ACTUAL_POINTS_P1 < 40) {
                setForP2();
                resetPoints();
            } else if (ACTUAL_POINTS_P2 == 60) {
                setForP2();
                resetPoints();
            } else {
                if (ACTUAL_POINTS_P2 == 10) {
                    btnP2.setText("15");
                    ACTUAL_POINTS_P2 = ACTUAL_POINTS_P2 + 5;
                } else if (ACTUAL_POINTS_P2 == 25) {
                    btnP2.setText("30");
                    ACTUAL_POINTS_P2 = ACTUAL_POINTS_P2 + 5;
                }
                btnP2.setText("" + ACTUAL_POINTS_P2);
            }
        }
    }

    public void setForP1(){
        if(!setThreeActive) {
            if (!setTwoActive) {
                if (ACTUAL_POINTS_P1_SET_ONE <= 5) {
                    ACTUAL_POINTS_P1_SET_ONE = ACTUAL_POINTS_P1_SET_ONE + 1;
                    refreshp1setone();
                } else if (ACTUAL_POINTS_P1_SET_ONE == 5 && ACTUAL_POINTS_P2_SET_ONE <= 4) {
                    ACTUAL_POINTS_P1_SET_ONE = ACTUAL_POINTS_P1_SET_ONE + 1;
                    refreshp2setone();
                    setTwoActive = true;
                }else if(ACTUAL_POINTS_P1_SET_ONE == 5 && ACTUAL_POINTS_P2_SET_ONE > 4) {
                    ACTUAL_POINTS_P1_SET_ONE = ACTUAL_POINTS_P1_SET_ONE + 1;
                    refreshp2setone();
                }else if(ACTUAL_POINTS_P1_SET_ONE == 6)
                {
                    TOTAL_POINTS_P1 = TOTAL_POINTS_P1 + 1;
                    setTwoActive = true;
                }
            } else {
                if (ACTUAL_POINTS_P1_SET_TWO <= 5) {
                    ACTUAL_POINTS_P1_SET_TWO = ACTUAL_POINTS_P1_SET_TWO + 1;
                    refreshp1settwo();
                } else if (ACTUAL_POINTS_P1_SET_TWO == 5 && ACTUAL_POINTS_P2_SET_TWO <= 4) {
                    ACTUAL_POINTS_P1_SET_TWO = ACTUAL_POINTS_P1_SET_TWO + 1;
                    refreshp1settwo();
                    TOTAL_POINTS_P1 = TOTAL_POINTS_P1 + 1;
                    setThreeActive = true;
                }else if(ACTUAL_POINTS_P1_SET_TWO == 5 && ACTUAL_POINTS_P2_SET_TWO > 4) {
                    ACTUAL_POINTS_P1_SET_TWO = ACTUAL_POINTS_P1_SET_TWO + 1;
                    refreshp1settwo();
                }else if(ACTUAL_POINTS_P1_SET_TWO == 6)
                {
                    TOTAL_POINTS_P1 = TOTAL_POINTS_P1 + 1;
                    setThreeActive = true;
                }
            }
        }else{
            if (ACTUAL_POINTS_P1_SET_THREE <= 5) {
                ACTUAL_POINTS_P1_SET_THREE = ACTUAL_POINTS_P1_SET_THREE + 1;
                refreshp1setthree();
            }else if(ACTUAL_POINTS_P1_SET_THREE == 5 && ACTUAL_POINTS_P2_SET_THREE > 4) {
                ACTUAL_POINTS_P1_SET_THREE = ACTUAL_POINTS_P1_SET_THREE + 1;
                refreshp1setthree();
            }else if(ACTUAL_POINTS_P1_SET_THREE == 6)
            {
                TOTAL_POINTS_P1 = TOTAL_POINTS_P1 + 1;
                if(TOTAL_POINTS_P1 > TOTAL_POINTS_P2) {
                    endOfgame(nameP1.getText().toString(), nameP2.getText().toString(), ACTUAL_POINTS_P1_SET_ONE, ACTUAL_POINTS_P2_SET_ONE, ACTUAL_POINTS_P1_SET_TWO, ACTUAL_POINTS_P2_SET_TWO, ACTUAL_POINTS_P1_SET_THREE, ACTUAL_POINTS_P2_SET_THREE);
                }else{
                    endOfgame(nameP2.getText().toString(), nameP1.getText().toString(), ACTUAL_POINTS_P1_SET_ONE, ACTUAL_POINTS_P2_SET_ONE, ACTUAL_POINTS_P1_SET_TWO, ACTUAL_POINTS_P2_SET_TWO, ACTUAL_POINTS_P1_SET_THREE, ACTUAL_POINTS_P2_SET_THREE);
                }
            }
        }
    }

    public void setForP2(){
        if(!setThreeActive) {
            if (!setTwoActive) {
                if (ACTUAL_POINTS_P2_SET_ONE <= 5) {
                    ACTUAL_POINTS_P2_SET_ONE = ACTUAL_POINTS_P2_SET_ONE + 1;
                    refreshp2setone();
                } else if (ACTUAL_POINTS_P2_SET_ONE == 5 && ACTUAL_POINTS_P1_SET_ONE <= 4) {
                    ACTUAL_POINTS_P2_SET_ONE = ACTUAL_POINTS_P2_SET_ONE + 1;
                    refreshp2setone();
                    setTwoActive = true;
                }else if(ACTUAL_POINTS_P2_SET_ONE == 5 && ACTUAL_POINTS_P1_SET_ONE > 4) {
                    ACTUAL_POINTS_P2_SET_ONE = ACTUAL_POINTS_P2_SET_ONE + 1;
                    refreshp2setone();
                }else if(ACTUAL_POINTS_P2_SET_ONE == 6)
                {
                    TOTAL_POINTS_P2 = TOTAL_POINTS_P2 + 1;
                }
            } else {
                if (ACTUAL_POINTS_P2_SET_TWO <= 5) {
                    ACTUAL_POINTS_P2_SET_TWO = ACTUAL_POINTS_P2_SET_TWO + 1;
                    refreshp2settwo();
                } else if (ACTUAL_POINTS_P2_SET_TWO == 5 && ACTUAL_POINTS_P1_SET_TWO <= 4) {
                    ACTUAL_POINTS_P2_SET_TWO = ACTUAL_POINTS_P2_SET_TWO + 1;
                    refreshp2settwo();
                    setThreeActive = true;
                }else if(ACTUAL_POINTS_P2_SET_TWO == 5 && ACTUAL_POINTS_P1_SET_TWO > 4) {
                    ACTUAL_POINTS_P2_SET_TWO = ACTUAL_POINTS_P2_SET_TWO + 1;
                    refreshp2settwo();
                }else if(ACTUAL_POINTS_P2_SET_TWO == 6)
                {
                    setThreeActive = true;
                }
            }
        }else if (ACTUAL_POINTS_P2_SET_THREE <= 5) {
            ACTUAL_POINTS_P2_SET_THREE = ACTUAL_POINTS_P2_SET_THREE + 1;
            refreshp2setthree();
        } else if (ACTUAL_POINTS_P2_SET_THREE == 5 && ACTUAL_POINTS_P1_SET_THREE > 4) {
            ACTUAL_POINTS_P2_SET_THREE = ACTUAL_POINTS_P2_SET_THREE + 1;
            refreshp2setthree();
        } else if (ACTUAL_POINTS_P2_SET_THREE == 6) {
            TOTAL_POINTS_P2 = TOTAL_POINTS_P2 + 1;
            if (TOTAL_POINTS_P1 > TOTAL_POINTS_P2) {
                endOfgame(nameP1.getText().toString(), nameP2.getText().toString(), ACTUAL_POINTS_P1_SET_ONE, ACTUAL_POINTS_P2_SET_ONE, ACTUAL_POINTS_P1_SET_TWO, ACTUAL_POINTS_P2_SET_TWO, ACTUAL_POINTS_P1_SET_THREE, ACTUAL_POINTS_P2_SET_THREE);
            } else
                endOfgame(nameP2.getText().toString(), nameP1.getText().toString(), ACTUAL_POINTS_P1_SET_ONE, ACTUAL_POINTS_P2_SET_ONE, ACTUAL_POINTS_P1_SET_TWO, ACTUAL_POINTS_P2_SET_TWO, ACTUAL_POINTS_P1_SET_THREE, ACTUAL_POINTS_P2_SET_THREE);
        }
    }

    public void refreshp1setone(){
        p1s1.setText(""+ACTUAL_POINTS_P1_SET_ONE);
    }
    public void refreshp1settwo(){
        p1s2.setText(""+ACTUAL_POINTS_P1_SET_TWO);
    }
    public void refreshp1setthree(){
        p1s3.setText(""+ACTUAL_POINTS_P1_SET_THREE);
    }
    public void refreshp2setone(){
        p2s1.setText(""+ACTUAL_POINTS_P2_SET_ONE);
    }
    public void refreshp2settwo(){
        p2s2.setText(""+ACTUAL_POINTS_P2_SET_TWO);
    }
    public void refreshp2setthree(){
        p2s3.setText(""+ACTUAL_POINTS_P2_SET_THREE);
    }

    public void resetPoints(){
        ACTUAL_POINTS_P1 = 0;
        ACTUAL_POINTS_P2 = 0;
        btnP1.setText("0");
        btnP2.setText("0");
    }
    public void resetSets(){
        ACTUAL_POINTS_P2_SET_TWO = 0;
        ACTUAL_POINTS_P1_SET_TWO = 0;
        ACTUAL_POINTS_P2_SET_ONE = 0;
        ACTUAL_POINTS_P1_SET_ONE = 0;
        ACTUAL_POINTS_P2_SET_THREE = 0;
        ACTUAL_POINTS_P1_SET_THREE = 0;
        refreshp2setthree();
        refreshp1setone();
        refreshp1setthree();
        refreshp2settwo();
        refreshp1settwo();
        refreshp2setone();
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public void endOfgame(String winner, String loser, final int p1s1, final int p2s1, final int p1s2, final int p2s2, final int p1s3, final int p2s3){
        int id;
        new AlertDialog.Builder(ThirdActivity.this)
                .setTitle("WIN")
                .setMessage(""+winner+" is the winner")
                .setCancelable(false)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        startActivity(new Intent(ThirdActivity.this, MainActivity.class));
                    }
                })

                .setNeutralButton("Save",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        boolean isInserted;
                        if (DatabaseTable.insertGame(

                                player1.getText().toString(),
                                player2.getText().toString(),
                                p1s1,
                                p1s2,
                                p1s3,
                                p2s1,
                                p2s2,
                                p2s3)) isInserted = true;
                        else isInserted = false;
                        if(isInserted = true)
                            Toast.makeText(ThirdActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ThirdActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                })


                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Whatever...
                    }
                }).create().show();
        SharedPreferences preferences = this.getSharedPreferences(
                "part   ", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("winner", winner);
        editor.putString("loser", loser);
        editor.putInt("p1s1", p1s1);
        editor.putInt("p1s2", p1s2);
        editor.putInt("p1s3", p1s3);
        editor.putInt("p2s1", p2s1);
        editor.putInt("p2s2", p2s2);
        editor.putInt("p2s3", p2s3);
        editor.apply();

    }



    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putInt("hrs", Integer.parseInt(String.format("%02d", hrs)));
        savedInstanceState.putInt("min", Integer.parseInt(String.format("%02d", mins)));
        savedInstanceState.putInt("sec", Integer.parseInt(String.format("%02d", secs)));
        savedInstanceState.putString("player1name", player1name);
        savedInstanceState.putString("player2name", player2name);

    }
    //onRestoreInstanceState
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        hrs = savedInstanceState.getInt("hrs");
        mins = savedInstanceState.getInt("min");
        secs = savedInstanceState.getInt("sec");
        time.setText(savedInstanceState.getInt("hrs") + ":" + savedInstanceState.getInt("min") + ":" + savedInstanceState.getInt("sec"));
        player1.setText(savedInstanceState.getString("player1name"));
        player2.setText(savedInstanceState.getString("player2name"));
    }
       public void onClick(View v) {
           if (v.getId() == R.id.bres) ;
           {


               Intent i = new Intent(ThirdActivity.this, FourthActivity.class);

               i.putExtra("Player1", getIntent().getStringExtra("Player 1 Name"));
               i.putExtra("Player2", getIntent().getStringExtra("Player 2 Name"));


               startActivity(i);
           }
       }
}