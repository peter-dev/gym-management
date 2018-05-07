import java.util.Scanner;

public class MenuController {

    private Scanner input = new Scanner(System.in);
    private GymAPI api;

    public static void main(String[] args) {

        MenuController menuController = new MenuController();

    }

    public MenuController() {
        api = new GymAPI();

        // On app start-up, automatically load the gym data (trainers and members) from an XML file
        try {
            api.load();
        } catch (Exception e) {
            System.out.println("Error reading from file: " + e);
        }
        // display start-up menu
        // http://chronicles.blog.ryanrampersad.com/2011/03/text-based-menu-in-java/
        // nested submenu example
        displayMainMenu();

    }


    private void displayMainMenu() {

        while (true) {

            System.out.println("======================================================================================");
            System.out.println("  ____                   __  __                                                   _   ");
            System.out.println(" / ___|_   _ _ __ ___   |  \\/  | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_ ");
            System.out.println("| |  _| | | | '_ ` _ \\  | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|");
            System.out.println("| |_| | |_| | | | | | | | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_ ");
            System.out.println(" \\____|\\__, |_| |_| |_| |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__|");
            System.out.println("       |___/                                      |___/                               ");
            System.out.println("================================================== Author: Piotr Baran ===============");
            System.out.println("======================================================================================");
            System.out.println(" \nOptions:");
            System.out.println("       l) Login");
            System.out.println("       r) Register");
            System.out.println("       0) Exit");
            //System.out.println("--------------------------------------------------------------------------------------");
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

        userTypeMenu: while (true) {
            System.out.println("\n======================================================================================");
            System.out.println("MEMBERSHIP TYPE");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" Options:");
            System.out.println("       m) Member");
            System.out.println("       t) Trainer");
            System.out.println("       0) Go Back");
            //System.out.println("--------------------------------------------------------------------------------------");
            System.out.print(" ==>>  ");
            char option = input.next().trim().charAt(0);
            input.nextLine();

            switch (option) {
                case 'm':
                    if (displayLoginMenu("MEMBER", formType)) {
                        displayMemberMenu();
                        break;
                    } else {
                        System.out.println("Access Denied");
                        exitApp();
                        break;
                    }

                case 't':
                    if (displayLoginMenu("TRAINER", formType)) {
                        displayTrainerMenu();
                        break;
                    } else {
                        System.out.println("Access Denied");
                        exitApp();
                        break;
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

    private boolean displayLoginMenu(String userType, String formType) {

        System.out.println("\n======================================================================================");
        System.out.println(userType + " " + formType + " FORM");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(" Details:");
        System.out.println("       Enter your email address:");
        System.out.print(" ==>>  ");
        String userName = input.nextLine().trim();
        System.out.println("       Enter your password:");
        System.out.print(" ==>>  ");
        String userPass = input.nextLine().trim();
        //System.out.println("--------------------------------------------------------------------------------------");


        // trainer / member menu if login / register success return boolean success
        // if register fails please try again
        // if login fails return false
        return true;
    }

    private void displayTrainerMenu() {

        trainerMenu: while (true) {
            System.out.println("\n======================================================================================");
            System.out.println("TRAINER MENU");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" Options:");
            System.out.println("       1) Add New Member");
            System.out.println("       2) List All Members");
            System.out.println("       3) Search for a Member by Email");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" Assessment Sub-Menu");
            System.out.println("       4) Add an Assessment for a Member");
            System.out.println("       5) Update Comment on an Assessment for a Member");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("       0) Exit");
            System.out.println("--------------------------------------------------------------------------------------");
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

        memberMenu: while (true) {
            System.out.println("\n======================================================================================");
            System.out.println("MEMBER MENU");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" Options:");
            System.out.println("       1) View your profile");
            System.out.println("       2) Update your profile");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" Progress Sub-Menu");
            System.out.println("       3) View progress by weight");
            System.out.println("       4) View progress by waist measurement");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("       0) Exit");
            System.out.println("--------------------------------------------------------------------------------------");
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


    private void exitApp() {
        System.out.println("Exiting...");

        // On app exit, automatically save the gym data (trainers and members) to an XML file.
        try {
            api.store();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e);
        }

        System.exit(0);
    }




}
