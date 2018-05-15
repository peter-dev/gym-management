/** Subclass of Member. Stores studentId and collegeName. */
public class StudentMember extends Member {

  private String studentId;
  private String collegeName;

  /**
   * Class Constructor. It initialises email, name, address, gender, height, starting weight, chosen
   * package, student id and college name.
   *
   * @param email
   * @param name
   * @param address
   * @param gender
   * @param height
   * @param startWeight
   * @param chosenPackage
   * @param studentId
   * @param collegeName
   */
  public StudentMember(
      String email,
      String name,
      String address,
      String gender,
      float height,
      float startWeight,
      String chosenPackage,
      String studentId,
      String collegeName) {
    super(email, name, address, gender, height, startWeight, chosenPackage);
    this.studentId = studentId;
    this.collegeName = collegeName;
    // chosenPackage(chosenPackage);
  }

  /**
   * Returns college name associated with a student member.
   *
   * @return college name
   */
  public String getCollegeName() {
    return collegeName;
  }

  /**
   * Returns student id that identifies the student.
   *
   * @return student id
   */
  public String getStudentId() {
    return studentId;
  }

  /**
   * The chosenPackage is set to the package associated with their collegeName. If there is no
   * package associated with their college, default to “Package 3”.
   *
   * @param packageChoice
   */
  @Override
  public void chosenPackage(String packageChoice) {
    this.chosenPackage = packageChoice;
  }

  @Override
  public String toString() {
    return super.toString()
        + "\nCollege Name:     "
        + collegeName
        + "\nStudent id:       "
        + studentId
        + "\nAssessments:      "
        + getAssessments().size();
  }
}
