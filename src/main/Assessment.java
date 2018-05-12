/**
 * It stores weight, thigh, waist, comment and a Trainer that entered the member’s assessment (i.e.
 * personal trainer).
 */
public class Assessment {

  private float weight;
  private float thigh;
  private float waist;
  private String comment;
  private String trainer;

  /**
   * Class Constructor. It initialises weight, thigh, waist, comment and trainer that entered the
   * member’s assessment.
   *
   * @param weight
   * @param thigh
   * @param waist
   * @param comment
   * @param trainer
   */
  public Assessment(float weight, float thigh, float waist, String comment, String trainer) {
    this.weight = weight;
    this.thigh = thigh;
    this.waist = waist;
    this.comment = comment;
    this.trainer = trainer;
  }

  /**
   * Class Constructor. Required to satisfy JUnit Test. Trainer parameter not required.
   *
   * @param weight
   * @param thigh
   * @param waist
   * @param comment
   */
  public Assessment(float weight, float thigh, float waist, String comment) {
    this.weight = weight;
    this.thigh = thigh;
    this.waist = waist;
    this.comment = comment;
  }

  /**
   * Returns member's weight in kgs
   *
   * @return member's weight in kgs
   */
  public float getWeight() {
    return weight;
  }

  /**
   * Returns member's thigh in inches
   *
   * @return member's thigh in inches
   */
  public float getThigh() {
    return thigh;
  }

  /**
   * Returns member's waist in inches
   *
   * @return member's waist in inches
   */
  public float getWaist() {
    return waist;
  }

  /**
   * Returns comment entered by the trainer
   *
   * @return comment entered by the trainer
   */
  public String getComment() {
    return comment;
  }

  /**
   * Updates current weight, no validation required.
   *
   * @param weight
   */
  public void setWeight(float weight) {
    this.weight = weight;
  }

  /**
   * Updates current thigh, no validation required.
   *
   * @param thigh
   */
  public void setThigh(float thigh) {
    this.thigh = thigh;
  }

  /**
   * Updates current waist, no validation required.
   *
   * @param waist
   */
  public void setWaist(float waist) {
    this.waist = waist;
  }

  /**
   * Updates assessment's comment, no validation required.
   *
   * @param comment
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * Returns trainer that entered the member’s assessment (i.e. personal trainer)
   *
   * @return trainer's speciality
   */
  public String getTrainer() {
    return trainer;
  }

  /**
   * Updates trainer that entered the member’s assessment
   *
   * @param trainer
   */
  public void setTrainer(String trainer) {
    this.trainer = trainer;
  }
}
