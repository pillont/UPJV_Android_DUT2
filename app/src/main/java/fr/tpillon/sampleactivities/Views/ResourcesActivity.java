package fr.tpillon.sampleactivities.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import fr.tpillon.sampleactivities.R;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        Toast toast = Toast.makeText(this,
                R.string.empty_text,
                Toast.LENGTH_SHORT
        );
        toast.show();
    }
}