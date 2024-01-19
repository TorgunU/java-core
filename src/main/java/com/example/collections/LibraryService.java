package com.example.collections;

import java.util.Collection;

public interface LibraryService {
    Collection<Book> getAllBooks();

    Collection<Book> getAllAvailableBooks();

    Collection<Book> getUserBooks(String userId);

    void takeBook(Long userId, Long bookId);

    void returnBook(String userId, String bookId);
}
