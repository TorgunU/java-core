package com.example.collections;


import java.util.*;

public class LibraryService {
    private Map<String, Book> idToBookMap;
    private Map<String, User> idToUserMap;
    private Map<User, Set<Book>> userBooks;
    private Map<Long, Long> reservedBooks;

    public LibraryService(Map<String, Book> idToBookMap, Map<String, User> idToUserMap,
                          Map<User, Set<Book>> userBooks) {
        this.idToBookMap = idToBookMap;
        this.idToUserMap = idToUserMap;
        this.userBooks = userBooks;

        reservedBooks = new HashMap<>();
        for (User user : userBooks.keySet()) {
            for (Book book : userBooks.get(user)) {
                reservedBooks.put(book.bookId(), user.userId());
            }
        }
    }

    public Iterable<Book> getAllBooks() {
        return idToBookMap.values();
    }

    public Collection<Book> getAllAvailableBooks() {
        Set<Book> availableBooks = new LinkedHashSet<>();
        for (Book book : idToBookMap.values()) {
            if (!reservedBooks.containsKey(book.bookId())) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public Iterable<Book> getUserBooks(String userId) {
        Long boxedUserId = Long.valueOf(userId);
        User foundUser = getUserOrThrow(boxedUserId);
        return userBooks.get(foundUser);
    }

    public void takeBook(Long userId, Long bookId) {
        User foundUser = getUserOrThrow(userId);
        Book foundBook = getBookOrThrow(bookId);
        if (!isBookAvailable(foundBook)) {
            throw new IllegalArgumentException("This book is already taken");
        }
        Set<Book> books = userBooks.get(foundUser);
        books.add(foundBook);
        reservedBooks.put(foundBook.bookId(), foundUser.userId());
    }

    public void returnBook(String userId, String bookId) {
        Book removedBook = getBookOrThrow(Long.valueOf(bookId));
        User user = getUserOrThrow(Long.valueOf(userId));
        if (isBookAvailable(removedBook)) {
            throw new IllegalArgumentException("This book is already available");
        }
        Set<Book> currentUserBooks = userBooks.get(user);
        currentUserBooks.remove(removedBook);
        reservedBooks.remove(removedBook.bookId());
    }

    private User getUserOrThrow(Long userId) {
        User foundUser = idToUserMap.get(String.valueOf(userId));
        if (foundUser == null) {
            throw new IllegalArgumentException("Can't find user");
        }
        return foundUser;
    }

    private Book getBookOrThrow(Long bookId) {
        Book foundBook = idToBookMap.get(String.valueOf(bookId));
        if (foundBook == null) {
            throw new IllegalArgumentException("Can't find book");
        }
        return foundBook;
    }

    private boolean isBookAvailable(Book book) {
        Collection<Book> availableBooks = getAllAvailableBooks();
        return availableBooks.contains(book);
    }
}
