import java.util.ArrayList;
import java.util.Scanner;

public class Library implements LibraryInfo {

    final private String userId = "Admin";
    final private String password = "Admin";

    static int lastStudnetId = 105;
    static int lastBookId = 205;

    static ArrayList<Student> allStudents = new ArrayList<Student>();
    static ArrayList<Book> allBooks = new ArrayList<Book>();
    static Scanner sc = new Scanner(System.in);

    // validate Admin method
    public boolean validateUser(String username, String password) {
        return (this.userId.equals(username) && this.password.equals(password));
    }

    // validate Student method
    public Student validateStudent(int id, String password) {
        for (Student s : allStudents) {
            System.out.println(s.studentId);
            if (s.studentId == id) {
                if (s.getpassword().equals(password)) {
                    return s;
                }
            }
        }
        return null;
    }

    public void populateDatabase() {
        allStudents.add(new Student("Ajith", 101, "123456"));
        allStudents.add(new Student("Priya", 102, "123456"));
        allStudents.add(new Student("Kanna", 103, "123456"));
        allStudents.add(new Student("vihal", 104, "123456"));

        allBooks.add(new Book(201, "Twilight"));
        allBooks.add(new Book(202, "Eclipse"));
        allBooks.add(new Book(203, "Atonement"));
        allBooks.add(new Book(204, "Birdsong"));
    }

    /**
     * @param s_name Name of studnet t be added
     * @param s_pass New password of student
     */
    public void addStudentToLibrary(String s_name, String s_pass) {
        int s_id = lastStudnetId++;
        allStudents.add(new Student(s_name, s_id, s_pass));
        System.out.println("Studnet added sucessfully!");
        System.out.println("Studnet userId is: " + s_id);
    }

    /**
     * @param b_name Book name that to be added
     */
    public void addBooktoLibrary(String b_name) {
        int b_id = lastBookId++;
        allBooks.add(new Book(b_id, b_name));
        System.out.println("Book added sucessfully!");
        System.out.println("Book ID is: " + b_id);
    }

    /**
     * @param s_id Student ID that to be removed
     */
    public void removeStudentFromLibrary(int s_id) {
        for (Student s : allStudents) {
            if (s.studentId == s_id) {
                allStudents.remove(s);
                System.out.println("Studnet removed sucessfully!");
                return;
            }
        }
        System.out.println("No student found in the ID :( " + s_id);
    }

    /**
     * @param s_id studnet id
     * @return studnet obj if s_id is valid or returns null
     */
    public Student isValidStudents(int s_id) {
        for (Student s : allStudents) {
            if (s.studentId == s_id) {
                return s;
            }
        }
        return null;
    }

    /**
     * @param b_id book ID to return
     * @return Book obj if b_id is valid or return null
     */
    public Book isValidBook(int b_id) {
        for (Book b : allBooks) {
            if (b.bookId == b_id) {
                return b;
            }
        }
        return null;
    }

    // Shows all books from database
    public void showallBooks() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Total Books : " + allBooks.size());
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%5s %15s %15s ", "B ID", "Name", "Available");
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        for (Book b : allBooks) {
            System.out.printf("%5s %15s %15s", b.bookId, b.bookName, b.available);
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------");
    }

    // This is an helper method to clear the console screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
