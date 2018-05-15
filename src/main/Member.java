import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Subclass of Person. Stores a person’s height, starting weight, chosenPackage and a hashmap to
 * record all the member's progress i.e. assessments performed by trainers.
 */
public class Member extends Person {

  private float height;
  private float startWeight;
  protected String chosenPackage; // can be accessed from sub classes

  private HashMap<String, Assessment> assessments;

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
  public Member(
      String email,
      String name,
      String address,
      String gender,
      float height,
      float startWeight,
      String chosenPackage) {
    super(email, name, address, gender);
    setHeight(height);
    setStartWeight(startWeight);
    chosenPackage(chosenPackage);
    assessments = new HashMap<>();
  }

  /**
   * Height is measured in metres and must be between 1 and 3 inclusive. Any height outside the
   * range will be defaulted to 1.0 meter
   *
   * @param height
   */
  public void setHeight(float height) {
    if (height >= 1.0f && height <= 3.0f) {
      this.height = height;
    } else {
      this.height = 1.0f;
    }
  }

  /**
   * Starting Weight is measured in kgs and must be between 35 and 250. Any weight outside the range
   * will be defaulted to 35.0 kgs
   *
   * @param startWeight
   */
  public void setStartWeight(float startWeight) {
    if (startWeight >= 35.0f && startWeight <= 250.0f) {
      this.startWeight = startWeight;
    } else {
      this.startWeight = 35.0f;
    }
  }

  /**
   * Returns height provided during a registration process.
   *
   * @return height is measured in metres
   */
  public float getHeight() {
    return height;
  }

  /**
   * Returns starting weight provided during a registration process.
   *
   * @return start weight measured in kgs
   */
  public float getStartWeight() {
    return startWeight;
  }

  /**
   * Returns chosen package, it will be different for PremiumMember and StudentMember.
   *
   * @return chosen package associated with a member of the gym
   */
  public String getChosenPackage() {
    return chosenPackage;
  }

  /**
   * Returns assessments performed by trainers.
   *
   * @return the map key will be the date (as string), the map values will be an assessment details
   */
  public HashMap<String, Assessment> getAssessments() {
    return assessments;
  }

  /**
   * Returns the latest assessment based on last entry (by calendar date). This method depends on
   * 'sortedAssessmentDates'
   *
   * @return latest assessment, null if no assessment found
   */
  public Assessment latestAssessment() {
    /* Previous implementation based on the following example:
     * https://stackoverflow.com/questions/3527216/accessing-the-last-entry-in-a-map
     *
     * NavigableMap<String, Assessment> map = new TreeMap<>(new DateComparator());
     * map.putAll(assessments);
     * Assessment lastEntry = map.lastEntry().getValue();
     */

    SortedSet sortedDates = sortedAssessmentDates();
    if (sortedDates.isEmpty()) {
      return null;
    }
    String lastDate = sortedDates.last().toString();
    Assessment lastEntry = assessments.get(lastDate);

    return lastEntry;
  }

  /**
   * Returns the assessments dates (as strings) sorted in date order. It uses a custom
   * DateComparator class
   *
   * @return dates (as strings) sorted in date order (yy/mm/dd)
   */
  public SortedSet sortedAssessmentDates() {
    SortedSet<String> orderedDates = new TreeSet<>(new DateComparator());
    orderedDates.addAll(assessments.keySet());

    return orderedDates;
  }

  /*
   * The below method is not meant to be an abstract method - mistake in the specs.
   * The concrete implementation of this method will be completed in Member subclasses.
   * @param packageChoice
   */
  public void chosenPackage(String packageChoice) {}

  @Override
  public String toString() {
    return super.toString()
        + "\nHeight:           "
        + height
        + " m"
        + "\nStart Weight:     "
        + startWeight
        + " kgs"
        + "\nChosen Package:   "
        + chosenPackage;
  }
}
