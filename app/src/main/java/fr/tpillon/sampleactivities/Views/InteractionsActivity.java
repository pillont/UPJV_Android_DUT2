package fr.tpillon.sampleactivities.Views;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fr.tpillon.sampleactivities.Models.Exceptions.EmptyTextException;
import fr.tpillon.sampleactivities.Logic.Services.TextService;
import fr.tpillon.sampleactivities.R;

public class InteractionsActivity extends AppCompatActivity {

    private final TextService textService= new TextService();

    private TextView savedTextView;
    private EditText editText;

    private Button submitButton;
    private Button defaultSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactions);


        editText = findViewById(R.id.editText);
        savedTextView = findViewById(R.id.savedTextView);

        defaultSaveButton = findViewById(R.id.defaultSaveButton);
        submitButton = findViewById(R.id.submitButton);

        defaultSaveButton.setOnClickListener(view-> saveDefaultText());
        submitButton.setOnClickListener(view-> saveText());
    }

    private void saveText() {
        String text = getTextToSave();


        try {
            String formattedString = textService.formatString(text);
            String textToDisplay = getString(R.string.saved_text_start, formattedString);
            savedTextView.setText(textToDisplay);

            changeColor(R.color.valid_color);
        } catch(EmptyTextException ex){

            Toast toast = Toast.makeText(this,
                R.string.empty_text_error_message,
                Toast.LENGTH_SHORT
            );

            toast.show();
        }
    }

    private void saveDefaultText() {
        savedTextView.setText(R.string.default_text);
        changeColor(R.color.neutral_color);
    }

    private void changeColor(@ColorRes int colorId){
        // on récupère la couleur des textes valides
        @ColorInt int color = getColor(colorId);

        // on change la couleur du texte
        savedTextView.setTextColor(color);
    }

    private String getTextToSave() {
        Editable editable = editText.getText();
        String text = editable.toString();
        editable.clear();

        return text;
    }
}