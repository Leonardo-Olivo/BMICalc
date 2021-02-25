package ch.olivo.leonardo.bmicalc.service;

import java.util.Arrays;

import ch.olivo.leonardo.bmicalc.enums.BMIClasses;

public class BMIService {
    public BMIService() {
    }

    public double calculateBMI(double weight, double height) {
        return weight / (Math.pow(height, 2));
    }

    public BMIClasses findBMIClass(double bmi) {
        return Arrays.stream(BMIClasses.values())
                .filter(c -> c.getUpperLimit() > bmi && c.getLowerLimit() < bmi).findFirst()
                .orElse(null);
    }
}
