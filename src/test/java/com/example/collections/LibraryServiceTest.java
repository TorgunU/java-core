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
        Set<Book> books = new LinkedHashSet<>(Arrays.asList(
                new Book("Book1", "Author1", 2000, 1L),
                new Book("Book2", "Author2", 2010, 2L),
                new Book("Book3", "Author3", 2020, 3L),
                new Book("Book4", "Author3", 2020, 4L)
        ));

        Set<User> users = new LinkedHashSet<>(Arrays.asList(
                new User("User1", 25, 100L),
                new User("User2", 30, 200L)
        ));

        Map<User, List<Book>> userBooks = new HashMap<>();
        userBooks.put(
                new User("User2", 30, 200L),
                new LinkedList<>(Arrays.asList(
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

        Collection<Book> allBooks = libraryService.getAllBooks();
        assertThat(allBooks).containsAll(expected);
    }

    @Test
    void whenGetAllAvailableBooks() {
        List<Book> expected = new LinkedList<>(List.of(
                new Book("Book4", "Author3", 2020, 4L)));

        List<Book> availableBooks = libraryService.getAllAvailableBooks();
        assertThat(availableBooks).isEqualTo(expected);
    }

    @Test
    public void whenGetUserBook() {
        List<Book> expected = new LinkedList<>(Arrays.asList(
                new Book("Book1", "Author1", 2000, 1L),
                new Book("Book2", "Author2", 2010, 2L),
                new Book("Book3", "Author3", 2020, 3L)
        ));

        List<Book> result = libraryService.getUserBooks("200");
        assertThat(result).isEqualTo(expected);
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
        assertThat(libraryService.getUserBooks("200")).isEqualTo(expected);
    }

    @Test
    public void whenTakingAlreadyTakenBook() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                libraryService.takeBook(200L, 2L));

        assertThat(exception.getMessage()).isEqualTo("This book is already taken");
    }

    @Test
    public void whenReturnBook() {
        List<Book> expected = libraryService.getAllAvailableBooks();
        expected.add(new Book("Book3", "Author3", 2020, 3L));
        libraryService.returnBook("200", "3");
        List<Book> current = libraryService.getAllAvailableBooks();
        assertThat(expected).containsAll(current);
    }

    @Test
    public void whenReturnAvailableBook() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                libraryService.returnBook("200L", "4"));

        assertThat(exception.getMessage()).isEqualTo("This book is already available");
    }

    @Test
    public void whenReturningBookUserNotFound() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                libraryService.takeBook(500L, 4L));
        assertThat(exception.getMessage()).isEqualTo("Can't find user");
    }
}
