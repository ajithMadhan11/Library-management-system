import java.time.LocalDate;

public class Book {

    int bookId;
    String bookName;
    int available = 1;
    int borrowerId;
    LocalDate nextAvailabel;

    Book(int id, String name) {
        this.bookId = id;
        this.bookName = name;
    }

}
