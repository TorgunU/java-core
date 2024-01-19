package com.example.collections;


import java.util.*;

public class MapBasedLibraryService implements LibraryService {
    private Map<String, Book> idToBookMap;
    private Map<String, User> idToUserMap;
    private Map<User, Set<Book>> userBooks;
    private Map<Long, Long> reservedUserBooks;

    public MapBasedLibraryService(Collection<Book> books, Collection<User> users) {
        idToBookMap = new HashMap<>();
        books.forEach(book -> idToBookMap.put(String.valueOf(book.bookId()), book));
        idToUserMap = new HashMap<>();
        users.forEach(user -> idToUserMap.put(String.valueOf(user.userId()), user));
        userBooks = new HashMap<>();
        reservedUserBooks = new HashMap<>();
    }

    @Override
    public Collection<Book> getAllBooks() {
        return idToBookMap.values();
    }

    @Override
    public Collection<Book> getAllAvailableBooks() {
        Set<Book> availableBooks = new LinkedHashSet<>();
        for (Book book : idToBookMap.values()) {
            if (!reservedUserBooks.containsKey(book.bookId())) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    @Override
    public Collection<Book> getUserBooks(String userId) {
        Long boxedUserId = Long.valueOf(userId);
        User foundUser = getUserOrThrow(boxedUserId);
        return userBooks.get(foundUser);
    }

    @Override
    public void takeBook(Long userId, Long bookId) {
        User foundUser = getUserOrThrow(userId);
        Book foundBook = getBookOrThrow(bookId);
        if (!isBookAvailable(foundBook)) {
            throw new IllegalArgumentException("This book is already taken");
        }
        Set<Book> books = userBooks.get(foundUser);
        if (books == null) {
            books = new HashSet<>();
        }
        books.add(foundBook);
        userBooks.put(foundUser, books);
        reservedUserBooks.put(foundBook.bookId(), foundUser.userId());
    }

    @Override
    public void returnBook(String userId, String bookId) {
        Book removedBook = getBookOrThrow(Long.valueOf(bookId));
        User user = getUserOrThrow(Long.valueOf(userId));
        if (isBookAvailable(removedBook)) {
            throw new IllegalArgumentException("This book is already available");
        }
        Set<Book> currentUserBooks = userBooks.get(user);
        currentUserBooks.remove(removedBook);
        reservedUserBooks.remove(removedBook.bookId());
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
