
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
// import java.lang.Math;

public class Student extends Library implements StudentServices {

    private String password;
    String studenttName;
    int studentId;
    ArrayList<Borrow> borrowlist = new ArrayList<Borrow>(2);
    int fine;

    // Studnet constructor
    Student(String name, int id, String password) {
        this.studenttName = name;
        this.studentId = id;
        setpassword(password);
    }

    // Getter for password
    public String getpassword() {
        return this.password;
    }

    // Setter for password
    public void setpassword(String password) {
        this.password = password;
    }

    // borrow book method
    public void borrowBook() {
        // returns If studnet have any pending fine and he/she can't borrow books
        if (this.fine != 0) {
            System.out.println("Please pay the pending fine amount to borrow new book!");
            System.out.println("Your pending fine amount is:" + this.fine);
            return;
        }
        showallBooks();
        // Getting BookId from user to return
        System.out.println("Enter Book ID to borrow");
        int b_id = Integer.parseInt(sc.nextLine());
        Book t_book = isValidBook(b_id);

        if (t_book != null) {
            // Checking if the book is availabe for borrow
            if (t_book.available == 0) {
                System.out.println("This book is not availabe right now to borrow");
            } else {

                // returns if user have borrowed more than 2 books
                if (this.borrowlist.size() == 2) {
                    System.out.println("You can't borrow more than 2 books!");
                    return;
                }

                // making the availabe status of book to 0
                t_book.available = 0;
                // changing the books borrowerID
                t_book.borrowerId = this.studentId;
                LocalDate today = LocalDate.now();
                LocalDate due = today.plusDays(10);

                // Creating a new borrow object and saving it to studnet's Borrow list
                Borrow borrow = new Borrow(t_book.bookId, today, due);
                this.borrowlist.add(borrow);
                t_book.nextAvailabel = due;
                System.out.println("Book " + t_book.bookName
                        + " has been borrowed sucessfully please return it on or before " + due);

            }
        } else {
            System.out.println("Invalid Book id");

        }

    }

    // Return book method
    public void returnBook() {

        // Returns if student have not borrowed any books
        if (this.borrowlist.size() == 0) {
            System.out.println("You don't have any books to return!");
            return;
        }
        System.out.print("Books to return :");
        for (Borrow borrow : borrowlist) {
            System.out.print(borrow.bookId + " |");
        }
        // Getting bookID that to be returned
        System.out.println();
        System.out.println("Enter Book ID to return:");
        int b_id = Integer.parseInt(sc.nextLine());
        for (Borrow borrow : borrowlist) {
            if (borrow.bookId == b_id) {

                // Finding if book is returned withing due date
                long t_date = borrow.dueDate.until(LocalDate.now(), ChronoUnit.DAYS);

                // Calculating fine as INR 10 for every day after due date.
                if (t_date >= 1) {
                    // this.fine = (int) Math.abs(t_date) * 10;
                    this.fine = (int) t_date * 10;
                    System.out.println("Your fine amount is:" + this.fine);
                }
                // Reverting the status of books once it is returned
                Book t_book = isValidBook(b_id);
                t_book.available = 1;
                t_book.borrowerId = 0;
                t_book.nextAvailabel = null;
                borrowlist.remove(borrow);
                System.out.println("Book has been returned sucessfully!");
                return;

            }
        }
        System.out.println("You havn't borrowed any book with such ID");
        return;

    }

    // This method generates report to Studnet
    public void viewReport() {
        System.out.println("---------------------------------------");
        System.out.println("           Studnet Report ");
        System.out.println("---------------------------------------");
        System.out.println("Studnet ID   : " + this.studentId);
        System.out.println("Studnet name : " + this.studenttName);
        System.out.println("Pending fine : " + this.fine);
        System.out.println("---------------------------------------");
        System.out.println("        Borrowed Book details");
        System.out.println("---------------------------------------");
        if (borrowlist.size() == 0) {
            System.out.println("No books Borrowed yet!");
        } else {
            System.out.printf("%5s %15s %15s", "Book ID", "Issued Date", "Due date");
            System.out.println();
            for (Borrow borrow : borrowlist) {
                System.out.printf("%5s %15s %15s", borrow.bookId, borrow.issueDate, borrow.dueDate);
                System.out.println();
            }
        }
        System.out.println("---------------------------------------");

    }

    // Pay fine method
    public void payFine() {
        try {
            // Returns if user have no pending fine
            System.out.println("Your pending fine amount is:" + this.fine);
            if (this.fine == 0) {
                System.out.println("You don't have any pending fees! Thank you :) ");
                return;
            }
            // Getting amunt to be payed
            System.out.println("Enter amount to pay:");
            int amt = Integer.parseInt(sc.nextLine());

            // Returning if amount is greater than payable amount
            if (amt > this.fine) {
                System.out.println("Entered amount is greater than payabale amount!");
                return;
            }

            // Deducting the payed fine amount
            this.fine -= amt;
            System.out.println("Amount payed sucessfully!");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input!");
            return;
        }

    }

}
