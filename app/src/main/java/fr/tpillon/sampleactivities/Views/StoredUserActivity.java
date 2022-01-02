package fr.tpillon.sampleactivities.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.tpillon.sampleactivities.Logic.Dao.SampleDbHelper;
import fr.tpillon.sampleactivities.Logic.Dao.UserDao;
import fr.tpillon.sampleactivities.Logic.Services.UserService;
import fr.tpillon.sampleactivities.Models.Entities.UserEntity;
import fr.tpillon.sampleactivities.R;

public class StoredUserActivity extends AppCompatActivity {
    private UserService userService;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText ageEditText;
    private UserEntity lastUser;
    private Button storeUserButton;
    private Button cleanUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_user);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        storeUserButton = findViewById(R.id.storeUserButton);
        cleanUserButton = findViewById(R.id.cleanUserButton);

        storeUserButton.setOnClickListener(view -> saveUser());
        cleanUserButton.setOnClickListener(view -> removeUser());

        userService = new UserService(this);
        UserEntity user = userService.lastOrNull();
        displayUser(user);
    }

    @Override
    protected void onDestroy() {
        userService.close();

        super.onDestroy();
    }

    private void saveUser() {
        UserEntity user = getUser();
        upsertUser(user);

        Toast.makeText(this, R.string.user_stored_message,Toast.LENGTH_SHORT)
                .show();
    }

    private void upsertUser(UserEntity user) {
        boolean isCreation = lastUser == null;
        if(isCreation) {
            lastUser = userService.create(user);
            return;
        }

        user.id = lastUser.id;
        userService.update(user);
    }

    private void removeUser() {
        userService.remove(lastUser);
        displayUser(null);

        Toast.makeText(this, R.string.user_removed_message,Toast.LENGTH_SHORT)
                .show();

    }

    private UserEntity getUser() {
        UserEntity user = new UserEntity();

        user.firstName = firstNameEditText.getText().toString();
        user.lastName = lastNameEditText.getText().toString();

        String ageStr= ageEditText.getText().toString();
        user.age = Integer.parseInt(ageStr);

        return user;
    }

    private void displayUser(UserEntity user) {
        // CAS : l utilisateur est sauvegardé
        UserEntity toDisplay = user;

        if(user == null){
            // CAS : l utilisateur n est pas sauvegardé ou supprimé
            toDisplay = new UserEntity();
        }

        firstNameEditText.setText(toDisplay.firstName);
        lastNameEditText.setText(toDisplay.lastName);

        String ageAsString = Integer.toString(toDisplay.age);
        ageEditText.setText(ageAsString);

        lastUser = user;
    }
}