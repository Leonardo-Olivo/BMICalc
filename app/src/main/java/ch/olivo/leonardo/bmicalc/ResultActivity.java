package ch.olivo.leonardo.bmicalc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ch.olivo.leonardo.bmicalc.enums.BMIClass;
import ch.olivo.leonardo.bmicalc.service.BMIService;

public class ResultActivity extends AppCompatActivity {

    private BMIService bmiService;
    private boolean isBound = false;
    private final ServiceConnection boundServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BMIService.BMIServiceBinder binderBridge = (BMIService.BMIServiceBinder) service;
            bmiService = binderBridge.getService();
            isBound = true;
            Intent incomingIntent = getIntent();
            double weight = incomingIntent.getDoubleExtra("weight", 0);
            double height = incomingIntent.getDoubleExtra("height", 0);
            double result = bmiService.calculateBMI(weight, height);

            BMIClass bmiClass = bmiService.findBMIClass(result);

            TextView resultView = findViewById(R.id.resultText);
            resultView.setText(String.valueOf(result));

            TextView classView = findViewById(R.id.resultClass);

            if (bmiClass == null) {
                classView.setText(R.string.Error);
            } else {
                classView.setText(bmiClass.getDisplayName());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            bmiService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BMIService.class);
        startService(intent);
        bindService(intent, boundServiceConnection, Context.BIND_AUTO_CREATE);
    }
}