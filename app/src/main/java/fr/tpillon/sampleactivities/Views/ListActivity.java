package fr.tpillon.sampleactivities.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        Button menuActivityButton = findViewById(R.id.menuActivityButton);

        // on passe par une lambda
        resourcesActivityButton.setOnClickListener((view)-> showResourcesActivity());

        // on créer un listener directement dans la méthode en appelant le constructeur
        interactionsActivityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showInteractionsActivity();
            }
        });

        //On créé d'abord le listener puis on l'affecte au button qui convient,
        //cela peut être utile si on souhaite que plusieurs bouton fasse la même chose.
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showSenderActivity();
            }
        };
        senderActivityButton.setOnClickListener(listener);

        lifeCycleActivityButton.setOnClickListener((view)-> showLifeCycleActivity());
        userActivityButton.setOnClickListener((view)-> showUserActivity());
        menuActivityButton.setOnClickListener((view)-> showMenuActivity());

    }

    private void showMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
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