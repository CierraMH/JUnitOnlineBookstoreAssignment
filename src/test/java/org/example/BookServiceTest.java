package org.example;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookService class TEST")
class BookServiceTest {

    @Mock
    private List<Book> bookDatabase;

    @InjectMocks
    private BookService bookService;
    //-----
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test methods");
    }
    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test method");
    }
    @AfterEach
    void afterEach() {
        System.out.println("After each test method");
    }
    @AfterAll
    static void afterAll() {
        System.out.println("After all test methods");
    }
    //----------
    @Test
    @DisplayName("Test SearchBook() method- POSITIVE")
    void searchBookPositive() {
        List<Book> bookList = new ArrayList<>();
        Book newBook = new Book("Green Eggs And Ham", "Dr.Suess", "Children's Book", 5.00);
        String keyword = "Green";
        bookList.add(newBook);
        Mockito.when(bookDatabase.stream()).thenReturn(Stream.of(newBook));
        assertEquals(bookList, bookService.searchBook(keyword));
    }
    @Test
    @DisplayName("Test SearchBook() method- NEGATIVE")
    void searchBookNegative() {
        List<Book> bookList = new ArrayList<>();
        Book newBook = new Book("Green Eggs And Ham", "Dr.Suess", "Children's Book", 5.00);
        String keyword = "Bob ross";
        Mockito.when(bookDatabase.stream()).thenReturn(Stream.of(newBook));
        assertEquals(bookList, bookService.searchBook(keyword));
    }
    @Test
    @DisplayName("Test SearchBook() method- EDGE")
    void searchBookEdge() {
        //allows you to add the same book more than once
        List<Book> bookList = new ArrayList<>();
        Book newBook = new Book("Green Eggs And Ham", "Dr.Seuss", "Children's Book", 5.00);
        String keyword = "";
        bookList.add(newBook);
        bookList.add(newBook);
        Mockito.when(bookDatabase.stream()).thenReturn(Stream.of(newBook, newBook));
        assertEquals(bookList, bookService.searchBook(keyword));
    }
    //----------
    @Test
    @DisplayName("Test PurchaseBook() method- POSITIVE")
    void purchaseBookPositive() {
        Book newBook = new Book("Green Eggs And Ham", "Dr.Seuss", "Children's Book", 5.00);
        User myuser = new User("USERNAME", "Password", "email@gmail.com");
        Mockito.when(bookDatabase.contains(newBook)).thenReturn(true);
        assertTrue(bookService.purchaseBook(myuser, newBook));
    }
    @Test
    @DisplayName("Test PurchaseBook() method- NEGATIVE")
    void purchaseBookNegative() {
        Book newBook = new Book("Green Eggs And Ham", "Dr.Seuss", "Children's Book", 5.00);
        User myuser = new User("USERNAME", "Password", "email@gmail.com");
        Mockito.when(bookDatabase.contains(newBook)).thenReturn(false);
        assertFalse(bookService.purchaseBook(myuser, newBook));
    }
    @Test
    @DisplayName("Test PurchaseBook() method- EDGE")
    void purchaseBookEdge() {
        //allowed the guest to purchase the book without having an account
        Book newBook = new Book("Green Eggs And Ham", "Dr.Seuss", "Children's Book", 5.00);
        User myuser = new User(null, null, null);
        Mockito.when(bookDatabase.contains(newBook)).thenReturn(true);
        assertTrue(bookService.purchaseBook(myuser, newBook));
    }
}