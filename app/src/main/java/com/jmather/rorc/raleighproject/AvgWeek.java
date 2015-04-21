package com.jmather.rorc.raleighproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class AvgWeek extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avg_week);

        //week is saturday to friday

        /*String date = "04/19/2015";
        String day = "sat";

        TextView todayDate = (TextView)findViewById(R.id.date);
        todayDate.setText(date);

        InfoCalculator ic = new InfoCalculator();
       // int timeDrivenWeek = InfoCalculator.totalTimeWeek(date);
        /*TextView totalTimeText = (TextView)findViewById(R.id.weekAT);
        int totalTimeInHours = timeDrivenWeek/60;
        int minutesLeftover = timeDrivenWeek%60;
        totalTimeText.setText(totalTimeInHours + ":" + minutesLeftover);

        double totalDistanceWeek = InfoCalculator.totalDistanceWeek(date);
        TextView totalDistanceText = (TextView)findViewById(R.id.weekAD);
        totalDistanceText.setText(totalDistanceWeek + "");

        double averageSpeed = totalDistanceWeek/(timeDrivenWeek/60);
        TextView averageSpeedText = (TextView)findViewById(R.id.weekAS);
        averageSpeedText.setText(averageSpeed + "mph");*/
    }
public void sendemail(View view)
{
    Intent intent = new Intent(this, sendEmail.class);
        startActivity(intent);    
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_avg_week, menu);
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
}
