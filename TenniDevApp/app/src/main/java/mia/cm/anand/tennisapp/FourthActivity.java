package mia.cm.anand.tennisapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        tp1 =(TextView) findViewById(R.id.tp1);
        p2=(TextView) findViewById(R.id.tp2);
        final TextView p1 = (TextView) findViewById(R.id.tp1);
        final TextView p2 = (TextView) findViewById(R.id.tp2);
        p1.setText( getIntent().getStringExtra("Player1"));
        p2.setText( getIntent().getStringExtra("Player2"));

    }
        public void onClick(View v){
            if (v.getId() == R.id.bback) ;
            {
                Intent i = new Intent(FourthActivity.this, ThirdActivity.class);
                startActivity(i);
            }
        }
    }
