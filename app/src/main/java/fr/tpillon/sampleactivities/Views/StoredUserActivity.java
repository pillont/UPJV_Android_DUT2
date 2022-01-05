package fr.tpillon.sampleactivities.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fr.tpillon.sampleactivities.Logic.Dao.SampleDbHelper;
import fr.tpillon.sampleactivities.Logic.Dao.UserDao;
import fr.tpillon.sampleactivities.Logic.Services.UserService;
import fr.tpillon.sampleactivities.Models.Entities.UserEntity;
import fr.tpillon.sampleactivities.Models.Exceptions.AgeException;
import fr.tpillon.sampleactivities.Models.Exceptions.NameMissingException;
import fr.tpillon.sampleactivities.R;

public class StoredUserActivity extends AppCompatActivity {
    private UserService userService;
    private UserEntity lastUser;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText ageEditText;

    private TextView ageErrorTextView;
    private TextView nameErrorTextView;
    private Button storeUserButton;
    private Button cleanUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_user);

        userService = new UserService(this);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        storeUserButton = findViewById(R.id.storeUserButton);
        cleanUserButton = findViewById(R.id.cleanUserButton);
        ageErrorTextView = findViewById(R.id.ageErrorTextView);
        nameErrorTextView = findViewById(R.id.nameErrorTextView);

        storeUserButton.setOnClickListener(view -> saveUser());
        cleanUserButton.setOnClickListener(view -> removeUser());

        displayUser();
    }

    @Override
    protected void onDestroy() {
        userService.close();

        super.onDestroy();
    }

    private void saveUser() {
        UserEntity user = getUser();
        upsertUser(user);
    }

    private void upsertUser(UserEntity user) {
        ageErrorTextView.setVisibility(View.INVISIBLE);
        nameErrorTextView.setVisibility(View.INVISIBLE);

        try {
            tryUpsertUser(user);

            Toast.makeText(
                    this,
                    R.string.user_stored_message,
                    Toast.LENGTH_SHORT
            ).show();
        }
        catch(NameMissingException ex) {
            nameErrorTextView.setVisibility(View.VISIBLE);
        } catch (AgeException ex) {
            ageErrorTextView.setVisibility(View.VISIBLE);
        }
    }

    private UserEntity getUser() {
        UserEntity user = new UserEntity();

        user.firstName = firstNameEditText.getText().toString();
        user.lastName = lastNameEditText.getText().toString();

        String ageStr= ageEditText.getText().toString();
        user.age = Integer.parseInt(ageStr);

        return user;
    }

    private void tryUpsertUser(UserEntity user)
        throws AgeException, NameMissingException {

        boolean isCreation = lastUser == null;
        if (isCreation) {
            lastUser = userService.create(user);
            return;
        }

        user.id = lastUser.id;
        userService.update(user);
    }

    private void removeUser() {
        userService.remove(lastUser);
        displayUser();

        Toast.makeText(this, R.string.user_removed_message,Toast.LENGTH_SHORT)
                .show();
    }

    private void displayUser() {
        lastUser = userService.lastOrNull();

        UserEntity toDisplay = lastUser;
        if(toDisplay == null){
            // CAS : l utilisateur n est pas sauvegardé ou supprimé
            toDisplay = new UserEntity();
        }

        // CAS : l utilisateur est sauvegardé
        firstNameEditText.setText(toDisplay.firstName);
        lastNameEditText.setText(toDisplay.lastName);

        String ageAsString = Integer.toString(toDisplay.age);
        ageEditText.setText(ageAsString);
    }
}