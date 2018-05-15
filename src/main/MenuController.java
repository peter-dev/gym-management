import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;

/** Types of login action that can be performed. */
enum LoginType {
  /** Member user */
  LOGIN,
  /** Trainer user */
  REGISTER
}

/** Types of gym users that can be used. */
enum UserType {
  /** Member user */
  MEMBER,
  /** Trainer user */
  TRAINER
}

/**
 * The menu driver class (MenuController) uses the console I/O to interact with the user. It should
 * create an instance of the GymApi class and allow the user to navigate (a subset of) the system's
 * features through a series of menus.
 *
 * @author Piotr Baran
 */
public class MenuController {

  private Scanner input = new Scanner(System.in);
  private GymAPI api;
  private HashMap<String, String> packages;

  public static void main(String[] args) {

    MenuController menuController = new MenuController();

    // menuController.savePackages();
    menuController.loadPackages();
    try {
      menuController.api.load();
    } catch (Exception e) {
      System.out.println("Error reading gym members from file: " + e);
      menuController.exitApp();
    }

    // display start-up menu
    menuController.displayMainMenu();
  }

  /**
   * Class constructor. It creates a new GymAPI object and on app start-up, automatically load the
   * gym data (trainers and members) from an XML file.
   */
  public MenuController() {
    api = new GymAPI();
    packages = new HashMap<>();
  }

  /** Ask the user do they want to login(l) or register(r). Allow user to exit the app (0). */
  private void displayMainMenu() {

    while (true) {

      System.out.println(
          "======================================================================================");
      System.out.println(
          "  ____                   __  __                                                   _   ");
      System.out.println(
          " / ___|_   _ _ __ ___   |  \\/  | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_ ");
      System.out.println(
          "| |  _| | | | '_ ` _ \\  | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|");
      System.out.println(
          "| |_| | |_| | | | | | | | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_ ");
      System.out.println(
          " \\____|\\__, |_| |_| |_| |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__|");
      System.out.println(
          "       |___/                                      |___/                               ");
      System.out.println(
          "================================================== Author: Piotr Baran ===============");
      System.out.println(
          "======================================================================================");
      System.out.println("\n Options:");
      System.out.println("       l) Login");
      System.out.println("       r) Register");
      System.out.println("       0) Exit");
      System.out.print(" ==>>  ");
      char option = input.next().trim().toLowerCase().charAt(0);

      switch (option) {
        case 'l':
          displayUserTypeMenu(LoginType.LOGIN);
          break;
        case 'r':
          displayUserTypeMenu(LoginType.REGISTER);
          break;
        case '0':
          exitApp();
          break;
        default:
          System.out.println(
              "Invalid option entered: ["
                  + option
                  + "]. Please choose one of the following options: [l] [r]");
          break;
      }
    }
  }

  /**
   * Ask the user if they are a member(m) or a trainer(t). Display login / register menu based on
   * user selection. Allow user to go back to previous menu (0).
   *
   * @param formType defines type of the form to be displayed LOGIN / REGISTER
   */
  private void displayUserTypeMenu(LoginType formType) {

    userTypeMenu:
    while (true) {
      System.out.println(
          "\n======================================================================================");
      System.out.println("MEMBERSHIP TYPE");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.println(" Options:");
      System.out.println("       m) Member");
      System.out.println("       t) Trainer");
      System.out.println("       0) Go Back");
      System.out.print(" ==>>  ");
      char option = input.next().trim().toLowerCase().charAt(0);

      switch (option) {
        case 'm':
          if (formType == LoginType.LOGIN) {
            // action member login menu
            String loggedInEmail = displayLoginScreen(UserType.MEMBER);
            if (loggedInEmail != null) {
              displayMemberMenu(api.searchMembersByEmail(loggedInEmail));
              break;
            } else {
              System.out.println("Access Denied");
              exitApp();
              break;
            }
          } else if (formType == LoginType.REGISTER) {
            // action member registration menu
            String registeredEmail = displayRegistrationScreen(UserType.MEMBER);
            if (registeredEmail != null) {
              displayMemberMenu(api.searchMembersByEmail(registeredEmail));
              break;
            } else {
              System.out.println("Failed to register new member. Please try again at later time.");
              exitApp();
              break;
            }
          }
        case 't':
          if (formType == LoginType.LOGIN) {
            // action trainer login menu
            String loggedInEmail = displayLoginScreen(UserType.TRAINER);
            if (loggedInEmail != null) {
              displayTrainerMenu(api.searchTrainersByEmail(loggedInEmail));
              break;
            } else {
              System.out.println("Access Denied");
              exitApp();
              break;
            }
          } else if (formType == LoginType.REGISTER) {
            // action trainer registration menu
            String registeredEmail = displayRegistrationScreen(UserType.TRAINER);
            if (registeredEmail != null) {
              displayTrainerMenu(api.searchTrainersByEmail(registeredEmail));
              break;
            } else {
              System.out.println("Failed to register new member. Please try again at later time.");
              exitApp();
              break;
            }
          }
        case '0':
          System.out.println("Returning... ");
          break userTypeMenu;
        default:
          System.out.println(
              "Invalid option entered: ["
                  + option
                  + "]. Please choose one of the following options: [m] [t]");
          break;
      }
    }
  }

  /**
   * If the user selected to login, verify that the email entered is stored in the appropriate array
   * list i.e. the members or trainers list. If the email does not exist, print out “access denied”
   * to the console and exit the program.
   *
   * @param userType defines type of the user required for validation MEMBER / TRAINER
   * @return email address if logged in successfully, null if email doesn't exist
   */
  private String displayLoginScreen(UserType userType) {

    System.out.println(
        "\n======================================================================================");
    System.out.println(userType + " LOGIN FORM");
    System.out.println(
        "--------------------------------------------------------------------------------------");
    input.nextLine(); // dummy read of String to clear buffer - bug in Scanner class
    String enteredEmail = editEmail();

    if (userType == UserType.MEMBER) {
      for (Member member : api.getMembers()) {
        if (member.getEmail().trim().toLowerCase().equals(enteredEmail)) {
          return enteredEmail;
        }
      }
    } else if (userType == UserType.TRAINER) {
      for (Trainer trainer : api.getTrainers()) {
        if (trainer.getEmail().trim().toLowerCase().equals(enteredEmail)) {
          return enteredEmail;
        }
      }
    }
    // email address not found
    return null;
  }

  /**
   * If the user entered an email that is already used in the system (for either trainers/members),
   * let them know it is an invalid email and ask them to enter a new one.
   *
   * @param userType defines type of the user required for validation MEMBER / TRAINER
   * @return newly registered email address in the system
   */
  private String displayRegistrationScreen(UserType userType) {

    System.out.println(
        "\n======================================================================================");
    System.out.println(userType + " REGISTRATION FORM");
    System.out.println(
        "--------------------------------------------------------------------------------------");
    input.nextLine(); // dummy read of String to clear buffer - bug in Scanner class

    ArrayList<Person> membersAndTrainers = new ArrayList<>();
    membersAndTrainers.addAll(api.getMembers());
    membersAndTrainers.addAll(api.getTrainers());

    boolean isUniqueEmail;
    String enteredEmail;

    do {
      isUniqueEmail = true;
      enteredEmail = editEmail();

      for (Person p : membersAndTrainers) {
        if (p.getEmail().trim().toLowerCase().equals(enteredEmail)) {
          System.out.println("Invalid email address. Please enter a new email address. ");
          // break for loop and start again
          isUniqueEmail = false;
          break;
        }
      }
    } while (isUniqueEmail == false);

    // unique email address entered, exit while loop and continue registration process
    if (userType == UserType.MEMBER) {
      char selectedMembershipType = editMembership();
      input.nextLine(); // dummy read of String to clear buffer - bug in Scanner class
      // student member
      if (selectedMembershipType == 's') {
        String enteredName = editName();
        String enteredAddress = editAddress();
        String enteredGender = editGender();
        float enteredHeight = editHeight();
        float enteredWeight = editWeight();
        input.nextLine(); // dummy read of String to clear buffer - bug in Scanner class
        String enteredStudentId = editStudentId();
        String enteredCollege = editCollege();
        String chosenPackage = identifyPackage(enteredCollege);
        api.addMember(
            new StudentMember(
                enteredEmail,
                enteredName,
                enteredAddress,
                enteredGender,
                enteredHeight,
                enteredWeight,
                chosenPackage,
                enteredStudentId,
                enteredCollege));
        return enteredEmail;
      }
      // premium member
      if (selectedMembershipType == 'p') {
        String enteredName = editName();
        String enteredAddress = editAddress();
        String enteredGender = editGender();
        float enteredHeight = editHeight();
        float enteredWeight = editWeight();
        input.nextLine(); // dummy read of String to clear buffer - bug in Scanner class
        String chosenPackage = editPackage();
        api.addMember(
            new PremiumMember(
                enteredEmail,
                enteredName,
                enteredAddress,
                enteredGender,
                enteredHeight,
                enteredWeight,
                chosenPackage));
        return enteredEmail;
      }

    } else if (userType == UserType.TRAINER) {
      String enteredName = editName();
      String enteredAddress = editAddress();
      String enteredSpeciality = editSpeciality();
      String enteredGender = editGender();
      api.addTrainer(
          new Trainer(enteredEmail, enteredName, enteredAddress, enteredGender, enteredSpeciality));
      return enteredEmail;
    }

    // registration not successful
    return null;
  }

  /**
   * Allow gym members to display their profile, edit personal details and show progress by weight
   * and by waist measurement.
   *
   * @param member currently logged in user
   */
  private void displayMemberMenu(Member member) {

    memberMenu:
    while (true) {
      System.out.println(
          "\n======================================================================================");
      System.out.println("MEMBER MENU");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.println(" Options:");
      System.out.println("       1) View your profile");
      System.out.println("       2) Update your profile");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.println(" Progress Sub-Menu");
      System.out.println("       3) View progress by weight");
      System.out.println("       4) View progress by waist measurement");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.println("       0) Exit");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.print(" ==>>  ");
      char option = input.next().trim().toLowerCase().charAt(0);
      input.nextLine(); // dummy read of String to clear buffer - bug in Scanner class

      switch (option) {
        case '1':
          System.out.println(member.toString());
          System.out.println("Press Enter key to continue...");
          input.nextLine();
          break;
        case '2':
          member.setName(editName());
          member.setAddress(editAddress());
          member.setHeight(editHeight());
          System.out.println("\nYour profile has been updated. Press Enter key to continue...");
          input.nextLine();
          input.nextLine();
          break;
        case '3':
          printAssessmentsBy("weight", member);
          break;
        case '4':
          printAssessmentsBy("waist", member);
          break;
        case '0':
          exitApp();
          break;
        default:
          System.out.println(
              "Invalid option entered: ["
                  + option
                  + "]. Please choose one of the following options: [1 ... 4]");
          break;
      }
    }
  }

  /**
   * Allow trainers to add, list, search for gym members. Allow trainers to add new Assessments to a
   * gym member or edit an existing assessment's comment.
   *
   * @param trainer currently logged in user
   */
  private void displayTrainerMenu(Trainer trainer) {

    trainerMenu:
    while (true) {
      System.out.println(
          "\n======================================================================================");
      System.out.println("TRAINER MENU");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.println(" Options:");
      System.out.println("       1) Add New Member");
      System.out.println("       2) List All Members");
      System.out.println("       3) Search for a Member by Email");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.println("       0) Exit");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.print(" ==>>  ");
      char option = input.next().trim().toLowerCase().charAt(0);

      switch (option) {
        case '1':
          // action member registration menu
          String registeredEmail = displayRegistrationScreen(UserType.MEMBER);
          if (registeredEmail != null) {
            System.out.println("\nSuccessfully added new member. Press Enter key to continue...");
          } else {
            System.out.println("\nFailed to register new member. Please try again at later time.");
          }
          input.nextLine();
          break;
        case '2':
          input.nextLine(); // dummy read of String to clear buffer - bug in Scanner class
          System.out.println("\nTotal:    " + api.numberOfMembers());
          int counter = 1;
          for (Member m : api.getMembers()) {
            System.out.println(
                "         [" + counter++ + "] " + m.getName() + " | " + m.getEmail());
          }
          System.out.println("Press Enter key to continue...");
          input.nextLine();
          break;
        case '3':
          input.nextLine(); // dummy read of String to clear buffer - bug in Scanner class
          Member m = api.searchMembersByEmail(editEmail());

          if (m != null) {
            System.out.println(m);
            System.out.println("Press Enter key to continue...");
            input.nextLine();
            // go to assessment menu
            displayAssessmentMenu(trainer, m);
          } else {
            System.out.println("\nMember not found in the system. Press Enter key to continue...");
            input.nextLine();
          }
          break;
        case '0':
          exitApp();
          break;
        default:
          System.out.println(
              "Invalid option entered: ["
                  + option
                  + "]. Please choose one of the following options: [1 ... 3]");
          break;
      }
    }
  }

  /**
   * Allow trainer to list Assessment that belong to a given Member, Trainer can add new Assessment
   * and update an existing Assessment for a Member
   *
   * @param trainer logged in trainer
   * @param member user returned during search
   */
  private void displayAssessmentMenu(Trainer trainer, Member member) {

    assessmentMenu:
    while (true) {
      System.out.println(
          "\n======================================================================================");
      System.out.println("ASSESSMENT MENU");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.println(" Options:");
      System.out.println("       1) List All Assessments");
      System.out.println("       2) Add an Assessment for a Member");
      System.out.println("       3) Update Comment on an Assessment for a Member");
      System.out.println("       0) Go Back");
      System.out.print(" ==>>  ");
      char option = input.next().trim().toLowerCase().charAt(0);
      input.nextLine(); // dummy read of String to clear buffer - bug in Scanner class

      switch (option) {
        case '1':
          printAssessmentsBy("all", member);
          break;
        case '2':
          float enteredWeight = editWeight();
          float enteredThigh = editThigh();
          float enteredWaist = editWaist();
          input.nextLine(); // dummy read of String to clear buffer - bug in Scanner class
          String enteredComment = editComment();
          String trainerDetails = trainer.getName() + ", " + trainer.getSpeciality();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
          String timeStamp = LocalDate.now().format(formatter);
          member
              .getAssessments()
              .put(
                  timeStamp,
                  new Assessment(
                      enteredWeight, enteredThigh, enteredWaist, enteredComment, trainerDetails));
          System.out.println("\nSuccessfully added new Assessment. Press Enter key to continue...");
          input.nextLine();
          break;
        case '3':
          printAssessmentsBy("comment", member);
          if (member.getAssessments().isEmpty()) {
            break;
          }
          String assessmentDate = editDate();
          if (member.getAssessments().containsKey(assessmentDate)) {
            String editedComment = editComment();
            member.getAssessments().get(assessmentDate).setComment(editedComment);
            System.out.println("\nSuccessfully updated Assessment. Press Enter key to continue...");
            input.nextLine();
          } else {
            System.out.println(
                "\nAssessment not found in the system. Please Enter key to continue...");
            input.nextLine();
          }
          break;
        case '0':
          System.out.println("Returning... ");
          break assessmentMenu;
        default:
          System.out.println(
              "Invalid option entered: ["
                  + option
                  + "]. Please choose one of the following options: [1 ... 3]");
          break;
      }
    }
  }

  /**
   * Lists assessments details for a given user by given criteria. If no assessments found, prints
   * "No registered assessments"
   *
   * @param printCriteria information to be printed (weight, waist or all) by entry date (ascending)
   * @param member member who's assessments are to be printed
   */
  private void printAssessmentsBy(String printCriteria, Member member) {
    SortedSet sortedDates = member.sortedAssessmentDates();
    if (!sortedDates.isEmpty()) {
      Iterator<String> it = sortedDates.iterator();
      while (it.hasNext()) {
        String key = it.next();
        System.out.print("\nAssessment Date:  " + key);
        if (printCriteria.equals("weight")) {
          System.out.print(" - Weight: " + member.getAssessments().get(key).getWeight() + " kgs");
        } else if (printCriteria.equals("waist")) {
          System.out.print(" - Waist: " + member.getAssessments().get(key).getWaist() + " inches");
        } else if (printCriteria.equals("comment")) {
          System.out.print(" - Comment: " + member.getAssessments().get(key).getComment());
        } else if (printCriteria.equals("all")) {
          System.out.println(member.getAssessments().get(key));
        }
      }
    } else {
      System.out.println("\nNo registered assessments");
    }
    System.out.println("\nPress Enter key to continue...");
    input.nextLine();
  }

  private char editMembership() {
    System.out.println(" Options:");
    System.out.println("       s) Student Member");
    System.out.println("       p) Premium Member");
    while (true) {
      System.out.print(" ==>>  ");
      char option = input.next().trim().toLowerCase().charAt(0);
      switch (option) {
        case 's':
        case 'p':
          return option;
        default:
          System.out.println(
              "Invalid option entered: ["
                  + option
                  + "]. Please choose one of the following options: [s] [p]");
          break;
      }
    }
  }

  private String editEmail() {
    System.out.println(" Details:");
    System.out.println("       Enter email address:");
    System.out.print(" ==>>  ");
    String enteredEmail = input.nextLine().trim().toLowerCase();
    return enteredEmail;
  }

  private String editName() {
    System.out.println(" Details:");
    System.out.println("       Enter full name:");
    System.out.print(" ==>>  ");
    String enteredName = input.nextLine().trim();
    if (enteredName == null || enteredName.isEmpty()) {
      return "n/a";
    }
    return enteredName;
  }

  private String editAddress() {
    System.out.println(" Details:");
    System.out.println("       Enter current address:");
    System.out.print(" ==>>  ");
    String enteredAddress = input.nextLine().trim();
    if (enteredAddress == null || enteredAddress.isEmpty()) {
      return "n/a";
    }
    return enteredAddress;
  }

  private String editGender() {
    System.out.println(" Options:");
    System.out.println("       m) Male");
    System.out.println("       f) Female");
    while (true) {
      System.out.print(" ==>>  ");
      char option = input.next().trim().toUpperCase().charAt(0);
      switch (option) {
        case 'M':
        case 'F':
          return String.valueOf(option);
        default:
          System.out.println(
              "Invalid option entered: ["
                  + option
                  + "]. Please choose one of the following options: [m] [f]");
          break;
      }
    }
  }

  private float editHeight() {
    System.out.println(" Details:");
    System.out.println("       Enter height in meters [min 1.0, max 3.0]:");
    System.out.print(" ==>>  ");

    if (input.hasNextFloat()) {
      return input.nextFloat();
    } else {
      input.next(); // clear buffer
      return 0f;
    }
  }

  private float editWeight() {
    System.out.println(" Details:");
    System.out.println("       Enter weight in kgs [min 35.0, max 250.0]:");
    System.out.print(" ==>>  ");

    if (input.hasNextFloat()) {
      return input.nextFloat();
    } else {
      input.next(); // clear buffer
      return 0f;
    }
  }

  private float editThigh() {
    System.out.println(" Details:");
    System.out.println("       Enter thigh in inches:");
    System.out.print(" ==>>  ");

    if (input.hasNextFloat()) {
      return input.nextFloat();
    } else {
      input.next(); // clear buffer
      return 0f;
    }
  }

  private float editWaist() {
    System.out.println(" Details:");
    System.out.println("       Enter waist in inches:");
    System.out.print(" ==>>  ");

    if (input.hasNextFloat()) {
      return input.nextFloat();
    } else {
      input.next(); // clear buffer
      return 0f;
    }
  }

  private String editStudentId() {
    System.out.println(" Details:");
    System.out.println("       Enter student id:");
    System.out.print(" ==>>  ");
    String enteredStudentId = input.nextLine().trim();
    if (enteredStudentId == null || enteredStudentId.isEmpty()) {
      return "n/a";
    }
    return enteredStudentId;
  }

  private String editCollege() {
    System.out.println(" Details:");
    System.out.println("       Enter college name:");
    System.out.print(" ==>>  ");
    String enteredCollege = input.nextLine().trim();
    if (enteredCollege == null || enteredCollege.isEmpty()) {
      return "n/a";
    }
    return enteredCollege;
  }

  private String editPackage() {
    System.out.println(" Available Packages:");
    for (String key : packages.keySet()) {
      System.out.println("       " + key);
    }
    System.out.println("       Enter package option:");
    System.out.print(" ==>>  ");
    String enteredPackage = input.nextLine().trim();
    if (enteredPackage == null || enteredPackage.isEmpty()) {
      return "n/a";
    }
    return enteredPackage;
  }

  private String editSpeciality() {
    System.out.println(" Details:");
    System.out.println("       Enter trainer's specialty:");
    System.out.print(" ==>>  ");
    String enteredSpeciality = input.nextLine().trim();
    if (enteredSpeciality == null || enteredSpeciality.isEmpty()) {
      return "n/a";
    }
    return enteredSpeciality;
  }

  private String editComment() {
    System.out.println(" Details:");
    System.out.println("       Enter comment for the Assessment:");
    System.out.print(" ==>>  ");
    String enteredComment = input.nextLine().trim();
    if (enteredComment == null || enteredComment.isEmpty()) {
      return "n/a";
    }
    return enteredComment;
  }

  private String editDate() {
    System.out.println(" Details:");
    System.out.println("       Enter Assessment's Date [yy/mm/dd]:");
    System.out.print(" ==>>  ");
    String enteredDate = input.nextLine().trim();
    if (enteredDate == null || enteredDate.isEmpty()) {
      return "n/a";
    }
    return enteredDate;
  }

  /**
   * Exit Application with code 0, On app exit, automatically save the gym data (trainers and
   * members) to an XML file.
   */
  private void exitApp() {
    System.out.println("Exiting...");

    try {
      api.store();
    } catch (Exception e) {
      System.out.println("Error writing gym members to file: " + e);
    }

    System.exit(0);
  }

  /** Pull the packages with key - values pairs from the associated XML file. */
  private void loadPackages() {

    try {
      Class<?>[] classes = new Class[] {String.class};
      XStream xstream = new XStream(new StaxDriver());
      XStream.setupDefaultSecurity(xstream);
      xstream.allowTypes(classes);
      ObjectInputStream in = xstream.createObjectInputStream(new FileReader("packages.xml"));
      packages = (HashMap<String, String>) in.readObject();
      in.close();
    } catch (Exception e) {
      System.out.println("Error reading packages from file: " + e);
    }
  }

  /** Push the packages with key - values pairs out to the associated XML file. */
  private void savePackages() {
    // predefined packages
    HashMap<String, String> packagesToFile = new HashMap<>();

    packagesToFile.put(
        "Package 1",
        "Allowed access anytime to gym.\nFree access to all classes.\nAccess to all changing areas including deluxe changing rooms.");
    packagesToFile.put(
        "Package 2",
        "Allowed access anytime to gym.\n€3 fee for all classes.\nAccess to all changing areas including deluxe changing rooms.");
    packagesToFile.put(
        "Package 3",
        "Allowed access to gym at off-peak times.\n€5 fee for all classes.\nNo access to deluxe changing rooms.");
    packagesToFile.put(
        "WIT",
        "Allowed access to gym during term time.\n€4 fee for all classes.\nNo access to deluxe changing rooms.");
    packagesToFile.put(
        "CIT",
        "Allowed access to gym during term time.\n€6 fee for all classes.\nNo access to deluxe changing rooms.");

    try {
      XStream xstream = new XStream(new StaxDriver());
      ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("packages.xml"));
      out.writeObject(packagesToFile);
      out.close();
    } catch (Exception e) {
      System.out.println("Error writing packages to file: " + e);
    }
  }

  /**
   * It identifies package associated with student's college name. If there is no package associated
   * with their college, default to “Package 3”.
   *
   * @param collegeName
   * @return package associated with a college name, default to "Package 3" if not found
   */
  private String identifyPackage(String collegeName) {

    if (packages.containsKey(collegeName.trim())) {
      return collegeName;
    }

    return packages.get("Package 3");
  }
}
