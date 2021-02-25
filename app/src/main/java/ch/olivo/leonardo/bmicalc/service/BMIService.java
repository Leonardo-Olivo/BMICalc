package ch.olivo.leonardo.bmicalc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Arrays;

import ch.olivo.leonardo.bmicalc.enums.BMIClass;

public class BMIService extends Service {
    public static final String SEED_KEY = "SEED_KEY";

    public BMIService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new BMIServiceBinder();
    }

    public double calculateBMI(double weight, double height) {
        double result = weight / (Math.pow(height, 2));
        return Math.round(result * 100.0) / 100.0;
    }

    public BMIClass findBMIClass(double bmi) {
        return Arrays.stream(BMIClass.values())
                .filter(c -> c.getUpperLimit() > bmi && c.getLowerLimit() < bmi).findFirst()
                .orElse(null);
    }

    public class BMIServiceBinder extends Binder {
        public BMIServiceBinder() {
        }

        public BMIService getService() {
            return BMIService.this;
        }
    }
}