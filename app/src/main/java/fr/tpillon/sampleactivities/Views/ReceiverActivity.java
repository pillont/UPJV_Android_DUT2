package fr.tpillon.sampleactivities.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fr.tpillon.sampleactivities.R;

public class ReceiverActivity extends AppCompatActivity {

    private TextView messageTextView;
    private TextView numberTextView;
    private TextView flagTextView;

    public static final String MESSAGE = "MESSAGE_KEY";
    public static final String NUMBER = "NUMBER_KEY";
    public static final String FLAG = "FLAG_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        Intent values = getIntent();
        String message = values.getStringExtra(MESSAGE);
        double number = values.getDoubleExtra(NUMBER, 0);
        boolean flag = values.getBooleanExtra(FLAG, false);


        messageTextView = findViewById(R.id.messageTextView);
        numberTextView = findViewById(R.id.numberTextView);
        flagTextView = findViewById(R.id.flagTextView);

        String numberAsString = Double.toString(number);
        String flagAsString = Boolean.toString(flag);

        this.messageTextView.setText(message);
        this.numberTextView.setText(numberAsString);
        this.flagTextView.setText(flagAsString);
    }
}