package com.example.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibraryServiceTest {
    private LibraryService libraryService;

    @BeforeEach
    public void setTestData() {
        Map<String, Book> books = new HashMap<>();
        books.put("1", new Book("Book1", "Author1", 2000, 1L));
        books.put("2", new Book("Book2", "Author2", 2010, 2L));
        books.put("3", new Book("Book3", "Author3", 2020, 3L));
        books.put("4", new Book("Book4", "Author3", 2020, 4L));

        Map<String, User> users = new HashMap<>();
        users.put("100", new User("User1", 25, 100L));
        users.put("200", new User("User2", 30, 200L));

        Map<User, Set<Book>> userBooks = new HashMap<>();
        userBooks.put(
                new User("User2", 30, 200L),
                new HashSet<>(Arrays.asList(
                        new Book("Book1", "Author1", 2000, 1L),
                        new Book("Book2", "Author2", 2010, 2L),
                        new Book("Book3", "Author3", 2020, 3L)
        )));

        libraryService = new LibraryService(books, users, userBooks);
    }

    @Test
    void whenGetAllBooks() {
        List<Book> expected = new LinkedList<>(Arrays.asList(
                new Book("Book1", "Author1", 2000, 1L),
                new Book("Book2", "Author2", 2010, 2L),
                new Book("Book3", "Author3", 2020, 3L),
                new Book("Book4", "Author3", 2020, 4L)
        ));

        Iterable<Book> allBooks = libraryService.getAllBooks();
        assertThat(allBooks).containsAll(expected);
    }

    @Test
    void whenGetAllAvailableBooks() {
        Iterable<Book> expected = new LinkedList<>(List.of(
                new Book("Book4", "Author3", 2020, 4L)));

        Iterable<Book> availableBooks = libraryService.getAllAvailableBooks();
        assertThat(availableBooks).containsAll(expected);
    }

    @Test
    public void whenGetUserBook() {
        List<Book> expected = new LinkedList<>(Arrays.asList(
                new Book("Book1", "Author1", 2000, 1L),
                new Book("Book2", "Author2", 2010, 2L),
                new Book("Book3", "Author3", 2020, 3L)
        ));

        Iterable<Book> result = libraryService.getUserBooks("200");
        assertThat(result).containsAll(expected);
    }

    @Test
    public void whenTakeAvailableBook() {
        List<Book> expected = new LinkedList<>(Arrays.asList(
                new Book("Book1", "Author1", 2000, 1L),
                new Book("Book2", "Author2", 2010, 2L),
                new Book("Book3", "Author3", 2020, 3L),
                new Book("Book4", "Author3", 2020, 4L)
        ));

        libraryService.takeBook(200L, 4L);
        assertThat(libraryService.getUserBooks("200")).containsAll(expected);
    }

    @Test
    public void whenTakingAlreadyTakenBook() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                libraryService.takeBook(200L, 2L));

        assertThat(exception.getMessage()).isEqualTo("This book is already taken");
    }

    @Test
    public void whenReturnBook() {
        Collection<Book> expected = libraryService.getAllAvailableBooks();
        expected.add(new Book("Book3", "Author3", 2020, 3L));
        libraryService.returnBook("200", "3");
        Collection<Book> current = libraryService.getAllAvailableBooks();
        assertThat(expected).containsAll(current);
    }

    @Test
    public void whenReturnAvailableBook() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                libraryService.returnBook("200", "4"));

        assertThat(exception.getMessage()).isEqualTo("This book is already available");
    }

    @Test
    public void whenReturningBookUserNotFound() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                libraryService.takeBook(500L, 4L));
        assertThat(exception.getMessage()).isEqualTo("Can't find user");
    }
}
