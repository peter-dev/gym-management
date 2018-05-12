/**
 * This class stores email, name, address and gender. The email is used to uniquely identify a
 * person in the system.
 */
public class Person {
  private String name;
  private String email;
  private String address;
  private String gender;

  /**
   * Class constructor. It initialises email, name, address and gender.
   *
   * @param email
   * @param name
   * @param address
   * @param gender
   */
  public Person(String email, String name, String address, String gender) {
    this.email = email;
    setName(name);
    this.address = address;
    setGender(gender);
  }

  /**
   * The name is maximum 30 characters; any name entered should be truncated to 30 characters.
   *
   * @param name
   */
  public void setName(String name) {
    int maxLength = 30;
    if (name != null && name.length() > maxLength) {
      this.name = name.substring(0, maxLength);
    } else {
      this.name = name;
    }
  }

  /**
   * The address field has no requirement for validation.
   *
   * @param address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * The gender can be either “M” or “F”. If it is not either of these then apply a default value of
   * “Unspecified”.
   *
   * @param gender
   */
  public void setGender(String gender) {
    String defaultGender = "Unspecified";
    switch (gender.toUpperCase()) {
      case "M":
      case "F":
        this.gender = gender.toUpperCase();
        break;
      default:
        this.gender = defaultGender;
        break;
    }
  }

  /**
   * Returns a user name provided during the registration process.
   *
   * @return name must be maximum 30 characters
   */
  public String getName() {
    return name;
  }

  /**
   * Returns an email that uniquely identifies a person in the system.
   *
   * @return email that identifies a person
   */
  public String getEmail() {
    return email;
  }

  /**
   * Returns an address that is associated with a person
   *
   * @return address where person is living
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns gender of a person, it can be 'M', 'F', or 'Unspecified'
   *
   * @return gender of a registered person
   */
  public String getGender() {
    return gender;
  }

  @Override
  public String toString() {
    return "\nName:             "
        + name
        + "\nEmail:            "
        + email
        + "\nAddress:          "
        + address
        + "\nGender:           "
        + gender;
  }
}
