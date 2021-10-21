import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    // Admin and Student menu template
    static String adminTemplate = "\n\nchoose from the following options\n\n" + "1:Add Student\n" + "2:Remove Student\n"
            + "3:Add Book\n" + "4:view Report\n" + "5:logout\n" + "6:exit\n";
    static String studnetTemplate = "\n\nchoose from the following options\n\n" + "1:Pay fine\n" + "2:Borrow book\n"
            + "3:Return Book\n" + "4:view my report\n" + "5:logout\n" + "6:Exit\n";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Driver driver = new Driver();
        // Creating object for admin class
        Admin admin = new Admin();

        // Creating object for Library class
        Library library = new Library();
        library.populateDatabase();

        // initial keep authenticated status to false
        boolean authenticated = false;
        /**
         * Check authentication This loop executes untill the admin or student is
         * getting authenticated
         */
        do {
            System.out.println("---------------Welcome to Library Management System---------------");
            System.out.println("Select from the following options");
            System.out.println("1.Admin login");
            System.out.println("2.Studnet login");
            System.out.println("3.Exit");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {

                    // Athenticating Admin if Users choice is 1
                    System.out.println("ADMIN LOGIN : ");
                    System.out.println("Enter your UserID: ");
                    String userId = sc.nextLine();
                    System.out.println("Enter your Password: ");
                    String password = sc.nextLine();

                    // Validating user with their USERID and PASSWORD
                    if (library.validateUser(userId, password)) {
                        clearScreen();
                        System.out.println("Welcome Admin!");
                        System.out.println();

                        // Transfering the user to Admin interface if authenicated
                        driver.adminIo(admin);
                    } else {
                        clearScreen();
                        System.out.println("Authnetication failed please try again :( ");
                        System.out.println();
                    }
                } else if (choice == 2) {

                    // Authenticating Studnet if choice is 2
                    System.out.println("STUDENT LOGIN : ");
                    System.out.println("Enter your UserID: ");
                    int userId = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter your Password: ");
                    String password = sc.nextLine();
                    Student student = library.validateStudent(userId, password);
                    if (student != null) {
                        clearScreen();
                        System.out.println("Welcome Student!");
                        System.out.println();

                        // Transferring to Studnet interface if authenticated
                        driver.userIo(student);
                    } else {
                        // clearScreen();
                        System.out.println("Authentication failed please try again :( ");
                        System.out.println();
                    }
                } else {
                    System.out.println("----------------------------------");
                    System.out.println("------Thankyou for using SMS------");
                    System.out.println("----------------------------------");
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid Input!");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid Input!");

            }
        } while (!authenticated);

        sc.close();
    }

    // Student Interface.
    void userIo(Student studnet) {
        boolean exit = false;
        do {
            System.out.println();
            System.out.println("-------------STUDENT MENU-------------");
            System.out.printf(studnetTemplate);
            System.out.println("--------------------------------------");
            System.out.println();
            int choice;

            try {
                // Switch case according to user input
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        studnet.payFine();
                        break;

                    case 2:
                        studnet.borrowBook();
                        break;

                    case 3:
                        studnet.returnBook();
                        break;

                    case 4:
                        studnet.viewReport();
                        break;

                    case 5: {
                        System.out.println("Studnet signed out successfully!");
                        exit = true;
                        break;

                    }
                    case 6: {
                        // Exit from System
                        clearScreen();
                        System.out.println("----------------------------------");
                        System.out.println("------Thankyou for using LMS------");
                        System.out.println("----------------------------------");
                        exit = true;
                    }
                        break;
                    default:
                        System.out.println("Please enter a valid input!");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid Input!");
                sc.next();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid Input!");
                sc.next();
            }

        } while (!exit);
    }

    // Admin interface
    void adminIo(Admin admin) {
        boolean exit = false;
        do {
            System.out.println();
            System.out.println("-------------ADMIN MENU-------------");
            System.out.printf(adminTemplate);
            System.out.println("------------------------------------");
            System.out.println();
            int choice;
            try {
                // Switch case according to user input
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        admin.addStudent();
                        break;

                    case 2:
                        admin.removeStudent();
                        break;

                    case 3:
                        admin.addBook();
                        break;

                    case 4:
                        admin.generateReport();
                        break;

                    case 5: {
                        clearScreen();
                        System.out.println("------Admin loged out sucessfully!------");
                        exit = true;
                        break;
                    }
                    case 6: {
                        // Exit from System
                        System.out.println("----------------------------------");
                        System.out.println("------Thankyou for using LMS------");
                        System.out.println("----------------------------------");
                        System.exit(0);
                        break;

                    }
                    default:
                        System.out.println("Please enter a valid input!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid Input!");
                sc.next();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid Input!");
                sc.next();
            }
        } while (!exit);
    }

    // Helper method to clearscreen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
