package ch.olivo.leonardo.bmicalc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ch.olivo.leonardo.bmicalc.enums.BMIClasses;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent incomingIntent = getIntent();
        double result = incomingIntent.getDoubleExtra("result", 0);
        result = Math.round(result * 100.0) / 100.0;

        BMIClasses bmiClass = (BMIClasses) incomingIntent.getSerializableExtra("BMIClass");

        TextView resultView = findViewById(R.id.resultText);
        resultView.setText(String.valueOf(result));

        TextView classView = findViewById(R.id.resultClass);
        classView.setText(bmiClass.getDisplayName());
    }
}