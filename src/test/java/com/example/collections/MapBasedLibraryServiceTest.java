package com.example.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapBasedLibraryServiceTest {
    private LibraryService libraryService;

    @BeforeEach
    public void setTestData() {
        List<Book> books = new LinkedList<>(Arrays.asList(
                new Book("Book1", "Author1", 2000, 1L),
                new Book("Book2", "Author2", 2010, 2L),
                new Book("Book3", "Author3", 2020, 3L),
                new Book("Book4", "Author3", 2020, 4L)));

        List<User> users = new LinkedList<>(Arrays.asList(
                new User("User1", 25, 100L),
                new User("User2", 30, 200L)));

        libraryService = new MapBasedLibraryService(books, users);

        libraryService.takeBook(200L, 1L);
        libraryService.takeBook(200L, 2L);
        libraryService.takeBook(200L, 3L);
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
