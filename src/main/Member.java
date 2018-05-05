
// THIS CODE IS INCOMPLETE

import java.util.HashMap;
import java.util.SortedSet;

public class Member extends Person {

    private float height;
    private float startWeight;
    private String chosenPackage;
    private HashMap<String, Assessment> assessments;

    public Member(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(startWeight);
        setChosenPackage(chosenPackage);
        assessments = new HashMap<>();
    }

    public void setHeight(float height) {
        if ( height >= 1.0f && height <= 3.0f) {
            this.height = height;
        } else {
            this.height = 0;
        }
    }

    public void setStartWeight(float startWeight) {
        if (startWeight >= 35.0f && startWeight <= 250.0f) {
            this.startWeight = startWeight;
        } else {
            this.startWeight = 0;
        }
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    public float getHeight() {
        return height;
    }

    public float getStartWeight() {
        return startWeight;
    }

    public String getChosenPackage() {
        return chosenPackage;
    }

    public Assessment latestAssessment() {
        // TODO
        return null;
    }

    public SortedSet sortedAssessmentDates() {
        // TODO
        return null;
    }

    public void chosenPackage(String chosenPackage) {
        // TODO ABSTRACT
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nHeight: " + height +
                "\nStart Weight: " + startWeight +
                "\nChosen Package: " + chosenPackage;
    }
}

