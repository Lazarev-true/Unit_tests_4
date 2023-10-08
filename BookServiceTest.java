import main.HW.Book;
import main.HW.BookRepository;
import main.HW.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


class BookServiceTest {
    BookService bookService;
    BookRepository bookRepositoryMock;
    Map<String, Book> books;

    @BeforeEach
    void prepareData() {
        bookRepositoryMock = mock(BookRepository.class);
        bookService = new BookService(bookRepositoryMock);
        books = new HashMap<>();
        books.put("1", new Book("1", "Book1", "Author1"));
        books.put("2", new Book("2", "Book2", "Author2"));
        books.put("3", new Book("3", "Book3", "Author3"));
    }

    @Test
    public void findBookByIdMethodTest() {
        List<String> booksKeysAsArray = new ArrayList<>(books.keySet());
        Random ran = new Random();
        String bookID = booksKeysAsArray.get(ran.nextInt(books.size()));
        Book expectedBook = books.get(bookID);
        when(bookRepositoryMock.findById(bookID)).thenReturn(expectedBook);
        Book actualBook = bookService.findBookById(bookID);
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    public void findAllBooksMethodTest() {
        List<Book> expectedBookList = new ArrayList<>(books.values());
        when(bookRepositoryMock.findAll()).thenReturn(expectedBookList);
        List<Book> actualBookList = bookService.findAllBooks();
        assertThat(actualBookList).isEqualTo(expectedBookList);
    }
}