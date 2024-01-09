package com.example.collections;


import java.util.*;

public class LibraryService {
    private Set<Book> books;
    private Set<User> users;
    private Map<User, List<Book>> userBooks;

    public LibraryService(Set<Book> books, Set<User> users, Map<User, List<Book>> userBooks) {
        this.books = books;
        this.users = users;
        this.userBooks = userBooks;
    }

    public Collection<Book> getAllBooks() {
        return books;
    }

    public List<Book> getAllAvailableBooks() {
        Set<Book> handleBooks = new HashSet<>();
        for (List<Book> usersBook : userBooks.values()) {
            handleBooks.addAll(usersBook);
        }
        List<Book> availableBook = new LinkedList<>();
        for (Book book : books) {
            if (!handleBooks.contains(book)) {
                availableBook.add(book);
            }
        }
        return availableBook;
    }

    public List<Book> getUserBooks(String userId) {
        Long boxedUserId = Long.valueOf(userId);
        User findedUser = findUser(boxedUserId);
        return userBooks.get(findedUser);
    }

    public void takeBook(Long userId, Long bookId) throws IllegalArgumentException {
        Book findedBook = findBook(bookId);
        if (!isBookAvailable(findedBook)) {
            throw new IllegalArgumentException("This book is already taken");
        }
        User findedUser = findUser(userId);
        List<Book> books = userBooks.get(findedUser);
        books.add(findedBook);
    }

    public void returnBook(String userId, String bookId) {
        Book removedBook = findBook(Long.valueOf(bookId));
        if (isBookAvailable(removedBook)) {
            throw new IllegalArgumentException("This book is already available");
        }
        User user = findUser(Long.valueOf(userId));
        List<Book> currentUserBooks = userBooks.get(user);
        currentUserBooks.remove(removedBook);
    }

    private User findUser(Long userId) throws IllegalArgumentException {
        User findedUser = null;
        for (User user : users) {
            if (userId.equals(user.userId())) {
                findedUser = user;
                break;
            }
        }
        if (findedUser == null) {
            throw new IllegalArgumentException("Can't find user");
        }
        return findedUser;
    }

    private Book findBook(Long bookId) throws IllegalArgumentException {
        Book findedBook = null;
        for (Book book : books) {
            if (bookId.equals(book.bookId())) {
                findedBook = book;
                break;
            }
        }
        if (findedBook == null) {
            throw new NullPointerException("Can't find book");
        }
        return findedBook;
    }

    private boolean isBookAvailable(Book book) {
        List<Book> availableBooks = getAllAvailableBooks();
        return availableBooks.contains(book);
    }
}
