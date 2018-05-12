import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

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
  }

  /**
   * Class constructor. It creates a new GymAPI object and on app start-up, automatically load the
   * gym data (trainers and members) from an XML file.
   */
  public MenuController() {
    api = new GymAPI();
    packages = new HashMap<>();

    // savePackages();
    loadPackages();

    try {
      api.load();
    } catch (Exception e) {
      System.out.println("Error reading gym members from file: " + e);
      exitApp();
    }

    // display start-up menu
    // displayMainMenu();
  }

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
      System.out.println(" \nOptions:");
      System.out.println("       l) Login");
      System.out.println("       r) Register");
      System.out.println("       0) Exit");
      // System.out.println("--------------------------------------------------------------------------------------");
      System.out.print(" ==>>  ");
      char option = input.next().trim().charAt(0);
      input.nextLine();

      switch (option) {
        case 'l':
        case 'L':
          displayUserTypeMenu("LOGIN");
          break;
        case 'r':
        case 'R':
          displayUserTypeMenu("REGISTER");
          break;
        case '0':
          exitApp();
          break;
        default:
          System.out.println("Invalid option entered: " + option);
          break;
      }
    }
  }

  private void displayUserTypeMenu(String formType) {

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
      // System.out.println("--------------------------------------------------------------------------------------");
      System.out.print(" ==>>  ");
      char option = input.next().trim().charAt(0);
      input.nextLine();

      switch (option) {
        case 'm':
          if (formType.equals("LOGIN")) {
            // action login menu
            if (displayLoginMenu("MEMBER")) {
              displayMemberMenu();
              break;
            } else {
              System.out.println("Access Denied");
              exitApp();
              break;
            }
          } else if (formType.equals("REGISTER")) {
            // action register menu
          }
        case 't':
          if (formType.equals("LOGIN")) {
            // action login menu
            if (displayLoginMenu("TRAINER")) {
              displayTrainerMenu();
              break;
            } else {
              System.out.println("Access Denied");
              exitApp();
              break;
            }
          } else if (formType.equals("REGISTER")) {
            // action register menu
          }
        case '0':
          System.out.println("Returning... ");
          break userTypeMenu;
        default:
          System.out.println("Invalid option entered: " + option);
          break;
      }
    }
  }

  private boolean displayLoginMenu(String userType) {

    System.out.println(
        "\n======================================================================================");
    System.out.println(userType + " LOGIN FORM");
    System.out.println(
        "--------------------------------------------------------------------------------------");
    System.out.println(" Details:");
    System.out.println("       Enter your email address:");
    System.out.print(" ==>>  ");
    String userName = input.nextLine().trim();

    if (userName.equals(GymUtility.UserType.MEMBER)) {
      for (Member member : api.getMembers()) {
        if (member.getEmail().toLowerCase().equals(userName.toLowerCase())) {
          return true;
        }
      }
    } else if (userName.equals(GymUtility.UserType.TRAINER)) {
      for (Trainer trainer : api.getTrainers()) {
        if (trainer.getEmail().toLowerCase().equals(userName.toLowerCase())) {
          return true;
        }
      }
    }
    // email address not found
    return false;
  }

  private void displayTrainerMenu() {

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
      System.out.println(" Assessment Sub-Menu");
      System.out.println("       4) Add an Assessment for a Member");
      System.out.println("       5) Update Comment on an Assessment for a Member");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.println("       0) Exit");
      System.out.println(
          "--------------------------------------------------------------------------------------");
      System.out.print(" ==>>  ");
      char option = input.next().trim().charAt(0);
      input.nextLine();

      switch (option) {
        case '0':
          exitApp();
          break;
        default:
          System.out.println("Invalid option entered: " + option);
          break;
      }
    }
  }

  private void displayMemberMenu() {

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
      char option = input.next().trim().charAt(0);
      input.nextLine();

      switch (option) {
        case '0':
          exitApp();
          break;
        default:
          System.out.println("Invalid option entered: " + option);
          break;
      }
    }
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
}
