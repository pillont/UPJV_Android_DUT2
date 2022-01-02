package fr.tpillon.sampleactivities.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.Date;

import fr.tpillon.sampleactivities.R;

public class LifeCycleActivity extends AppCompatActivity {
    private Date stopDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        Toast.makeText(this, R.string.created_activity_message,Toast.LENGTH_SHORT)
            .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, R.string.destroyed_activity_message,Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopDate = new Date(System.currentTimeMillis());
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Date now = new Date(System.currentTimeMillis());
        long intervalInMs = now.getTime() - stopDate.getTime();
        long intervalInSec = intervalInMs /1000;


        String result = this.getString(R.string.exit_time_message, intervalInSec);
        Toast.makeText(this, result,Toast.LENGTH_LONG)
            .show();
        stopDate = null;
    }
}

