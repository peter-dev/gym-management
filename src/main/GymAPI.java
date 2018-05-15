import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class operates between the model classes and the menu driver class. It stores: an ArrayList
 * of Members, an ArrayList of Trainers.
 */
public class GymAPI {
  private ArrayList<Member> members;
  private ArrayList<Trainer> trainers;

  /** Class constructor. It initializes an empty list of members and trainers. */
  public GymAPI() {
    this.members = new ArrayList<Member>();
    this.trainers = new ArrayList<Trainer>();
  }

  /**
   * Returns a list containing all the members in the gym. Returns an empty list if none are found.
   *
   * @return members list, an empty list if there are no members in the gym
   */
  public ArrayList<Member> getMembers() {
    return members;
  }

  /**
   * Returns a list containing all the trainers in the gym. Returns an empty list if none are found.
   *
   * @return trainers list, an empty list if there are no members in the gym
   */
  public ArrayList<Trainer> getTrainers() {
    return trainers;
  }

  /**
   * Add new member to the gym. The member contains the following information: email, name, address,
   * gender, height, startWeight, chosenPackage
   *
   * @param member representation of the member
   */
  public void addMember(Member member) {
    if (member != null) {
      members.add(member);
    }
  }

  /**
   * Add new trainer to the gym. The trainer contains the following information: email, name,
   * address, gender, speciality
   *
   * @param trainer representation of the trainer
   */
  public void addTrainer(Trainer trainer) {
    if (trainer != null) {
      trainers.add(trainer);
    }
  }

  /**
   * Returns a total number of registered gym members.
   *
   * @return total number of members, 0 if there are no members in the gym
   */
  public int numberOfMembers() {
    return members.size();
  }

  /**
   * Returns a total number of registered gym trainers.
   *
   * @return total number of trainers, 0 if there are no trainers in the gym
   */
  public int numberOfTrainerss() {
    return trainers.size();
  }

  /**
   * Returns a boolean indicating if the index passed as a parameter is a valid index for the
   * member’s array list.
   *
   * @param index indicates a position in the member’s array list
   * @return true if member found, false if outside the list boundary
   */
  public boolean isValidMemberIndex(int index) {
    return (index < 0 || (index >= members.size())) ? false : true;
  }

  /**
   * Returns a boolean indicating if the index passed as a parameter is a valid index for the
   * trainer’s array list.
   *
   * @param index indicates a position in the trainer’s array list
   * @return true if member found, false if outside the list boundary
   */
  public boolean isValidTrainerIndex(int index) {
    return (index < 0 || (index >= trainers.size())) ? false : true;
  }

  /**
   * Returns the member object that matches the email entered. If no member matches, return null.
   *
   * @param emailEntered email address to be used for searching the list of members
   * @return member object, null if not match
   */
  public Member searchMembersByEmail(String emailEntered) {
    for (Member m : members) {
      if (emailEntered.toLowerCase().equals(m.getEmail().toLowerCase())) return m;
    }
    return null;
  }

  /**
   * Returns the trainer object that matches the email entered. If no member matches, return null.
   *
   * @param emailEntered email address to be used for searching the list of trainers
   * @return trainer object, null if not match
   */
  public Trainer searchTrainersByEmail(String emailEntered) {
    for (Trainer t : trainers) {
      if (emailEntered.toLowerCase().equals(t.getEmail().toLowerCase())) return t;
    }
    return null;
  }

  /**
   * Returns a list of member names that partially or entirely matches the entered name. An empty
   * array is returned when their are no matches.
   *
   * @param nameEntered user name to be used for searching the list of members
   * @return list of names, an empty list if there are no matches
   */
  public ArrayList<String> searchMembersByName(String nameEntered) {
    // check if name is null or empty spaces only
    if (nameEntered == null || nameEntered.trim().length() == 0) {
      return new ArrayList<>();
    }
    // split name at spaces (based on Lab 12)
    nameEntered = nameEntered.trim().toLowerCase();
    String[] wordArray = nameEntered.split(" ");
    HashSet<String> words = new HashSet<>();
    for (String word : wordArray) {
      words.add(word);
    }
    // create a list of names that partially or entirely matches the entered name
    ArrayList<String> membersList = new ArrayList<>();
    for (Member m : members) {
      for (String s : words) {
        if (m.getName().toLowerCase().contains(s)) {
          membersList.add(m.getName());
          break;
        }
      }
    }
    return membersList;
  }

  /**
   * Returns a list of trainer names that partially or entirely matches the entered name. An empty
   * array is returned when their are no matches.
   *
   * @param nameEntered user name to be used for searching the list of trainers
   * @return list of names, an empty list if there are no matches
   */
  public ArrayList<String> searchTrainersByName(String nameEntered) {
    // check if name is null or empty spaces only
    if (nameEntered == null || nameEntered.trim().length() == 0) {
      return new ArrayList<>();
    }
    // split name at spaces (based on Lab 12)
    nameEntered = nameEntered.trim().toLowerCase();
    String[] wordArray = nameEntered.split(" ");
    HashSet<String> words = new HashSet<>();
    for (String word : wordArray) {
      words.add(word);
    }
    // create a list of names that partially or entirely matches the entered name
    ArrayList<String> membersList = new ArrayList<>();
    for (Trainer m : trainers) {
      for (String s : words) {
        if (m.getName().toLowerCase().contains(s)) {
          membersList.add(m.getName());
          break;
        }
      }
    }
    return membersList;
  }

  /**
   * Returns a list containing all the members details in the gym whose latest assessment weight is
   * an ideal weight (based on the devine method). Returns an empty list if none are found. This
   * method depends on 'sortedAssessmentDates' (Member) and 'isIdealBodyWeight' (GymUtility)
   *
   * @return list of members with ideal weight, null if none are found
   */
  public ArrayList<Member> listMembersWithIdealWeight() {
    ArrayList<Member> idealWeightMembers = new ArrayList<>();
    for (Member m : members) {
      if (m.getAssessments().isEmpty()) continue;
      boolean isIdealWeight = GymUtility.isIdealBodyWeight(m, m.latestAssessment());
      if (isIdealWeight) {
        idealWeightMembers.add(m);
      }
    }
    return idealWeightMembers;
  }

  /**
   * Returns an array list containing all the members whose BMI category (based on the latest
   * assessment weight) partially or entirely matches the entered category.
   *
   * @param category entered category to be used for defining the BMI category
   * @return list of members whose BMI category partially or entirely matches the entered category,
   *     empty list if none are found
   */
  public ArrayList<Member> listMembersBySpecificBMICategory(String category) {
    // check if category is null or empty spaces only
    if (category == null || category.trim().length() == 0) {
      return new ArrayList<>();
    }
    // split name at spaces (based on Lab 12)
    category = category.trim().toLowerCase();
    String[] wordArray = category.split(" ");
    HashSet<String> words = new HashSet<>();
    for (String word : wordArray) {
      words.add(word);
    }
    // create a list of Members whose BMI category partially or entirely matches the entered
    // category
    ArrayList<Member> searchedCategoryMembers = new ArrayList<>();

    for (Member m : members) {
      if (m.getAssessments().isEmpty()) continue;
      double bmiValue = GymUtility.calculateBMI(m, m.latestAssessment());
      String bmiCategory = GymUtility.determineBMICategory(bmiValue);
      for (String s : words) {
        if (bmiCategory.toLowerCase().contains(s)) {
          searchedCategoryMembers.add(m);
          break;
        }
      }
    }
    return searchedCategoryMembers;
  }

  /**
   * List, for each member, their latest assessment weight and their height both imperially and
   * metrically.
   *
   * @return user details only if there are members in the gym, if there are no members in the gym,
   *     the message "No registered members" should be returned
   */
  public String listMemberDetailsImperialAndMetric() {
    if (members == null || members.isEmpty()) {
      return "No registered members";
    }
    String memberDetails = "";
    for (Member m : members) {
      if (m.getAssessments().isEmpty()) continue;
      memberDetails +=
          m.getName()
              + ": "
              + Math.round(m.latestAssessment().getWeight())
              + " kg ("
              + Math.round(GymUtility.kgToPoundConversion(m.latestAssessment().getWeight()))
              + " lbs) "
              + GymUtility.roundFloatTo1Decimal(m.getHeight())
              + " metres ("
              + Math.round(GymUtility.metreToInchConversion(m.getHeight()))
              + " inches).\n";
    }
    return memberDetails;
  }

  /**
   * Push the members and trainers array lists out to the associated XML file
   *
   * @throws Exception if fails to save the data to file
   */
  public void store() throws Exception {

    ArrayList<Person> gymMembers = new ArrayList<>();
    /*gymMembers.add(
        new Trainer(
            "bob.harper@trainer.com",
            "Bob Harper",
            "Nashville, Tennessee, U.S.",
            "M",
            "Personal Trainer"));
    gymMembers.add(
        new Trainer(
            "lou.ferrigno@trainer.com",
            "Lou Ferrigno",
            "Brooklyn, New York, U.S.",
            "M",
            "Fitness Trainer"));

    Member member1 =
        new StudentMember(
            "piotr.baran@student.com",
            "Piotr Baran",
            "Cork, Ireland",
            "M",
            2f,
            80f,
            "Package 3",
            "MIT00001",
            "MIT");
    Assessment assessment1 =
        new Assessment(82f, 0f, 0f, "Well done.", "Bob Harper, Personal Trainer");
    member1.getAssessments().put("18/04/25", assessment1);

    Member member2 =
        new StudentMember(
            "tara.baran@student.com",
            "Tara Baran",
            "Cork, Ireland",
            "F",
            1.6f,
            50f,
            "WIT",
            "WIT00001",
            "WIT");
    Assessment assessment2 =
        new Assessment(
            48f, 0f, 0f, "Well done. You are getting there.", "Lou Ferrigno, Fitness Trainer");
    Assessment assessment2_1 =
        new Assessment(
            47f, 0f, 0f, "Well done. You achieved your goal.", "Lou Ferrigno, Fitness Trainer");
    member2.getAssessments().put("18/04/10", assessment2);
    member2.getAssessments().put("18/05/05", assessment2_1);

    Member member3 =
        new PremiumMember(
            "peter.mccarthy@premium.com",
            "Peter McCarthy",
            "Waterford, Ireland",
            "M",
            1.8f,
            75f,
            "Package 3");

    gymMembers.add(member1);
    gymMembers.add(member2);
    gymMembers.add(member3);*/

    gymMembers.addAll(members);
    gymMembers.addAll(trainers);

    XStream xstream = new XStream(new StaxDriver());
    ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("gym-members.xml"));
    out.writeObject(gymMembers);
    out.close();
  }

  /**
   * Pull the members and trainers array lists from the associated XML file.
   *
   * @throws Exception if fails to load the data from file
   */
  public void load() throws Exception {

    Class<?>[] classes =
        new Class[] {
          Member.class, StudentMember.class, PremiumMember.class, Trainer.class, Assessment.class
        };
    XStream xstream = new XStream(new StaxDriver());
    XStream.setupDefaultSecurity(xstream);
    xstream.allowTypes(classes);
    ObjectInputStream in = xstream.createObjectInputStream(new FileReader("gym-members.xml"));

    ArrayList<Person> allDataFromFile = (ArrayList<Person>) in.readObject();

    in.close();

    /*
     * I wanted to experiment with Java 8 functionality, therefore I am using Java Streams
     * to filter the Collection. The code is based on the following example:
     * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
     */

    allDataFromFile.stream().filter(s -> s instanceof Member).forEach(s -> members.add((Member) s));
    allDataFromFile
        .stream()
        .filter(s -> s instanceof Trainer)
        .forEach(s -> trainers.add((Trainer) s));
  }
}
