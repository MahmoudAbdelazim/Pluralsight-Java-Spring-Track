package patientintake;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BMICalculator {
    public static double calculateBmi(Integer height, Integer weight) {
        double bmi = (double) (weight * 703) / (height * height);
        return BigDecimal.valueOf(bmi)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
