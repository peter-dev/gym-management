
// THIS CODE IS INCOMPLETE

public class Member extends Person {
    private float height;
    private float startWeight;
    private String chosenPackage;

    public Member(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(startWeight);
        setChosenPackage(chosenPackage);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = 0 ;
    }

    public float getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(float startWeight) {
        this.startWeight = 0 ;
    }

    public String getChosenPackage() {
        return chosenPackage;
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

}

