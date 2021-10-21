public class Admin extends Library implements AdminServices {

    // add studnet method to add studnet to Database
    public void addStudent() {
        System.out.println("Enter Studnet name");
        String s_name = sc.nextLine();
        System.out.println("Enter Studnet password");
        String s_password = sc.nextLine();
        addStudentToLibrary(s_name, s_password);
    }

    // Remove studnet method to remove studnet
    public void removeStudent() {

        // getting studnet ID to return
        System.out.println("Enter Studnet ID to remove");
        int s_id = Integer.parseInt(sc.nextLine());
        Student s = isValidStudents(s_id);

        // Checking if studnet have any pending fees or borrowed books
        if (s.fine != 0) {
            System.out.println("You can't remove the Studnet because he/she have some pending fine to be payed!");
            return;
        }
        if (s.borrowlist.size() != 0) {
            System.out.println("You can't remove the Studnet because he/she have some pending books to be returned!");
            return;
        }

        // Removing user from database
        removeStudentFromLibrary(s_id);

    }

    // Adding new book to Library
    public void addBook() {
        System.out.println("Enter Boook name");
        String b_name = sc.nextLine();
        addBooktoLibrary(b_name);
    }

    // Prints the borrowed books by user
    public void showBorrowedBooks(Student s) {
        if (s.borrowlist.size() == 0) {
            System.out.printf("%15s", "-");
            return;
        }
        for (Borrow b : s.borrowlist) {
            System.out.print(b.bookId + " | ");

        }
    }

    // This method is used to generate report
    public void generateReport() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("                        LIBRARY REPORT                     ");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Total Studnet : " + allStudents.size());
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%5s %15s %15s %15s", "S ID", "Name", "fine", "Borrowed Books");
        System.out.println();
        System.out.println("---------------------------------------------------------------");

        for (Student s : allStudents) {
            System.out.printf("%5s %15s %15s ", s.studentId, s.studenttName, s.fine);
            showBorrowedBooks(s);
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Total Books : " + allBooks.size());
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("%5s %15s %15s %15s %15s", "B ID", "Name", "Available", "BorrowerID", "Next available on");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------");
        for (Book b : allBooks) {
            System.out.printf("%5s %15s %15s %15s %15s", b.bookId, b.bookName, b.available, b.borrowerId,
                    b.nextAvailabel);
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------");

    }
}
