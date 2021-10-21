import java.time.LocalDate;

public class Borrow {
    int bookId;
    LocalDate issueDate;
    LocalDate dueDate;

    Borrow(int bookId, LocalDate issueDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }
}
