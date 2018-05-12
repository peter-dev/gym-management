/** Subclass of Person. Stores the trainer’s specialty. */
public class Trainer extends Person {

  private String speciality;

  /**
   * Class Constructor. It initialises email, name, address, gender and trainer's speciality.
   *
   * @param email
   * @param name
   * @param address
   * @param gender
   * @param speciality
   */
  public Trainer(String email, String name, String address, String gender, String speciality) {
    super(email, name, address, gender);
    setSpeciality(speciality);
  }

  /**
   * Returns trainer's speciality (i.e. personal trainer)
   *
   * @return trainer's speciality
   */
  public String getSpeciality() {
    return speciality;
  }

  /**
   * Update trainer's speciality
   *
   * @param speciality
   */
  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }

  @Override
  public String toString() {
    return super.toString() + "\nSpeciality:       " + speciality;
  }
}
