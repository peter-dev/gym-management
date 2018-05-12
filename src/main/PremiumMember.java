/** Subclass of Member. Stores no additional data. */
public class PremiumMember extends Member {

  /**
   * Class constructor. It initialises email, name, address, gender, height, starting weight and
   * chosen package.
   *
   * @param email
   * @param name
   * @param address
   * @param gender
   * @param height
   * @param startWeight
   * @param chosenPackage
   */
  public PremiumMember(
      String email,
      String name,
      String address,
      String gender,
      float height,
      float startWeight,
      String chosenPackage) {
    super(email, name, address, gender, height, startWeight, chosenPackage);
    // chosenPackage(chosenPackage);
  }

  /**
   * The chosenPackage is set to the value passed as a parameter. There is no validation on the
   * entered data.
   *
   * @param packageChoice
   */
  @Override
  public void chosenPackage(String packageChoice) {
    this.chosenPackage = packageChoice;
  }
}
