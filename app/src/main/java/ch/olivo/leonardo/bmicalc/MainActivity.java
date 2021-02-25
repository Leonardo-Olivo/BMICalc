package ch.olivo.leonardo.bmicalc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculate = findViewById(R.id.calculate);
        EditText weightText = findViewById(R.id.weight);
        EditText heightText = findViewById(R.id.height);
        Intent intent = new Intent(this, ResultActivity.class);

        calculate.setOnClickListener(v -> {
            double weight = getValues(weightText);
            double height = getValues(heightText);

            intent.putExtra("weight", weight);
            intent.putExtra("height", height);
            startActivity(intent);
        });
    }

    private double getValues(EditText editText) {
        Editable editable = editText.getText();

        if (editable.toString().equals("")) {
            return 0;
        }

        return Double.parseDouble(editable.toString());
    }
}