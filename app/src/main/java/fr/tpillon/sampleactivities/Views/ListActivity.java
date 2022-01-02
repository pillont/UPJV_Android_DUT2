package fr.tpillon.sampleactivities.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import fr.tpillon.sampleactivities.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button resourcesActivityButton= findViewById(R.id.resourcesActivityButton);
        Button interactionsActivityButton = findViewById(R.id.interactionsActivityButton);
        Button senderActivityButton = findViewById(R.id.senderActivityButton);
        Button lifeCycleActivityButton = findViewById(R.id.lifeCycleActivityButton);
        Button userActivityButton = findViewById(R.id.userActivityButton);

        resourcesActivityButton.setOnClickListener((view)-> showResourcesActivity());
        interactionsActivityButton.setOnClickListener((view)-> showInteractionsActivity());
        senderActivityButton.setOnClickListener((view)-> showSenderActivity());
        lifeCycleActivityButton.setOnClickListener((view)-> showLifeCycleActivity());
        userActivityButton.setOnClickListener((view)-> showUserActivity());
    }

    private void showUserActivity() {
        Intent intent = new Intent(this, StoredUserActivity.class);
        startActivity(intent);
    }

    private void showLifeCycleActivity() {
        Intent intent = new Intent(this, LifeCycleActivity.class);
        startActivity(intent);
    }

    private void showSenderActivity() {
        Intent intent = new Intent(this, SenderActivity.class);
        startActivity(intent);
    }

    private void showInteractionsActivity() {
        Intent intent = new Intent(this, InteractionsActivity.class);
        startActivity(intent);
    }

    private void showResourcesActivity() {
        Intent intent = new Intent(this, ResourcesActivity.class);
        startActivity(intent);
    }
}