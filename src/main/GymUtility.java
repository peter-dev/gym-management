import java.math.BigDecimal;
import java.text.DecimalFormat;

public class GymUtility {

    // predefined list of constants used to determine BMI category
    // https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
    public enum BmiLevel {
        L1(16d, "SEVERELY UNDERWEIGHT"), L2(18.5d, "UNDERWEIGHT"), L3(25d, "NORMAL"), L4(30d, "OVERWEIGHT"), L5(35d, "MODERATELY OBESE"), L6(35d, "SEVERELY OBESE");

        private final double bmiRange;
        private final String bmiDescription;

        BmiLevel(double bmiRange, String bmiDescription) {
            this.bmiRange = bmiRange;
            this.bmiDescription = bmiDescription;
        }

        double getBmiRange() { return bmiRange; }
        String getBmiDescription() { return bmiDescription; }
    }



    public static double calculateBMI(Member member, Assessment assessment) {
        float weight = assessment.getWeight();
        float height = member.getHeight();
        double bmi = (double) weight / Math.pow(height, 2);
        return bmi;
    }

    public static String determineBMICategory(double bmiValue) {

        if (bmiValue < BmiLevel.valueOf("L1").getBmiRange()) {
            return BmiLevel.valueOf("L1").getBmiDescription();
        } else if (bmiValue < BmiLevel.valueOf("L2").getBmiRange()) {
            return BmiLevel.valueOf("L2").getBmiDescription();
        } else if (bmiValue < BmiLevel.valueOf("L3").getBmiRange()) {
            return BmiLevel.valueOf("L3").getBmiDescription();
        } else if (bmiValue < BmiLevel.valueOf("L4").getBmiRange()) {
            return BmiLevel.valueOf("L4").getBmiDescription();
        } else if (bmiValue < BmiLevel.valueOf("L5").getBmiRange()) {
            return BmiLevel.valueOf("L5").getBmiDescription();
        } else {
            return BmiLevel.valueOf("L6").getBmiDescription();
        }

    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {

        // member weight and height
        float weightInKg = assessment.getWeight();
        float heightInInch = metreToInchConversion(member.getHeight());
        String gender = member.getGender();


        // formula values
        float baseHeightInInch = 60f;   // 5 feet x 12 = 60 inches
        float baseWeightMale = 50f;
        float baseWeightFemale = 45.5f;
        float incrementalWeight = 2.3f;

        // if member is 5 feet or less return 50kg for male and 45.5kg for female
        if (heightInInch <= baseHeightInInch) {
            return (gender.equals("M")) ? (weightInKg == baseWeightMale) : (weightInKg == baseWeightFemale);
        }

        // assign correct base weight
        float baseWeight = (gender.equals("M")) ? baseWeightMale : baseWeightFemale;

        // add 2.3kg for each inch over 5 feet (60 inches)
        float calculatedIdeal = baseWeight + (incrementalWeight * (heightInInch - baseHeightInInch));
        System.out.println("Expected: " + weightInKg + " Result: " + calculatedIdeal);
        System.out.println("Expected: " + roundFloatTo2Decimal(weightInKg)  + " Result: " + roundFloatTo2Decimal(calculatedIdeal));

        return (roundFloatTo2Decimal(weightInKg) == roundFloatTo2Decimal(calculatedIdeal));
    }


    public static float metreToInchConversion(float lengthMeters) {
        // 1 metre = 39.3701 inch
        return (lengthMeters * 39.3701f);
    }

    public static float roundFloatTo2Decimal(float number) {
        // round up float number to 2 decimal places
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(number));
    }


}
