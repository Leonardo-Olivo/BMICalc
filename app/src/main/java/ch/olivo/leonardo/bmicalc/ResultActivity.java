package ch.olivo.leonardo.bmicalc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ch.olivo.leonardo.bmicalc.enums.BMIClasses;
import ch.olivo.leonardo.bmicalc.service.BMIService;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        BMIService bmiService = new BMIService();


        Intent incomingIntent = getIntent();
        double weight = incomingIntent.getDoubleExtra("weight", 0);
        double height = incomingIntent.getDoubleExtra("height", 0);
        double result = bmiService.calculateBMI(weight, height);
        result = Math.round(result * 100.0) / 100.0;

        BMIClasses bmiClass = bmiService.findBMIClass(result);

        TextView resultView = findViewById(R.id.resultText);
        resultView.setText(String.valueOf(result));

        TextView classView = findViewById(R.id.resultClass);

        if (bmiClass == null) {
            classView.setText(R.string.Error);
        } else {
            classView.setText(bmiClass.getDisplayName());
        }
    }
}