public class PremiumMember extends Member  {

    public PremiumMember(String email, String name, String address, String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender, height, startWeight, chosenPackage);
    }

    @Override
    public void chosenPackage(String packageChoice) {
        // TODO


        // The chosenPackage is set to the value passed as a parameter. There is no validation on the entered data
    }
}
