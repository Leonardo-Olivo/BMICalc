package ch.olivo.leonardo.bmicalc.enums;

public enum BMIClass {
    UNDERWEIGHT("Underweight", 0, 18.5),
    NORMAL_WEIGHT("Normal weight", 18.5, 24.9),
    PRE_OBESITY("Pre Obesity", 24.9, 29.9),
    OBESITY_ONE("Obesity Class 1", 29.9, 34.9),
    OBESITY_TWO("Obesity Class 2", 34.9, 39.9),
    OBESITY_THREE("Obesity Class 3", 39.9, 1000);

    BMIClass(String displayName, double lowerLimit, double upperLimit) {
        this.displayName = displayName;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    private final String displayName;
    private final double upperLimit;
    private final double lowerLimit;
}
