package mia.cm.anand.tennisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    public static Button button1;
    public static Button button2;
    public static Button aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListener();

        button2 = (Button) findViewById(R.id.startGame);
        button2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent("mia.cm.anand.tennisapp.MainActivity");
                                           finish();
                                       }
                                   }
        );

        aboutUs = (Button) findViewById(R.id.about_us);
        aboutUs.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                Intent intent = new Intent("mia.cm.anand.tennisapp.about_us");
                startActivity(intent);
            }
        }
        );
    }

    private void OnClickButtonListener() {
        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent("mia.cm.anand.tennisapp.SecondActivity");
                                           startActivity(intent);
                                       }
                                   }
        );
    }


}
