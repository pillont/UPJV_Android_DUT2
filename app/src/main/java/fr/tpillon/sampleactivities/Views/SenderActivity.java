package fr.tpillon.sampleactivities.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import fr.tpillon.sampleactivities.R;

public class SenderActivity extends AppCompatActivity {

    private EditText messageEditText;
    private EditText numberEditText;
    private SwitchCompat flagSwitch;
    private Button senderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);

        messageEditText = findViewById(R.id.messageEditText);
        numberEditText = findViewById(R.id.numberEditText);
        flagSwitch = findViewById(R.id.flagSwitch);

        senderButton = findViewById(R.id.senderButton);
        senderButton.setOnClickListener(view-> startNewActivity());
    }

    private void startNewActivity() {
        // HERE : récupération du text dans la zone de texte
        Editable messageEditable = messageEditText.getText();
        String message = messageEditable.toString();

        // HERE : récupération du nombre dans la zone de saisie de nombre
        double number;
        try {
            Editable numberEditable = numberEditText.getText();
            number = Double.parseDouble(numberEditable.toString());
        } catch (NumberFormatException ex) {
            // potentiellement pas de valeur,
            // on envoie 0
            number = 0;
        }

        // HERE : récupération de l etat du switch
        boolean flag = flagSwitch.isChecked();

        Intent intent = new Intent(this, ReceiverActivity.class);
        intent.putExtra(ReceiverActivity.MESSAGE, message);
        intent.putExtra(ReceiverActivity.NUMBER, number);
        intent.putExtra(ReceiverActivity.FLAG, flag);

        startActivity(intent);
    }
}